package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class RemoveSpriteAction implements LevelAction{
    private int mySpriteId;

    /**
     * Purpose:
     * Assumptions:
     * @param spriteId
     */
    public RemoveSpriteAction(int spriteId) {
        mySpriteId = spriteId;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite removed = level.getSpriteManager().removeSpriteById(mySpriteId);
        level.getStatusManager().alterResourcesByValue(removed.getDestroyCost());
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
