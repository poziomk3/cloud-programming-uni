import { connect } from "../utils/rabbitmq.js";
import { logger } from "../utils/logger.js";
import { config } from "../config.js";
import getEventClass from "../utils/getEvent.js";

export class Publisher {
  constructor() {
    this.publisherType = config.application.publisherType;
    this.eventType = config.application.publishEvent;
    this.eventClass = getEventClass(this.eventType);
  }

  async publish(event) {
    const { channel } = await connect();
    await channel.assertQueue(this.eventType);
    channel.sendToQueue(this.eventType, Buffer.from(JSON.stringify(event)));
    logger.info(
      `Application ${config.application.id}, Published ${
        this.eventType
      }: ${JSON.stringify(event)}`
    );
  }

  publishEvent = async () => {
    const event = new this.eventClass({ value: Math.random() });
    await this.publish(event);
  };

  run() {
    if (this.publisherType === "interval") {
      setInterval(this.publishEvent, config.application.processingTime);
    } else if (this.publisherType === "randomInterval") {
      const scheduleRandom = () => {
        setTimeout(() => {
          this.publishEvent();
          scheduleRandom();
        }, Math.random() * 5000 + 1000);
      };
      scheduleRandom();
    }
  }
}
