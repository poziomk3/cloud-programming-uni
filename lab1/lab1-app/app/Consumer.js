import { connect } from "../utils/rabbitmq.js";
import { logger } from "../utils/logger.js";
import { config } from "../config.js";
import getEventClass from "../utils/getEvent.js";

export class Consumer {
  constructor(callback) {
    this.eventType = config.application.consumeEvent;
    this.callback = callback;
  }

  async run() {
    const { channel } = await connect();
    await channel.assertQueue(this.eventType, { durable: true });

    const EventClass = getEventClass(this.eventType);

    channel.prefetch(1);

    channel.consume(
      this.eventType,
      async (msg) => {
        if (msg !== null) {
          const event = JSON.parse(msg.content.toString());
          const eventInstance = new EventClass(event);
          this.callback(eventInstance);
          await new Promise((resolve) => setTimeout(resolve, config.application.processingTime));
          logger.info(
            `Application ${
              config.application.id
            }, Finished processing event: ${JSON.stringify(eventInstance)}`
          );
          channel.ack(msg);
        }
      },
      { noAck: false }
    );
  }
}
