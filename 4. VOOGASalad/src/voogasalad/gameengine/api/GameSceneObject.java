package voogasalad.gameengine.api;

import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelStatusManager;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.List;

/**
 * Class: GameSceneObject
 * Purpose: Contains all the objects that change in each new scene and their new positions
 * Assumptions: N/A
 * Dependencies: Depends on the LevelStatusManager, SpriteManager, and a background path
 * Example of how to use: Player can ask for resources, lives, and on-screen sprites whose values or positions/existence may have changed with each new scene.
 * Other details: There should never be getters for LevelStatusManager and SpriteManager as these are meant to be secured Level objects that the GameSceneObject acts as a wrapper for.
 */
public class GameSceneObject {
    private final LevelStatusManager myLevelStatusManager;
    private final SpriteManager mySpriteManager;
    private final String myBackgroundPath;

    /**
     * Purpose: Constructor for GameScene object
     * Assumptions: Assumes non-null values for backgroundPath, levelStatusManager, and spriteManager
     * @param backgroundPath the path for the current background
     * @param levelStatusManager the level status manager for the current level
     * @param spriteManager the sprite manager for the current level
     */
    public GameSceneObject(String backgroundPath, LevelStatusManager levelStatusManager, SpriteManager spriteManager) {
        myLevelStatusManager = levelStatusManager;
        mySpriteManager = spriteManager;
        myBackgroundPath = backgroundPath;
    }

    /**
     * Purpose: Gets the current resources in the level
     * Assumptions: N/A
     * @return the value of the current resources
     */
    public int getResources() {
        return myLevelStatusManager.getResources();
    }

    /**
     * Purpose: Gets the current number of lives in the level
     * Assumptions: N/A
     * @return the value of the current number of lives
     */
    public int getLives() {
        return myLevelStatusManager.getLives();
    }

    /**
     * Purpose: Gets the current score for the level
     * Assumptions: N/A
     * @return the value of the current score in the level
     */
    public int getLevelScore() { return myLevelStatusManager.getScore(); }

    /**
     * Purpose: Gets the List of on-screen sprites to display
     * Assumptions: N/A
     * @return a list of the current on-screen sprites to display
     */
    public List<Sprite> getOnScreenSprites() {
        return mySpriteManager.getCopyOnScreenSprites() ;
    }

    /**
     * Purpose: Gets the current background path
     * Assumptions: N/A
     * @return a String that represents the current background path in the display
     */
    public String getBackgroundPath() {
        return myBackgroundPath;
    }
}
