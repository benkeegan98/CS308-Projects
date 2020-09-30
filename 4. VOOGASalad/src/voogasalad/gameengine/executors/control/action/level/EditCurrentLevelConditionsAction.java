package voogasalad.gameengine.executors.control.action.level;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.configurators.ConditionsConfigurator;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * Purpose: Alter current level's active condition objects
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Instantiate EditCurrentLevelConditionsAction with XML element encoding for a condition object, then offer to LevelActionsRequester
 * Other details: N/A
 */
public class EditCurrentLevelConditionsAction implements LevelAction {

    private NodeList myEditedConditionsList;

    /**
     * Purpose: Create EditCurrentLevelConditionsAction
     * Assumptions: N/A
     * @param editableObjectRoot XML object encoding for a condition object
     */
    public EditCurrentLevelConditionsAction(Element editableObjectRoot) {
        myEditedConditionsList = editableObjectRoot.getElementsByTagName("Condition");
    }

    /**
     * Purpose: Execute edit curent level action, replacing active conditions with the condition object passed in construction
     * Assumptions: N/A
     * @param level level on which the action is to be executed on
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        Collection<LevelCondition> editedGameConditions = conditionsConfigurator.buildLevelConditionsCollection(myEditedConditionsList);
        Collection<LevelCondition> currentLevelConditions = level.getLevelConditions();
        Set<LevelCondition> originalConditionsToRemove = new HashSet<>();
        for (LevelCondition newCondition : editedGameConditions) {
            for (LevelCondition oldCondition : currentLevelConditions) {
                if (newCondition.getLevelConditionId() == oldCondition.getLevelConditionId()) {
                    originalConditionsToRemove.add(oldCondition);
                }
            }
        }
        currentLevelConditions.removeAll(originalConditionsToRemove);
        currentLevelConditions.addAll(editedGameConditions);
    }

    /**
     * Purpose: Allows the level to poll the action to see if it has completed
     * Assumptions: N/A
     * @return the isFinished value of the action set after the action has finished acting
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
