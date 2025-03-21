import { PublisherFactory } from "./PublisherFactory.js";
import { ConsumerFactory } from "./ConsumerFactory.js";
import { config } from "../config.js";

export class AppInitializer {
  static start() {
    switch (config.application.mode) {
      case "publisher":
        PublisherFactory.createPublisher().run();
        break;

      case "consumer":
        const consumer = ConsumerFactory.createConsumer((event) => {});
        consumer.consume();
        break;

      case "publishAfterConsume":
        const publisher = PublisherFactory.createPublisher();
        const consumerAfterPublish = ConsumerFactory.createConsumer((event) => {
          publisher.publishEvent();
        });
        consumerAfterPublish.consume();
        break;

      default:
        throw new Error("Invalid application mode");
    }
  }
}
