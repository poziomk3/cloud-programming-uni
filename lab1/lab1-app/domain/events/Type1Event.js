import { BaseEvent } from "./BaseEvent.js";
export class Type1Event extends BaseEvent {
  constructor(data) {
    super("Type1Event", data);
  }
}
