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
public class AlterLivesAction implements LevelAction {
    private int myValue;

    /**
     * Purpose: Constructor for action to alter lives on a level
     * Assumptions: N/A
     * @param value the number of lives to add/subtract to the level
     */
    public AlterLivesAction(int value) {
        myValue = value;
    }

    /**
     * Purpose: Executes the action of altering live son a level
     * Assumptions: N/A
     * @param level the level of which this action will act upon
     */
    @Override
    public void execute(Level level) {
        level.getStatusManager().alterLivesByValue(myValue);
    }

    /**
     * Purpose: Allows the level to poll the action to see if it is finished
     * Assumptions: N/A
     * @return true because this action only takes 1 frame to execute
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
