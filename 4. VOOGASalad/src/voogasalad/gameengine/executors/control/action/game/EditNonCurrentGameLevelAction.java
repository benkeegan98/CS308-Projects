package voogasalad.gameengine.executors.control.action.game;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.configurators.LevelConfigurator;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.ArrayList;
import java.util.List;
/**
 * Class: EditNonCurrentGameLevel
 * Purpose: Edits a level in the game that is not the current one that is active
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Used by the ActionsProcessor to process and live game editing request on editing game levels
 * Other details: N/A
 */
public class EditNonCurrentGameLevelAction implements GameAction {

    private NodeList myEditedLevelsList;

    /**
     * Purpose: Constructor for EditNonCurrentGameLevelAction for editing a non-currently-active level
     * Assumptions: N/A
     * @param editableObjectRoot the EditableObjectRoot, which, in this case, is the Level being edited
     */
    public EditNonCurrentGameLevelAction(Element editableObjectRoot) {
        myEditedLevelsList = editableObjectRoot.getElementsByTagName("Level");
    }

    /**
     * Purpose: Identifies the levels that needs to be replaced and replaces them from the game
     * Assumptions: Assumes dependency on LevelConfigurator
     * @param game the game for which this action will be acted upon
     * @throws GameEngineException when the new levels fail to configure
     */
    @Override
    public void execute(Game game) throws GameEngineException {
        LevelConfigurator levelConfigurator = new LevelConfigurator();
        List<Level> editedLevels = levelConfigurator.configureLevels(myEditedLevelsList, game.getCompletePrototypesCollection());
        List<Level> currentGameLevels = game.getLevels();
        List<Level> originalLevelsToRemove = new ArrayList<>();
        for (Level newLevel : editedLevels) {
            for (Level oldLevel : currentGameLevels) {
                if (newLevel.getLevelId() == oldLevel.getLevelId()) {
                    originalLevelsToRemove.add(oldLevel);
                }
            }
        }
        currentGameLevels.removeAll(originalLevelsToRemove);
        currentGameLevels.addAll(editedLevels);
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
