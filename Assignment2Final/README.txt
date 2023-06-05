This application runs through gradle using gradle build and gradle run to run the application.
The power is of the shot is connected to the line of the cue as well as the mouse position so try to keep both at a similar position
Classes used for each pattern:
Factory:
- ComponentsFactory
- BallFactory
- TableFactory
- Components
- Ball
- Table

Builder:
- BallBuilder
- BallBuilderImpl
- BallDirector
- Components

Strategy:
- BallStrategy
- BlueBallStrategy
- RedBallStrategy
- WhiteBallStrategy
- Ball
