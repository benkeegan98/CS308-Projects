package voogasalad.gameengine.executors.control.levelcontrol;

import voogasalad.gameengine.executors.control.action.level.LevelAction;

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
public class LevelActionsRequester {
    Set<LevelAction> myRequestedActions;

    /**
     * Purpose:
     * Assumptions:
     */
    public LevelActionsRequester() {
        myRequestedActions = new HashSet<>();
    }

    /**
     * Purpose:
     * Assumptions:
     * @param action
     */
    public void requestAction(LevelAction action) {
        myRequestedActions.add(action);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Set<LevelAction> getRequestedActions() {
        Set<LevelAction> returnSet = myRequestedActions;
        myRequestedActions = new HashSet<>();
        return returnSet;
    }
}
