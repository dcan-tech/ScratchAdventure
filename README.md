# Java Text Adventure — `ScratchAdventure`

An object-oriented text adventure game written in Java as an ongoing learning project.

This project was inspired by a book I purchased, *The Little Java Book of Adventure Game Programming* by Huw Collingbourne. Unfortunately, the book is quite dated, and I found much of it difficult to apply to modern Java. I decided instead to build something similar from the ground up, hence the name `ScratchAdventure`.

I have tried to keep the actual game world small at this point and focus instead on scalability and clean, functional code. I have been developing the project incrementally and refactoring the structure as new features require it.

## Current Features

- Room-based world with directional movement
- Player inventory
- Items that can be placed in rooms or carried by the player
- Item commands such as `take`, `drop`, `open`, and `close`
- Container items that can hold other items
- Save and load support
- Persistence of:
  - Player location
  - Player inventory
  - Room contents
  - Container contents
  - Container open/closed state
- Command handling with abbreviated movement and utility commands

## Current Development

I am currently expanding the command system to support more complex interactions between objects, including commands such as placing items inside containers.

A more capable parser also exists in `StructuredCommandParser`, but it has not been implemented yet. I have kept it separate so that the current working structure remains functional while the new parser is developed.

The goal is to allow the game to move beyond simple verb/object commands and understand structures such as:

```text
put axe in chest
```

## Project Goals

The main goal of this project is to strengthen my understanding of Java through practical development. Areas being explored include:

- Object-oriented design
- Inheritance
- Collections
- Maps and iteration
- Command parsing
- State management
- File persistence
- Refactoring as program complexity grows

The project is intentionally being developed over time so that new concepts can be incorporated into an existing codebase rather than practiced only as isolated exercises.

## Status

**Active development.**
