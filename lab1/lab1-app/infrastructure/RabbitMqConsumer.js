import { connect } from "./rabbitmq.js";
import { logger } from "./logger.js";
import getEventClass from "./getEvent.js";
import { config } from "../config.js";

export class RabbitMQConsumer {
  constructor(queueName, callback) {
    this.queueName = queueName;
    this.callback = callback;
  }

  async consume() {
    try {
      const { channel } = await connect();
      await channel.assertQueue(this.queueName, { durable: true });

      channel.prefetch(1);
      logger.info(`Consuming from queue: ${this.queueName}`);

      channel.consume(
        this.queueName,
        async (msg) => {
          if (msg !== null) {
            const eventData = JSON.parse(msg.content.toString());
            const EventClass = getEventClass(this.queueName);
            const eventInstance = new EventClass(eventData);

            this.callback(eventInstance);
            await new Promise((resolve) =>
              setTimeout(resolve, config.application.processingTime)
            );

            logger.info(`Processed event: ${JSON.stringify(eventInstance)}`);
            channel.ack(msg);
          }
        },
        { noAck: false }
      );
    } catch (error) {
      logger.error("RabbitMQ Consume Error:", error);
    }
  }
}
