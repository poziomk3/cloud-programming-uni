import { connect } from "./rabbitmq.js";
import { logger } from "./logger.js";

export class RabbitMQPublisher {
  constructor(queueName) {
    this.queueName = queueName;
  }

  async publish(event) {
    try {
      const { channel } = await connect();
      await channel.assertQueue(this.queueName, { durable: true });
      channel.sendToQueue(this.queueName, Buffer.from(JSON.stringify(event)));
      logger.info(
        `Published to queue: ${this.queueName}, Event: ${JSON.stringify(event)}`
      );
    } catch (error) {
      logger.error("RabbitMQ Publish Error:", error);
    }
  }
}
