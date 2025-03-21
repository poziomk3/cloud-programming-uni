import dotenv from "dotenv";
dotenv.config();

export const config = {
  rabbitmq: {
    url: process.env.RABBITMQ_URL || "amqp://localhost",
  },
  application: {
    id: process.env.APPLICATION_ID || "default_app",
    publishEvent: process.env.PUBLISH_EVENT || "",
    publisherType: process.env.PUBLISHER_TYPE || "",
    consumeEvent: process.env.CONSUME_EVENT || "",
    mode: process.env.APPLICATION_MODE || "publisher",
    processingTime: process.env.PROCESSING_TIME || 3000,
  },
};
