import amqp from "amqplib";
import { config } from "../config.js";
import { logger } from "./logger.js";

let connection = null;
let channel = null;

export async function connect() {
  if (connection && channel) {
    return { connection, channel };
  }

  try {
    connection = await amqp.connect(config.rabbitmq.url);
    channel = await connection.createChannel();

    connection.on("error", (err) => {
      logger.error("RabbitMQ connection error:", err);
      connection = null;
      channel = null;
    });

    connection.on("close", () => {
      logger.warn("RabbitMQ connection closed");
      connection = null;
      channel = null;
    });

    return { connection, channel };
  } catch (error) {
    logger.error("Failed to connect to RabbitMQ", error);
    process.exit(1);
  }
}
