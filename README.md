**Scott Lai <br>
A01158559**

This is a Java Project for Game of Life. It has 3 packages.

Below are my explainations of my design decisions.

# Package application

## Main
    Main Class to start the program.

Contains `public static void main(String[] args)` and an instance of `Game`.
The reason I split `Game` from `Main` is because it allows us to easily abandon
the current game and start over again without having to restart the program.

## Game
    Represents a Game of Life

A game has a `World`. This allows in the future, if we want to include more 
Worlds into our game, we can just easily instantiate more `World` instances to 
our game without chaning the code too much.

# Package organism

## LifeForm
    Abstract class to represent a LifeForm, i.e. Plant Eaters and Plants in the 
    Game of Life.

A super class for all organism in the Game of Life. It contains many useful 
methods such as `die()`, `moveTo(Cell)` and three abstract methods called 
`takeTurn()`, `edibleBy(LifeForm)` and `eat(LifeForm)`. This ensures that if we
want to add a new `LifeForm` into the game, that `LifeForm` must do something at
the start of a turn, define which other `LifeForm` can eat it and what happens 
when it `eat()` other `LifeForm` and having access to useful methods and 
attributes for an easier implementation.

## Herbivore
    Class to represent a Plant Eater Object in the Game of Life.

Represents a Plant Eater in the Game of Life. It inherits from the `LifeForm` 
super class. It has other attributes and methods such as `hp`.

## Plant
    Class to represent a Plant in the Game of Life.

Represents a Plant in the Game of Life. It inherits from the `LifeForm` super 
class which allows us to group it and `Herbivore` together in `Cell`.

# Package utility

## RandomGenerator
    Utility class to generate a sequence of predefined pseudorandom integers.

Helps the LifeForm to randomly choose the Cells to move or spawn on. This class
allows for an easier implementation of choosing moves in `World` and `Cell`.

# Package world

## Cell
    Class to represent a Cell of a world in the Game of Life.

Represents a single Cell in the Game of Life. It is responsible for spawning 
`LifeForm` and other important methods such as actually "killing" a `LifeForm`
by removing the reference to its occupying `LifeForm`. 

## World
    Class to represent a World of the Game of Life.

Represents a World in the Game of Life. This class is basically a 2D array of 
`Cell`. It also has other important and useful utility methods such as 
`randomCell(List<Cell>)`. It's also responsible for handle mouse click.