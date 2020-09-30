package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
/**
 * Class: AdvanceToNextLevelAction
 * Purpose: Action that changes the game to the next level
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: A Game Condition can have a AdvanceToNextLevelAction as an action, and this will be added to the GameRulesController to be executed.
 * Other details: N/A
 */
public class AdvanceToNextLevelAction implements GameAction{
    /**
     * Purpose: Advances the game to the next level
     * Assumptions: N/A
     * @param game the game for which this action will to act upon
     */
    @Override
    public void execute(Game game) {
        game.loadNextLevel();
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
