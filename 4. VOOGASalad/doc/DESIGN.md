
# Design 

## Design Goals
### GAE
* To create an attractive, user-friendly GUI that is intuitive and straight-forward for the Game Designer
* To create a GUI that leverages the flexibility of the Game Engine by allowing for highly customizeable game design
* To create encapsulated code that allows for the easy addition of new features to the GUI
* To minimize code smells by eliminating duplicate code and other poor coding pratices
* To check user input for errors and handle any exceptions without crashing the program
* To be able to switch between the GAE and Game Player GUI's in order to implement live game editing
* To save the data in such a way so that it can be accessed and parsed by the other sub-teams in order to load and play a game

### Engine
* Make it easier to add in new implementations of strategies for sprites
* Create an intuitive and easy to use API for interaction with the Player and to protect internal engine objects
* Support a flexible variety of game rules
* Support live game editing
* Support a variety of sprite characteristics and actions including movement, shooting, health, etc.
* Insulate the majority of the engine code from JavaFX dependency
* Make it easy to build complicated games with various levels; make it easy to extend engine to run different types of games or multiple games at once

## Design Overview
### GAE
- At a high level, we created a class called ``Bus.java`` , which displayed a Scene from which the user could choose to either enter the Game Authoring Environment(GAE) and create a game, or select to load a game in from their local file system. This Bus class contained an instance of both the GAE and the Game Player, and it was simply a matter of calling on the GAE's API or the Player's API, both of which returned their respective Scenes. 
    - On the GAE's side, the class which defined this API was ``SceneCreator.java``. This API, ``createGAEScene()``, invokes a class called ``TabPaneCreator.java`` , adds this properly configured TabPane to a root and attaches the root to the Scene being returned.
    - One level below this, is the functionality associated with the ``TabPaneCreator.java`` class. This class has a public API which returns a TabPane, called ``getTabPane``, which provides the SceneCreator class with a properly configured TabPane. Through the private methods ``createTabPane()`` and ``createPane()``, this class reads a Resource Bundle, called "typeToParams" and creates an instance of ``ParameterCreator.java`` for each key in the resource file. ``ParameterCreator.java`` extends a BorderPane, and its constructor takes in other properties defined in a Resource Bundle, thereby populating the BorderPane of each ``ParameterCreator`` instance with the appropriate Nodes and labels. To give an example of this, a key in "typeToParams" is "Enemies", and associated with it is Name,Image,ImageHeight,ImageWidth,Preview,Health,EnemySpeed,CreateCost,DestroyCost,Submit. In a separate Resource Bundle, I specify that "Name" requires a TextField, "Image" requires an instance of a class that extends a Button and opens up a file chooser, etc. Using reflection, this original ``TabPaneCreator.java`` class is therefore able to dynamically create instances of ParameterCreator and add each BorderPane to a Tab which would then be added to the TabPane.
    - At the same level, however, TabPaneCreator also creates an instance of ``LevelConfigPane.java`` and adds it to the TabPane along with the other tabs. The reason for separately creating a `LevelConfigPane.java` class rather than grouping it with the other instances of ``ParameterCreator.java`` is because the Level Configuration pane is inherently a very different one, with a different layout and different functionality.
            - The ``LevelConfigPane.java`` class extends a BorderPane as well, and it displays all the objects (Towers, Enemies, Obstacles) that have been created on click of the ParameterCreator instances's "Submit" buttons. It gives the user the option to activate objects for a level, it calls upon the ``MapConfig.java`` class to allow a user to configure a map for a level, it allows the user to configure the amount of resources and lives for a level, and it allows the user to define the "Rules of the game" through Conditions and Actions. Once a user clicks the "Create a New Level" button, all the configured states are saved within the ``LevelConfigPane.java`` class, and fields are cleared. When the user clicks the "Submit Game" button, they are brought back to the initial Bus Scene, an XML is configured and saved through calls to ``FileCreator.java``'s APIs, and the user can then load the game and play it. 

