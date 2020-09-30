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
public class LivesCountCondition extends LevelCondition {

    public static final int DEFAULT_MARKED_COUNT = 0;
    public static final String MARKED_COUNT_MAP_KEY = "MarkedCount";

    private int myMarkedCount;

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param markedCount
     * @param conditionClassification
     * @param actions
     */
    public LivesCountCondition(int levelConditionId, int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(levelConditionId, conditionClassification, actions);
        myMarkedCount = markedCount;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param parameters
     * @param actions
     */
    public LivesCountCondition(int levelConditionId, Map<String, String> parameters, Set<LevelAction> actions) {
        super(levelConditionId, parameters, actions);
        try {
            myMarkedCount = Integer.parseInt(parameters.get(MARKED_COUNT_MAP_KEY));
        } catch (NullPointerException | NumberFormatException e) {
            myMarkedCount = DEFAULT_MARKED_COUNT;
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
        return level.getStatusManager().getLives()==myMarkedCount;
    }
}
