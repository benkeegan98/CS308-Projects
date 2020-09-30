package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Status;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class WinLevelAction implements LevelAction {
    /**
     * Purpose:
     * Assumptions:
     * @param level
     */
    @Override
    public void execute(Level level) {
        level.getStatusManager().setGameSceneStatus(Status.WON);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
