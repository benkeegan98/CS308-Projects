package voogasalad.gameengine.executors.control.action.game;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.configurators.ConditionsConfigurator;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.*;
/**
 * Class: GameSceneObject
 * Purpose: Action that edits a game condition
 * Assumptions: N/A
 * Dependencies: Depends on the ConditionsConfigurator
 * Example of how to use: Used by the ActionsProcessor to process and live game editing request on editing conditions
 * Other details: N/A
 */
public class EditGameConditionsAction implements GameAction{

    private NodeList myEditedConditionsList;

    /**
     * Purpose: Constructor for edit game conditions action
     * Assumptions: N/A
     * @param editableObjectRoot the root of the editable object, which, in this case, will hold the game condition
     */
    public EditGameConditionsAction(Element editableObjectRoot) {
        myEditedConditionsList = editableObjectRoot.getElementsByTagName("GameCondition");
    }

    /**
     * Purpose: Identifies and replaces old conditions in the game with the new edited conditions
     * Assumptions: N/A
     * @param game the game which this action will act upon
     * @throws GameEngineException if the new conditions provided fail to configure
     */
    @Override
    public void execute(Game game) throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        Collection<GameCondition> editedGameConditions = conditionsConfigurator.buildGameConditionsCollection(myEditedConditionsList);
        Collection<GameCondition> currentGameConditions = game.getAllGameConditions();
        Set<GameCondition> originalConditionsToRemove = new HashSet<>();
        for (GameCondition newCondition : editedGameConditions) {
            for (GameCondition oldCondition : currentGameConditions) {
                if (newCondition.getGameConditionId() == oldCondition.getGameConditionId()) {
                    originalConditionsToRemove.add(oldCondition);
                }
            }
        }
        currentGameConditions.removeAll(originalConditionsToRemove);
        currentGameConditions.addAll(editedGameConditions);
    }

    /**
     * Purpose: Allows the game to check if the action is finished to remove it
     * Assumptions: Assumes that this action will always act in the span of 1 frame
     * @return true (because it is a one-time action).
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
