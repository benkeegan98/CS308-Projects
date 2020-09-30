package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public interface LevelAction {
    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @throws GameEngineException
     */
    void execute(Level level) throws GameEngineException;

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    boolean isFinished();
}
