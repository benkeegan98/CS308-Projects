package voogasalad.gameengine.api;

import org.w3c.dom.Document;
import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;

/**
 * Class: Engine
 * Purpose: Provides and API for the Player to generally communicates with the Engine and gets any necessary object that
 * is not reloaded at every game scene. Currently, the Engine merely acts as a wrapper for the Game; however, the separation
 * also allows for more games at once later on.
 * Assumptions: Assumes only one Game at this time (however, multiple Game functionality can be easily added).
 * Dependencies: Dependent on the Game class as this is mostly the API/the GameController that delegates actions to Games.
 * Example of how to use: Player can poll the engine for game level switches and then get the new level background and sound
 * background path to load once and not have to re-load at every single game scene.
 * Other details: N/A
 */
public class Engine {
    private Game myGame;

    /**
     * Purpose: Constructor for the Engine
     * Assumptions: N/A
     * @param gameDocument the document to configure the game
     * @throws GameEngineException if game fails to configure
     */
    public Engine(Document gameDocument) throws GameEngineException {
        myGame = new Game(gameDocument);
    }

    /**
     * Purpose: Serves as a general method for the Player to call the engine to execute the next game scene
     * Assumptions: N/A
     * @param elapsedTime the time elapsed since last call of execution
     * @return the current GameSceneObject that holds everything that changes from game scene to game scene
     * @throws GameEngineException if the game fails to execute the next scene
     */
    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        return myGame.execute(elapsedTime);
    }

    /**
     * Purpose: Gets the action processor for the current game
     * Assumptions: Only one action processor for the current engine and game at the moment
     * @return the current ActionsProcessor for the game so the player can make requests
     */
    public ActionsProcessor getActionsProcessor() {
        return myGame.getActionsProcessor();
    }

    /**
     * Purpose: Gets a copy of current active sprite prototypes by archetype
     * Assumptions: N/A
     * @param spriteArchetype the archetype of the prototypes desired
     * @return a List (copy) of the sprite prototypes of this archetype currently active
     * @throws GameEngineException if game fails to create copy list of prototypes
     */
    public List<Sprite> getSpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myGame.getCopySpritePrototypesByArchetype(spriteArchetype);
    }

    /**
     * Purpose: Gets the background path for the current level
     * Assumptions: N/A
     * @return the String path for the current level's background
     */
    public String getCurrentLevelBackgroundPath() {
        return myGame.getCurrentLevelBackgroundPath();
    }

    /**
     * Purpose: Allows the player to poll the engine for level switches to reload level-specific displays
     * Assumptions: N/A
     * @return a boolean to represent whether the level switched
     */
    public boolean didLevelSwitch() {
        return myGame.didLevelSwitch();
    }

    /**
     * Purpose: Gets the total current score of the game across all levels
     * Assumptions: N/A
     * @return the total game score value
     */
    public int getCurrentTotalGameScore() {
        return myGame.getCurrentTotalGameScore();
    }

    /**
     * Purpose: Gets the title of the game as configured in the XML
     * Assumptions: N/A
     * @return the game title
     */
    public String getGameTitle() {
        return myGame.getGameTitle();
    }

    /**
     * Purpose: Gets the id of the current level (for live game editing) because an edit on the current, playing level
     * is very different from an edit on a non-active level.
     * Assumptions: N/A
     * @return the current level id
     */
    public int getCurrentLevelId() {
        return myGame.getCurrentLevelId();
    }

    /**
     * Purpose: Gets the path for the current soundtrack to be played
     * Assumptions: N/A
     * @return the path for the current sound track
     */
    public String getCurrentLevelSoundPath() { return myGame.getCurrentLevelSoundPath(); }

    /**
     * Purpose: Get the current game status in order for the engine to reload game-specific displays
     * Assumptions: N/A
     * @return the status of the current game
     */
    public Status getGameStatus() {
        return myGame.getGameStatus();
    }

}