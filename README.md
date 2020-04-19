This is an archive for my Java Assignemnt 03 (Game Of Life) for my COMP 2522
(Object-Oriented) class at BCIT.

Game of Life (NOT Conway's Game of Life) is a simulation of a world where there
are *Plant*, *Herbivore*, *Carnivore*, and *Omnivore*.

# Rules

At the start of each *turn (day)*, an *organism* will lose 1 *hp* unless it *eats* other *organism*, which will restore its *hp* to full. In addition, it will *breed* (if possible), and then *move* to 1 adjacent tile (randomly). An *organism* will **DIE** if it gets eaten by other *orgnaism*, or its *hp* reaches 0. 

Below are some attributes for the organisms in this simulation.

## Plant
* Represents as **GREEN** tile
* Prey: nothing
* Can *breed* if surrounded by at least 2 other *plants*, and at least 3 empty tiles.

## Herbivore
* Represents as **YELLOW** tile
* Preys: *plants*
* Can *breed* if surrouned by at least 1 other *herbivore*, at least 2 empty    tiles, and at least 3 *preys*

## Carnivore
* Represents as **RED** tile
* Preys: *herbivores*, and *omnivores*
* Can *breed* if surrounded by at least 1 other *carnivore*, at least 3 empty tiles, and exactly 2 *preys*
  
## Omnivore
* Represents as **ORANGE** tile
* Preys: *plant*, *herbivores*, and *carnivores*
* Can *breed* if surrounded by at least 1 other *omnivore*, at least 3 empty tiles, and exactly 1 *prey*

# Demo

## Square World
![]([../media/demo-square.gif?raw=true](https://github.com/tuonglai3602/Game-Of-Life/blob/media/demo-square.gif))

## Hexagonal World