![](https://i.imgur.com/leF2Leh.jpg)
### Player
The Player class instantiates the engine, and then instantiates the PlayerVisualization class with the necessary Player and Engine state values as parameters. It then sets up the game loop which does two things:

1. If the engine signals a level switch through a call to its API method didLevelSwitch(), Player calls setNewLevel() on its PlayerVisualization object.
2. Otherwise, it calls the engine API method execute followed by PlayerVisualization's update method, passing it the Object object's fields onScreenSprites, lives and resources.

PlayerVisualization instantiates all the UI elements of the Player, and coordinates them, allowing for a simple API call to update to update the entire UI.

### Engine
The API for the game engine provides a way for other classes outside of the engine to request actions and items from the engine.This API is made up of 3 components: the `Engine`, which is instantiated at the start of the game and serves as a way for requesting objects that may not change from frame to frame (for example, the engine provides the `getGameTitle()` method, which is global across the game), the `ActionsProcessor`, which serves as a way for classes outside  the engine to request engine actions (for example, two of the actions processed by the actions requester is `processAddSpriteAction(int prototypeId, int xPos, int yPos)` and `processGameEditingAction(Document doc)`), and the `GameSceneObject`, which is returned from frame to frame and contains everything that may have changed across different frames (for example,`getOnScreenSprites()` which returns all active on-screen sprites for this frame). 

As for the configurators, when the engine is initialized, and XML document is passed to it that represents the game, this XML is then passed to the`Game` class from the `Engine` class, and the `Game` class calls upon the `GameConfigurator`, `LevelsConfigurator`, `PrototypesConfigurator`,`ConditionsConfigurator`, and `PrototypesConfigurator` to parse the XML and turn it into the corresponding engine objects.

Last, the executors make up the primary part of the engine design for functionality. The executors executes everything that goes on within a game. The executor is broken down into essentially 2 primary components: game control and sprites. The others (utils, exceptions, and object creators) are primarily there to help these 2 primary components. Some key features inside the executors include the implementation of the Factory pattern for selecting a specific front-end Sprite (`JavaFXSprite`), the Builder pattern for complicated objects creations (`StrategyBuilder` for building a variety of sprite strategies from the same strategy interfaces, `SpriteBuilder` for building a sprite with many optional strategy fields, `LevelBuilder` for building a level with the necessary managers), the Prototype pattern for building active on-screen sprites, and the Strategy pattern for implementing different approaches to singular strategy within a sprite. The executors also leverage actions and conditions to implement the rules of the game as well as process Live Game Editing requests and UI interactions.


## Assumptions and Decisions

### Engine
* Assumes no enemy detection needed (we felt that this was an algorithmic feature that can easily be implemented with our Strategies Pattern, which has already been added by the AttackStrategy, and does not add anything significant to our design).

## How to Add New Features

### Engine
* To add in new implementations of a strategy, simply create the new classes. Then, make sure to add the necessary arguments for them into to the `StrategyBuilder` class; then, an XML with the necessary tags and type for this strategy will be automatically processed correct 
* To add in new strategies, create the new classes, create a `Builder` for the according class. Inside `SpriteBuilder`, change the builder to hold this new strategy and inside `Sprite`, change the sprite to hold this new strategy. Then, in the `PrototypesConfigurator` class, add a method to allow for parsing of this strategy using the specific builder you have just created following the example methods already implemented. 
* To add in new actions, simply create the appropriate action class, have it implement either `GameAction` or `LevelAction`, and implement its necessary methods. The action will be automatically processed.
* To add in new conditions, simply create the appropriate condition class, have it extend either `GameCondition` or `LevelCondition`, and implement its necessary methods. The condition will be automatically processed.
* To add in more Live Editing Features, simply categorize them as either a level-level editing feature, and game-level editing feature, or a prototype-level editing feature and add it in as an action. Add your action and its categorization as a key value pair inside `LiveGameEditing.properties`. Everything else should be automatically processed.

