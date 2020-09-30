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
public abstract class LevelCondition {
    public static final String CONDITION_CLASSIFICATION_MAP_KEY = "Classification";
    public static final ConditionClassification DEFAULT_CONDITION_CLASSIFICATION = ConditionClassification.ONETIME;

    private Set<LevelAction> myActions;
    private ConditionClassification myConditionClassification;
    private int myLevelConditionId;

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param classification
     * @param actions
     */
    public LevelCondition(int levelConditionId, ConditionClassification classification, Set<LevelAction> actions) {
        myActions = actions;
        myConditionClassification = classification;
        myLevelConditionId = levelConditionId;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param parameters
     * @param actions
     */
    public LevelCondition(int levelConditionId, Map<String, String> parameters, Set<LevelAction> actions) {
        try {
            myConditionClassification = ConditionClassification.valueOf(parameters.get(CONDITION_CLASSIFICATION_MAP_KEY));
        } catch (NullPointerException | IllegalArgumentException e) {
            myConditionClassification = DEFAULT_CONDITION_CLASSIFICATION;
        }
        myActions = actions;
        myLevelConditionId = levelConditionId;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public ConditionClassification getClassification() {
        return myConditionClassification;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Set<LevelAction> getLevelActions() {
        return myActions;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @return
     */
    public abstract boolean hasHappened(Level level);

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getLevelConditionId() {
        return myLevelConditionId;
    }
}
