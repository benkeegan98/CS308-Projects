package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.configurators.ConditionsConfigurator;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.List;
/**
 * Class: DoNothingActionForTest
 * Purpose: Action that does nothing for test purpose
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: This can be used when wanting to check if a condition is necessary to test but no action wants to be taken
 * Other details: N/A
 */
public class DoNothingActionForTest implements GameAction{

    /**
     * Purpose: Does nothing to the game
     * Assumptions: N/A
     * @param game the game for which the action will act upon
     */
    @Override
    public void execute(Game game) throws GameEngineException {
    }

    /**
     * Purpose: Allows the game to check if the action is finished to remove it
     * Assumptions: Assumes that this action will always act in the span of 1 frame
     * @return true (because it is a one-time action).
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
