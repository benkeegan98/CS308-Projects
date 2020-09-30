package voogasalad.gameengine.executors.control.condition.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.condition.ConditionClassification;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

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
public class OnscreenArchetypeCountCondition extends LevelCondition {

    public static final String ARCHETYPE_MAP_KEY = "Archetype";
    public static final SpriteArchetype DEFAULT_ARCHETYPE = SpriteArchetype.UNCLASSIFIED;
    public static final String MARKED_COUNT_MAP_KEY = "MarkedCount";
    public static final int DEFAULT_MARKED_COUNT=0;

    private int myMarkedCount;
    private SpriteArchetype myArchetype;

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param archetype
     * @param markedCount
     * @param conditionClassification
     * @param actions
     */
    public OnscreenArchetypeCountCondition(int levelConditionId, SpriteArchetype archetype, int markedCount, ConditionClassification conditionClassification, Set<LevelAction> actions) {
        super(levelConditionId, conditionClassification, actions);
        myArchetype = archetype;
        myMarkedCount = markedCount;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param parameters
     * @param actions
     */
    public OnscreenArchetypeCountCondition(int levelConditionId, Map<String, String> parameters, Set<LevelAction> actions) {
        super(levelConditionId, parameters, actions);
        try {
            myArchetype = SpriteArchetype.valueOf(parameters.get(ARCHETYPE_MAP_KEY));
        } catch (NullPointerException | IllegalArgumentException e) {
            myArchetype = DEFAULT_ARCHETYPE;
        }
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
        System.out.println("ON SCREEN COUNT" + level.getSpriteManager().getOnScreenSpritesByArchetype(myArchetype).size());
        return level.getSpriteManager().getOnScreenSpritesByArchetype(myArchetype).size() == myMarkedCount;
    }
}
