import amqp from "amqplib";
import { config } from "../config.js";
import { logger } from "./logger.js";

export async function connect() {
  try {
    const connection = await amqp.connect(config.rabbitmq.url);
    const channel = await connection.createChannel();
    return { connection, channel };
  } catch (error) {
    logger.error("Failed to connect to RabbitMQ", error);
    process.exit(1);
  }
}
