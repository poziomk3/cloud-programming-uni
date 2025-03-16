export class BaseEvent {
  constructor(type, data) {
    this.type = type;
    this.data = data;
    this.timestamp = new Date();
  }
}
