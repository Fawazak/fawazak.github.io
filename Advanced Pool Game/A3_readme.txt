# PoolGame

**Note 1:** The friction value in the config file is used as a multiplier in the
implementation. As the friction approaches 0, the friction decreases. As friction
approach 1, the friction increases. A high value of friction will make it 
impossible for a ball to move. Range of valid friction is: `0 < friction < 1`.

**Note 2:** While the forces applied to the cue ball is variable based on the
length of the line shown when dragging from the cue ball, there is a maximum cap
on the force.

**Note 3:** The center of the ball needs to be in the pocket for the code to 
consider it as "in the pocket" instead of its rectangular bound just intersecting
with the pocket's rectangular bound.

**How to play:** 

- The first screen of the application will be the easy level configuration file. You can choose to change the level at anytime including the beginning.
- To start the game (including the timer and score) the user must press the start button when they are ready to play.
- If the user wishes to change levels they can press the level they want, the timer will stop and in order to start the new level and restart the timer and score the user must also press the start button again. 
- Always press start before starting the game/ level.
- No need to press start when cue ball goes in as it is automatically reset.
- The cheat button works by pressing the cheat button corresponding to the colour.
- A player can undo a cheat as well.

## Commands

* Build: `gradle build` to build the application

* Run: `gradle run` to load the easy config file and start the game 


* Generate documentation:`gradle javadoc` or in intellij Tools>Generate Javadoc..


## Features implemented:
- Pocket configuration
- More coloured Balls
- Undo
- Cheat
- Difficulty level
- Timer and score

## Design Patterns:

- State:
  - Context.java was the Context class
  - State.java was the state interface
  - EasyLevel.java was the Concrete state
  - NormalLevel.java was the Concrete state
  - HardLevel.java was the Concrete state
- Observer
  - Observer.java was the Observer interface
  - ObserverImpl.java was the Concrete observer Class
  - Subject.java was the Subject interface
  - Game.java was the Concrete Subject
- Memento: 
  - Memento.java was the Memento Class
  - Caretaker.java was the CareTaker Class
  - Ball.java was the Originator