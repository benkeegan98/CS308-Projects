# Project Plan

## Mid-point Demo Goals by Module

### Gaphical Authoring Environment (GAE)
* Complete use cases unrelated to Live Game Editing
* Export an XML file with game setup 

### Game Player
* Make the tower popup visible
* Make the obstacle popup visible
* Allow the user to start and pause


### Game Engine
* Collisions work between towers, projectiles, and enemies
* GameManager can start and end games, start and end levels
* Death works for at least enemies
* Player should be able to place towers on map



## Individual Responsibilities by Module

### Graphical Authoring Environment (GAE)

* Marc Jabbour (mj196)
    * Create .properties files for level attributes
    * Build all aspects of the GAE GUI used to set level attributes (Level Use Cases)
    * Create an Exception class for level-related user input
    * Institute error checking for level-related user input
    * Work on class that saves user configurations after GAE is configured by designer
* Amber Johnson (ajj18) 
    * Create .properties files for enemy attributes
    * Build all aspects of the GAE GUI used to set enemy attributes (Enemy Use Cases)
    * Create a class to output the XML file
    * Create an Exception Interface
    * Create an Exception class for enemy-related user input
    * Institute error checking for enemy-related user input
* Xiaoyang Liu (xl149)
    * Create .properties files for tower attributes
    * Build all aspects of the GAE GUI used to set tower attributes (Tower Use Cases)
    * Create display class to house level, enemy, and tower user input fields
    * Create an Exception class for tower-related user input
    * Institute error checking for tower-related user input

## Game Player

* Sumer Vardhan (sv110)
    * Visualise game from XML file and Serializable Objects

* Michael Castro (mc546), Ben Keegan (bk142)
    * Build right pane used to select and upgrade towers and obstacles, as well as drag and drop feature
    * Create game loop that can start the animation of enemies spawning and following the path as well as tower projectile/attack animations


## Game Engine

* Ha Nguyen (hn47)
    * Parsing CollisionMap that determines the kinds of collisions that occur between all possible combinations of sprites
    * Create methods that deal with the consequences of collision, such as `takeDamage`
    * Handle user input so that the user can buy and place towers on the screen
* Emily Otero (eo56)
    * Create GameManager and LevelManager that handles the behaviors of Sprites that exist in the game currently
    * Create Money class that is used to buy and sell towers throughout the game
    * Create Tower classes and all instances of tower states being changed and updated
* Christopher Xu ()
    * Create Sprite class and its subclass that represents the different actors in every level
    * Write HandleDeath which handles the consequences of a sprite's death on the game and removes it from the level
    * Handle map of objects passed in from the XML file and parse them into sprite objects 