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
public class LoseLevelAction implements LevelAction {
    /**
     * Purpose:
     * Assumptions:
     * @param level
     */
    @Override
    public void execute(Level level) {
        level.getStatusManager().setGameSceneStatus(Status.LOST);
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
