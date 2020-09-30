package voogasalad.gameengine.executors.control.action;

import org.w3c.dom.Element;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class EditPrototypeImageViewAction implements GameAction, LevelAction {

    private int myPrototypeId;
    private String myImageViewPath;
    private boolean levelUpdateFinished;
    private boolean gameUpdateFinished;

    /**
     * Purpose:
     * Assumptions:
     * @param editableObjectRoot
     */
    public EditPrototypeImageViewAction(Element editableObjectRoot) {
        myPrototypeId = Integer.parseInt(editableObjectRoot.getElementsByTagName("PrototypeId").item(0).getTextContent());
        myImageViewPath = editableObjectRoot.getElementsByTagName("EditedField").item(0).getTextContent();
    }

    /**
     * Purpose:
     * Assumptions:
     * @param game
     * @throws GameEngineException
     */
    @Override
    public void execute(Game game) throws GameEngineException {
        List<Sprite> gamePrototypes = game.getCompletePrototypesCollection();
        updateSprites(gamePrototypes);
        gameUpdateFinished = true;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        List<Sprite> onScreenSprites = level.getSpriteManager().getOnsScreenSprites();
        updateSprites(onScreenSprites);
        levelUpdateFinished = true;
    }

    private void updateSprites(List<Sprite> spritesList) {
        for (Sprite sprite : spritesList) {
            if (sprite.getPrototypeId()== myPrototypeId) {
                sprite.updateImage(myImageViewPath);
            }
        }
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return levelUpdateFinished && gameUpdateFinished;
    }
}
