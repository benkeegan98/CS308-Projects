package voogasalad.gameengine.executors.control.levelcontrol;

import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public interface GameScene {
    /**
     * Purpose:
     * Assumptions:
     * @param elapsedTime
     * @return
     * @throws GameEngineException
     */
    GameSceneObject execute(double elapsedTime) throws GameEngineException;
}
