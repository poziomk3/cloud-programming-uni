import { RabbitMQConsumer } from "../infrastructure/RabbitMqConsumer.js";
import { config } from "../config.js";

export class ConsumerFactory {
  static createConsumer(callback) {
    return new RabbitMQConsumer(config.application.consumeEvent, callback);
  }
}
