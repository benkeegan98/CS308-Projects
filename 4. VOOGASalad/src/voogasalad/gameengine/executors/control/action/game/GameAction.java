package voogasalad.gameengine.executors.control.action.game;

import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
/**
 * Class: GameAction interface
 * Purpose: Actions that will act on the game
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: ActionRequester can either request an action of the action can be attached to a condition, and
 * the game will process these actions to be acted upon
 * Other details: N/A
 */
public interface GameAction {
    /**
     * Purpose: Executes the appropriate actions specified by this GameAction to the game
     * Assumptions: N/A
     * @param game the game that the action in question will act upon
     * @throws GameEngineException
     */
    void execute(Game game) throws GameEngineException;

    /**
     * Purpose: Allows the game to poll the action to see if it is finished in order to remove it if the action happens to span across multiple levels
     * Assumptions: N/A
     * @return the boolean value that represents the state of the action
     */
    boolean isFinished();
}
