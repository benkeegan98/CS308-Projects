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
public class EnemiesClearedCondition extends LevelCondition {
    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param classification
     * @param actions
     */
    public EnemiesClearedCondition(int levelConditionId, ConditionClassification classification, Set<LevelAction> actions) {
        super(levelConditionId, classification, actions);
    }

    /**
     * Purpose:
     * Assumptions:
     * @param levelConditionId
     * @param parameters
     * @param actions
     */
    public EnemiesClearedCondition(int levelConditionId, Map<String, String> parameters, Set<LevelAction> actions) {
        super (levelConditionId, parameters, actions);
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @return
     */
    @Override
    public boolean hasHappened(Level level) {
        System.out.println("TEST ENEMIES CLEARED:" + ((! level.getWaveManager().hasNextWave())) + " " + (level.getSpriteManager().getOnScreenSpritesByArchetype(SpriteArchetype.ENEMY).size()==0));
        return ((! level.getWaveManager().hasNextWave()) && level.getSpriteManager().getOnScreenSpritesByArchetype(SpriteArchetype.ENEMY).size()==0);
    }
}
