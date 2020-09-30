# Design Plan

## Introduction

Our goal is to create a program that provides a graphical interface for creating Tower Defense style games. We aim to empower the developer to test their games as comprehensively as possible by allowing live editing of games (i.e editing the game while it's being played). 

We plan to develop games using 'Sprites', which comprise of towers, enemies, enemy spawn zones, etc, as well as 'Actions' that act on levels and sprites. Our primary design goal is to implement Sprites as highly abstracted elements so that adding new types of elements to our game architecture environment is as straightforward as possible. We plan to accomplish this goal through a separation of our game authoring environment and our actual game engine. In addition, we also use Composition in the form of the Strategy pattern in order to make creating sprites as flexible as possible. By returning a GameSceneObject to the front-end, we also allow for modularity in that the player does not need to know everything in the back-end. 

## Overview

We decided to break up the functionality of our program into the following four components:

#### Game Authoring Environment (GAE)
Responsible for displaying the GUI that allows the developer to create a Tower Defense game. Includes a display class, an Exception interface, Exception classes, a configuration class to prepare the data for export, and a write class for the XML file to be exported.  

#### Game Data
Comprised of two components. Firstly, the XML file that defines the particular Tower Defense variant developed by the game developer. And secondly, the data representing the current state of the game. This is a file containing the data obtained by serializing the collection of objects created by the Game Engine.

#### Game Player
Responsible for visualizing the game developed by the user. Uses the XML file to set up the initial game as well as the collection of serializable objects to represent the current state of the game (i.e. specific placements of enemies, towers and bullets at the current moment in time for example). Allows user interaction (or playing the game) by calling the game engine to update the collection of serializable objects as applicable (or simply returning some information from one of the objects). In the second sprint, we have decided to move the XML set up process inside the Game Engine rather than the Game Player in order to not expose internal classes of the backend.

#### Game Engine 
Responsible for defining the functionality of the various components implemented by the game development environment. Creates a collection of serializable objects that represent the current state of the game 


#### Module Interaction

When main.java is run, it will instantiate the bus class.  This class contains methods to create a scene.  The scene will have tabs to select between the GAE and the game itself.  Depending on the tab selected by the user, the bus class will either instantiate the GAE or the game player environment. 

If the GAE is chosen, the user is prompted with fields to design a game.  Once they are ready to play their game, the GAE generates an XML file.

If the game player tab is chosen, it will instantiate the game engine.

The game engine, upon instantiation, creates the collection of serializable objects representing the initial states of the various components specified by the user through the XML file.

The game player is now ready to visualise the game. It sets up the game loop which does three things repeatedly:
* Displays the game based on the XML file and the collection of serializable objects created by the game engine
* Allow the user to interact with the game (by trying to place a tower for example)
* Calling the appropriate game engine methods to update the collection of serializable objects as applicable

To enable live editing of games, we have implemented a Bus class. In this Bus class, we have initialized the program's GAE instance as well as the program's Game Player instance. In the Game Player GUI, the user can choose to pause the game. If the game player selects to "Edit Game", a first method is invoked within the Bus class (keeping in mind that the Bus class has access to both the GAE and Game Player objects). This method gathers the most recent XML that the Game Player class was reading (immediately prior to the pause), and calls a method to update the GAE's display with this XML (e.g. ``GAEObject.updateXML(gamePlayerObject.getXML())``). After updating the XML, the Bus method sets the current visualization scene to be that of the GAE.

Now, the developer is looking at the GAE that has had its default states populated based on the current-game states. The developer can make further changes, and once the developer has finished, he can click a button that will save and export a newly updated XML file into a second Bus method. This second Bus method takes the newly updated XML file exported from the GAE and passes it back to the Game Player instance (e.g. The method within Bus would look like ``gamePlayerObject.loadXML(xmlFromGAE)``). From here, the Game Player class loads in this new XML file and reflects the changes made by interacting with the Game Engine. Finally, after loading the new XML into the Game Player class, this second Bus method sets the scene back to the Game Player GUI and resumes the game loop.


## Design Details

#### Game Authoring Environment (GAE)

This module will read in Resource Bundles for all the options available to the Designer. These are options for the map/terrain, the path enemies can folow, the enemies, the towers, and the obstacles the developer can place. The GAE will display a skeleton of all these options and allow the developer to edit the values of each of these options in the `Display` class. Once this is completed, the developer can click on a button to save the XML to his local filesystem. Next, a button will appear that will allow the developer to jump into the game (and enter the `Game Player` class's GUI) or return to the homescreen (to be created).

If the player decides to jump into the game, he can load in an XML file from his filesystem and play the game.
At any point, the player can pause the game and choose to "Edit". The following diagram shows the file structure that will handle this live game editing feature.

![](https://i.imgur.com/eraetIJ.jpg)


From within the `GamePlayer` class, the "EditGame" button will invoke the `changeToGAE()` method, which is defined within the Bus class. This method calls on `myGAE.updateXML()` and passes it the XML that `GamePlayer` was most recently reading (meaning it holds current-game state values). What the GAE class does with this XML is reload the GAE and sets its default state values to the current-game state values. The `changeToGAE()` method then sets the current scene to be that of the GAE (now updated). From here, the developer can make further changes to the game, and once he is done, he can click on the "Save and Return To Game" button, which will invoke another method that lives within the Bus class. This method, `changeToPlayer()`, saves the changes made in the GAE, configures a new XML file, and passes the new XML into the Game Player class through the `myPlayer.loadXML()` method. This method within the `GamePlayer` class will have to update the game engine to reflect the new XML file being read. After this update, the `changeToPlayer()` method sets the current scene to be the Game Player's GUI. The game loop can now be resumed from within the `GamePlayer` class. 


#### Game Player

The Game Player module handles the actual game. This module will contain the `GamePlayer` class, which will house the Game Loop, and inside the Game Loop we will repeatedly display the game based on the initial XML file and then the current collection of serializable objects created by the game engine. We will also allow the user to interact with the Game from within the Game Loop - live editing - which will update the states of each serializable tower/obstacle object that has been placed in the game. Also within the game loop, `GamePlayer` will make calls to the appropriate GameEngine methods to update the states of each serializable object in the engine's collection, such as in game towers, obstacles, and enemies. The game will rerender within each Game Loop based on the current states of each serializable object in the Game Screen.

The Game Player module will contain different front end components that will be put together in a `PlayerInterface` class, which is what will be displayed in the Player GUI. This will be made up of a `RightPane` object which allows the user to interact with the Towers and Obstacles, and a `GameScreen` object in which towers/obstacles will be placed, and the animation will take place.

The GamePlayer will have a class for parsing through the XML file to make calls to the Engine, passing it the information it needs to construct the initial collection of serializable objects.


#### Game Engine
The game engine sits within the GamePlayer, and is invoked at every step of the game loop. The `Engine` class serves as the point of entry into the rest of the encapsulated components of Game Engine. Within this component, there are several different components; at a high level, they are as follows: the `LevelManager`, which manages the different `Level`, which in turn has instances of `StatusManager`, `WavesManager`, `SpriteManager`, `ActionsManager`, and `ConditionsManager`. 

The `Engine` holds all states that are global to the game and and the collection of level sequence. The `Engine` class serves as the entry and exit point into the Game Engine (as an entire component) at any particular time; thus, whenever the `GamePlayer` wants to invoke actions carried out by the Game Engine as a unit, it would have to go through the `Engine` class. The `Engine` further manages the current level; thus, whenever actions are invoked for a current level, the `Engine` would access its current level manager and carry out the request. Then, the request would change states of objects in the Game Engine. This change will then be composed and serialized into a `GameStateObject` that is passed back to the `Player` to render.

The `Level` class manages everything that is specific to a level; this includes the sprites list, the the actions and conditions, etc. Based on different actions, such as collisions, place tower, etc. the `Level` calls different `Manager` classes that will interact with `Sprite` objects and handle events such as collisions. 

The `Sprite` object holds everything that is specific to a sprite, this includes a `Health` strategy, a `Range` strategy, etc. The `Sprite` object will leverage a Builder pattern in order to construct specific classes with these fields. These components will be interfaces that will also handle different implementations of handling things such as `Health`. 

#### Game Data

After the Designer selects the options they want for the new game, this information will be stored in an XML file.  Also in this module will be default settings for each level stored in XML files.  



## User Interface

#### Game Authoring Environment (GAE)

The GAE will have an empty virtual game map component, which the user can manipulate, and another right panel on the side where the user can change game attributes or access components to drop into the map. 
The user can interact with the virtual map in a variety of ways: they can create a spawn point or multiple spawn points which are defined as points where enemies spawn from. The ability to create multiple spawn spots will allow the user to change up the type of tower defense game, such as Bloons with one spawn point and Plants vs Zombies with multiple spawn points. The user will be able to drag and drop a base which they need to protect, and will draw a path between the spawn point(s) and the base with path components. 
The user can also set different attributes in their game on the right panel. They can set the background for the map by importing an image or choosing from the provided options, and this will be reflected in the virtual map. They can create and choose which types of towers to make available to the user, and the different attributes associated with these towers. For example, the user can change the image of the tower for each type of upgrade, and the different types of attack such as fire and ice, as well as the attack attributes such as damage and rate of fire for each type of upgrade. The user will have to set the cost of the tower in coin. The currency will be a big part of our game, and the user will be able to buy and upgrade towers with game coins.
The user can set the different enemy types in their game and in each specific level, and the attributes associated with each type of enemy such as image, health, and damage. They can also set waves of enemies, and how many of each type of enemies comes in each wave, as well as the order that the waves come in.
The user can also specify on the map where the towers can and cannot be placed.
The user will have the ability to add new levels, which will create a new blank virtual map for the user to interact with.

#### Game Player Environment

The game player environment will have a similar look to the authoring environment in the sense that there will be a main game map and a right pane. The main game map will look just as configured in the authoring environment.
The right pane will be a control pane for the towers. Users can drag and drop different towers and place them on the map. Once dropped, they can set the orientation of the tower - the direction in which it will fire its projectile attack. All of the different types of towers that the user made available in the game authoring environment will be on this right pane. The player can only drag and drop a tower if they have enough coins to buy one. If you click on the type of tower in the right pane, you have options to buy the upgrades for the tower that were specified in the game authoring environment. 

The player environment will have a run button which will start the animation/spawn of enemies and the attacks of the towers.

#### UI Prototype

Below you are visuals of what the GAE and the Game Player will look like.

GAE:

![](https://i.imgur.com/fRBAgF9.png)

The GAE will conist of three main components: a game preview, a popup that allows the user to design the specific attributes, and a panel that allows the designer to choose the respective popup (as seen in the right side of the GAE).

Game Player:

![](https://i.imgur.com/kmrPTYC.png)

The image above demonstrates how the game player will look like. It will conist of two main compoennts: the actual game and a "drop down" panel that allows the player to choose a speific tower or obstacle to add to the path (items that were created in the GAE). When the user clicks towers or obstacles, a drop down will show up. In this drop down, the player can choose the objects they want.

![](https://i.imgur.com/Yoh8yfd.png)

The image above shows a visual representing what will show up when the user clicks towers or obstacles.

Extras not Visualied:
* The enitre game game scene is essentially a transparent grid. The user will click on the screen to create a path, "activating" cells whiich help create the path (similar to a cell society concept).




## Code Interfaces
##### This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed but are not significant to include in a high-level description of the program). Describe how each module handles specific features given in the assignment specification, what resources it might use, how it collaborates with other modules, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Look for opportunities to share APIs between your sub-teams (e.g., authoring and player front ends or authoring and engine back ends) that may themselves be separate modules (like Java and OpenJFX are composed of several modules). Note, each sub-team should have its own external and internal APIs. Finally, justify the decision to create each module with respect to the design's key goals, principles, and abstractions.

#### Game Authoring Environment:
    ## Display Class -- provide a scene for user to graphically edit aspects of the game, including towers, enemies, level.
        # public Display()
            GameConfiguration currentConfiguration = new GameConfiguration();
        # private setObjectInfo(ResourcesBundle objectInfo)
        # private editTowers() -- provide a window/dropdown for editing tower stats.
        # private editEnemy() -- provide a window/dropdown for editing the enemy stats
        # private editLevel() -- provide a window/dropdown for editing specifics of each level.
        # private loadPresetConfig(XML) -- read an premade xml file and load saved setting (for live game editing as well as loading premade game).
        
    ## GameConfiguration -- can be extended to add more elements of the game
        # public addTowerType() -- add a new type of tower (object? something that represesnts all the info) into game configuration.
        # public addEnemyType() -- add a new type of enemy
        # public addLevelInfo() -- add a new level 
    ## XMLWriter
        # public exportToXML(GameConfiguration)
        
#### Game Data:
    ## GAE xml -- xml file coding for initial game setting including selection of all tower types, all enemy types, all levels configurations.
    
#### Game Engine
```
#### GameEngine
    public GameSceneObject execute();
    public void ConfigureGame();
    public void processActionRequest(Request request);
    public void createSprite();
    public void updateSound();
    
### GameStateObject
    public List<Sprite> getCurrentOnScreenEnemies();
    public List<Sprite> getCurrentOnScreenTowers(); 
    public List<Sprite> getCurrentOnScreenSprites;
    public int getCurrentScore();
    public int getCurrentWealth();
    
### Sprite
    public String getImagePath();
    public Point getPosition();
    public Object getImage();
    
```
      
#### Game Player
```
#### Starting the Game
public void startGame();
public String getXML();
public String setXML();
```

## Example games
Describe three example games from your genre in detail that differ significantly. Clearly identify how the functional differences in these games is supported by your design and enabled by your authoring environment. Use these examples to help clarify the abstractions in your design.
* [Bloons Tower Defense](https://ninjakiwi.com/Games/Tower-Defense/Play/Bloons-Tower-Defense.html)
    * Very basic representation of tower defense games. A line of balloons follows a clear path from one point on the screen to the other. A shop is always available where the player can buy towers to put on valid spots on the map. These towers automatically shoot projectiles at the balloons that destroy them. The player can buy upgrades to make these towers more powerful and makes it easier to pop balloons.
    * Our GAE plans to give the designer the power to define the path enemies take, as well as different kinds of enemies with different properties. It will allow the designer to determine valid spaces to place towers, so players may not be able to place towers on the path.
* Plants vs Zombies
    * Enemies move in straight lines down several lanes that span the screen. The player is able to place plants (towers) at most points in the enemy's path. When zombies approach the towers, they deal damage to the plant. When the health of the tower reaches 0, the plant disappears and the zombie continues down the path.
    * In this case, valid spaces for tower placement are all in the path of the enemies. Our game editor allows valid spaces to be placed in the enemies' defined path. Enemies and towers are essentially two different subtypes of the same object (sprite) and have the same attributes - rate of attack, health, damage, etc. As such, both enemies and towers will be able to deal damage to each other and be destroyed.
* [Onslaught 2](kongregate.com/games/gaby/onslaught2)
    * Game design is much like Bloons, but towers placed in proximity to each other unlock special abilities.
    * In theory, it's fairly straightforward to add more functionality in the backend. If we wanted to support the designer to implement something like this, we could tell each tower to keep track of its neighbors, which triggers a boolean that is kept as a property in the tower. On the designer side, when they create a new type of tower, they can click a tab called "Special Conditions" that allows them to define conditional actions taken by the sprite based on certain the state of certain properties. For example, if myNeighbors.size = 4, then strength * 2.

## Design Considerations
##### This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length and describe at least three alternatives in detail (including pros and cons from all sides of the discussion). Describe any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.

* Considered using an XML file that is parsed and updated throughout each timeframe. It was decided that using this approach would not be ideal because overwriting the file might lead to mistakes. We also wanted to differntiate the actual game state and the original setup to make live game editing easier. We decided to implement this in a way that the game setup would be encoded in an XML file, while the game state would be represented in a file that contains a serialized object from the Game Engine. This string could be deserialized back into an object in order to restore this game state.
* Discussed the possibility of instantiating the Game Player instance from within the GAE and including live game editing by creating new GAE instances every time the game player paused the game and chose to edit. We moved away from this because we did not want to create new instances of each object every time we were updating. Instead, we added a manager class, the Bus class, and it handles the back and forth between the Game Player class and the GAE.
* Discussed different possibilities for targeting enemies. We decided that we were going to have an interface for targeting enemies as they walk past the tower. The tower can't simply start shooting at the point a moving target is standing at at the beginning of a frame, because in the next frame, the enemy will be gone and no projectiles will connect. We considered making it so the enemies will take damage periodically when in range, and the animations would follow the enemy as it leaves. However, we decided instead to implement an interface that will allow the projectile to follow the cursor, because it does not require computing the changing distance between the tower and the moving enemy. We want to keep the game engine framework as simple and free of complicated logic as possible, so as not to hardcode any behaviors into the game. This will make it more extensible in the future, and gives more freedom to the game designer when they define behaviors for their objects.