package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
/**
 * Purpose: Alters the score of a level by a value
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Can be combined with the destruction of a sprite, which yields a numeric score to add to the level
 * Other details: N/A
 */
public class AlterScoreAction implements LevelAction{

    private int myValue;

    /**
     * Purpose: Creates AlterScoreAction to alter the score of a level
     * Assumptions: N/A
     * @param value the score value of which the level will be altered by
     */
    public AlterScoreAction(int value) {
        myValue = value;
    }

    /**
     * Purpose: Executes the level score altering action
     * Assumptions: N/A
     * @param level the level of which this action will be executed on
     */
    @Override
    public void execute(Level level) {
        level.getStatusManager().alterScoreByValue(myValue);
    }

    /**
     * Purpose: Allows the level to poll to check if the action has finished
     * Assumptions: N/A
     * @return true because this is a 1-frame action
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
