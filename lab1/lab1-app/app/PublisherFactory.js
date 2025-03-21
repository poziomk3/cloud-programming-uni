import getEventClass from "../infrastructure/getEvent.js";
import { RabbitMQPublisher } from "../infrastructure/RabbitMqPublisher.js";
import { config } from "../config.js";

export class PublisherFactory {
  static createPublisher() {
    const eventType = config.application.publishEvent;
    const EventClass = getEventClass(eventType);
    const publisher = new RabbitMQPublisher(eventType);

    return {
      publishEvent: () => {
        const event = new EventClass({ value: Math.random() });
        publisher.publish(event);
      },
      run: () =>
        publisherStrategy(config.application.publisherType, () => {
          const event = new EventClass({ value: Math.random() });
          publisher.publish(event);
        }),
    };
  }
}

function publisherStrategy(type, callback) {
  if (type === "interval") {
    setInterval(callback, 4000);
  } else if (type === "randomInterval") {
    const scheduleRandom = () => {
      setTimeout(() => {
        callback();
        scheduleRandom();
      }, Math.random() * 5000 + 1000);
    };
    scheduleRandom();
  }
}
