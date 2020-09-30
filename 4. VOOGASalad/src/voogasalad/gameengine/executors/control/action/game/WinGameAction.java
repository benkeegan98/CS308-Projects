package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
/**
 * Class: WinGameAction
 * Purpose: Changes the status of the game to game
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Can be used after a condition is checked (e.g., When there are no levels left, win game)
 * Other details: N/A
 */
public class WinGameAction implements GameAction {
    /**
     * Purpose: Changes the status of the game to win
     * Assumptions: N/A
     * @param game The game for which this action will be acted on
     */
    @Override
    public void execute(Game game) {
        game.setGameStatus(Status.WON);
    }

    /**
     * Purpose: Allows the game to poll whether the action across different frames to see if it is finished
     * Assumptions: N/A
     * @return a boolean that signifies whether or not the action has finished
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
