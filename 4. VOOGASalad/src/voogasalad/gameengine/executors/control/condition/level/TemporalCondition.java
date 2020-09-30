package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;

import java.util.Map;
import java.util.Set;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class TemporalCondition extends LevelCondition {

    public static final String MARKED_TIME_MAP_KEY = "MarkedTime";
    public static final int DEFAULT_MARKED_TIME = 0;

    private double myMarkedTime;

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param markedTime
     * @param conditionClassification
     * @param actions
     */
    public TemporalCondition(int levelConditionId, double markedTime, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(levelConditionId, conditionClassification, actions);
        myMarkedTime = markedTime;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param parameters
     * @param actions
     */
    public TemporalCondition(int levelConditionId, Map<String, String> parameters, Set<LevelAction> actions) {
        super(levelConditionId, parameters, actions);
        try {
            myMarkedTime = Integer.parseInt(parameters.get(MARKED_TIME_MAP_KEY));
        } catch (NullPointerException | NumberFormatException e) {
            myMarkedTime = DEFAULT_MARKED_TIME;
        }
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @return
     */
    @Override
    public boolean hasHappened(Level level) {
        if (level.getStatusManager().getTotalElapsedTime() >= myMarkedTime) {
            System.out.println("Temporal condition happened" + level.getStatusManager().getTotalElapsedTime());
        }
        return level.getStatusManager().getTotalElapsedTime() >= myMarkedTime;
    }
}
