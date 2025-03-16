import { Type1Event } from "../domain/Type1Event.js";
import { Type2Event } from "../domain/Type2Event.js";
import { Type3Event } from "../domain/Type3Event.js";
import { Type4Event } from "../domain/Type4Event.js";

const getEventClass = (eventType) => {
  switch (eventType) {
    case "Type1Event":
      return Type1Event;
    case "Type2Event":
      return Type2Event;
    case "Type3Event":
      return Type3Event;
    case "Type4Event":
      return Type4Event;
    default:
      throw new Error("Unsupported event type");
  }
};

export default getEventClass;
