package voogasalad.gameengine.executors.control.gamecontrol;

import voogasalad.gameengine.executors.control.action.game.GameAction;

import java.util.HashSet;
import java.util.Set;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class GameActionsRequester {
    Set<GameAction> myRequestedActions;

    public GameActionsRequester() {
        myRequestedActions = new HashSet<>();
    }

    /**
     * Purpose:
     * Assumptions:
     * @param action
     */
    public void requestAction(GameAction action) {
        myRequestedActions.add(action);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Set<GameAction> getRequestedActions() {
        Set<GameAction> returnSet = myRequestedActions;
        myRequestedActions = new HashSet<>();
        return returnSet;
    }
}
