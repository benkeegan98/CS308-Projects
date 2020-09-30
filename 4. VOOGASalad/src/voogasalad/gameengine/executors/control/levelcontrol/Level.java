package voogasalad.gameengine.executors.control.levelcontrol;

import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelConditionsManager;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelStatusManager;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelWaveManager;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelActionsManager;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.Set;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class Level implements GameScene {

    private SpriteManager mySpriteManager;
    private LevelWaveManager myLevelWaveManager;
    private LevelStatusManager myLevelStatusManager;
    private LevelConditionsManager myLevelConditionsManager;
    private LevelActionsManager myLevelActionsManager;
    private int myLevelId;
    private String myBackgroundPath;
    private String mySoundPath;
    private LevelActionsRequester myActionsRequester;

    /**
     * Purpose:
     * Assumptions:
     * @param levelBuilder
     */
    public Level(LevelBuilder levelBuilder) {
        myLevelId = levelBuilder.getLevelId();
        mySpriteManager = levelBuilder.getSpriteManager();
        myLevelWaveManager = levelBuilder.getWaveManager();
        myLevelStatusManager = levelBuilder.getStatusManager();
        myLevelConditionsManager = levelBuilder.getConditionsManager();
        myLevelActionsManager = levelBuilder.getActionsManager();
        myBackgroundPath = levelBuilder.getBackgroundPath();
        mySoundPath = levelBuilder.getSoundPath();
        myActionsRequester = levelBuilder.getLevelActionsRequester();
    }

    /**
     * Purpose:
     * Assumptions:
     * @param elapsedTime
     * @return
     * @throws GameEngineException
     */
    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        mySpriteManager.executeSpriteNextState(elapsedTime);
        myLevelStatusManager.notifyNewCycle(elapsedTime);
        myLevelActionsManager.addLevelActionsAsCollection(myActionsRequester.getRequestedActions());
        myLevelActionsManager.addLevelActionsAsCollection(myLevelConditionsManager.getLevelActionsToExecute(this));
        myLevelActionsManager.executeLevelActions(this);
        return new GameSceneObject(myBackgroundPath, myLevelStatusManager.getCopyOfStatusManager(), mySpriteManager);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String getBackgroundPath() {
        return myBackgroundPath;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public String getSoundPath() { return mySoundPath; }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public LevelActionsRequester getActionsRequester() {
        return myActionsRequester;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public LevelWaveManager getWaveManager() {
        return myLevelWaveManager;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public LevelStatusManager getStatusManager() {
        return myLevelStatusManager;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getLevelId() {
        return myLevelId;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getCurrentScore() {
        return myLevelStatusManager.getScore();
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Set<LevelCondition> getLevelConditions() {
        return myLevelConditionsManager.getLevelConditions();
    }
}
