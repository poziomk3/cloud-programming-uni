// GPT generated doc - reflection
// This part is a basic dynamic instantiation — you're choosing which class to instantiate at runtime based on a string (eventType). That's a light form of reflection — manually implemented, not via built-in reflection APIs like in Java or C#.

// JavaScript doesn’t have a strict “reflection” system like other languages, but it does allow dynamic evaluation and object inspection via:

// Object.keys, typeof, instanceof
// dynamic access (object[property])
// new SomeClass() from dynamic references
// eval() (used rarely)
// importing modules dynamically (import())

import EventRegistry from "../domain/events/index.js";

const getEventClass = (eventType) => {
  const EventClass = EventRegistry[eventType];
  if (!EventClass) throw new Error("Unsupported event type");
  return EventClass;
};

export default getEventClass;
