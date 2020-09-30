package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
/**
 * Purpose: Alter the number of lives on the level
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Can be used on a condition of enemies finishing their movement to decrease a life
 * Other details: N/A
 */
public class AlterResourcesAction implements LevelAction{

    private int myValue;

    /**
     * Purpose: Creates the AlterResourcesAction for a level
     * Assumptions: N/A
     * @param value the value of which to alter the level's resources for
     */
    public AlterResourcesAction(int value) {
        myValue = value;
    }

    /**
     * Purpose: Executes the resources altering action on the level given
     * Assumptions: N/A
     * @param level the level of which this action will be executed on
     */
    @Override
    public void execute(Level level) {
        level.getStatusManager().alterResourcesByValue(myValue);
    }

    /**
     * Purpose: Allows the level to poll if this action has finished
     * Assumptions: N/A
     * @return true because this action will always take 1 frame
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
