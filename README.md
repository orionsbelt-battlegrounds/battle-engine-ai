AI for the battle engine [![Build Status](https://travis-ci.org/orionsbelt-battlegrounds/battle-engine-ai.png?branch=master)](https://travis-ci.org/orionsbelt-battlegrounds/battle-engine-ai) [![Coverage Status](https://coveralls.io/repos/orionsbelt-battlegrounds/battle-engine-ai/badge.png?branch=master)](https://coveralls.io/r/orionsbelt-battlegrounds/battle-engine-ai?branch=master)
================

The `battle-engine-ai` is part of the [Orion's Belt BattleGrounds](https://github.com/orionsbelt-battlegrounds) project. It mimics the main [battle-engine](https://github.com/orionsbelt-battlegrounds/obb-rules) rules and aims to provide an *artificial intelligence* module in `scala` to play the game via the [api](https://github.com/orionsbelt-battlegrounds/api).

#### Install and Run

* Download and install [sbt](http://www.scala-sbt.org/)
* run `git clone git@github.com:orionsbelt-battlegrounds/battle-engine-ai.git`
* run `sbt test`

If everything is ok and we haven't blown up the build, you should have a [valid working copy](https://travis-ci.org/orionsbelt-battlegrounds/battle-engine-ai).

### Game Rules

![Board Example](https://raw.github.com/orionsbelt-battlegrounds/battle-engine-ai/master/doc/SampleBoard.jpeg)

The game is tipically played on a 8x8 board. Each square on the `Board` is associated with a `Coordinate(x,y)` and contains an `Element`. The element contains the `CombatUnit`, the quantity, the owner and the current direction. A `CombatUnit` has several attributes and may have special powers. You can see all the defined [units in javascript](https://github.com/orionsbelt-battlegrounds/battle-engine/tree/master/src/units) (or [json](http://rules.api.orionsbelt.eu/units)).

#### CombatUnit Attributes

* Name and code
* Attack and attack range
* Defense
* Movement type (all, diagonal, front, normal)
* Category: light, medium or heavy
* Bonus
* Special powers: rebound, strike back, catapult, tripple attack, paralyse, poison

Each combat unit may have any combination of the previous attributes, making each unit unique and with its own gameplay.

#### Turn

Each player plays in turn. The player has **6 action points** to spend on its turn.

* Each unit has it own movement cost
* Splitting a combat unit costs the double of its movement cost
* Rotating costs 1 movement point
* Attacking costs 1 movement point, and the unit can't do anything else on the current turn
