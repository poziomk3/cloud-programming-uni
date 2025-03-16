import { config } from "./config.js";
import { Publisher } from "./app/Publisher.js";
import { Consumer } from "./app/Consumer.js";
const mode = config.application.mode;

if (mode === "publisher") {
  const publisher = new Publisher();
  publisher.run();
} else if (mode === "consumer") {
  const consumer = new Consumer(() => {});
  consumer.run();
} else if (mode === "publishAfterConsume") {
  const publisher = new Publisher();
  const consumer = new Consumer(() => publisher.publishEvent());
  consumer.run();
}
