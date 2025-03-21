import { Type1Event } from "./Type1Event.js";
import { Type2Event } from "./Type2Event.js";
import { Type3Event } from "./Type3Event.js";
import { Type4Event } from "./Type4Event.js";

// This part is a basic dynamic instantiation — you're choosing which class to instantiate at runtime based on a string (eventType). That's a light form of reflection — manually implemented, not via built-in reflection APIs like in Java or C#.

// JavaScript doesn’t have a strict “reflection” system like other languages, but it does allow dynamic evaluation and object inspection via:

// Object.keys, typeof, instanceof
// dynamic access (object[property])
// new SomeClass() from dynamic references
// eval() (used rarely)
// importing modules dynamically (import())

const EventRegisty = {
  Type1Event,
  Type2Event,
  Type3Event,
  Type4Event,
};

export default EventRegisty;
