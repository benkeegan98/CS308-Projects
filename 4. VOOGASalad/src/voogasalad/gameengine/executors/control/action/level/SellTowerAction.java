package voogasalad.gameengine.executors.control.action.level;

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
public class SellTowerAction implements LevelAction {

    private double myXPos;
    private double myYPos;

    /**
     * Purpose:
     * Assumptions:
     * @param xpos
     * @param yPos
     */
    public SellTowerAction(double xpos, double yPos) {
        myXPos = xpos;
        myYPos = yPos;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     */
    @Override
    public void execute(Level level) {
        Sprite removed = level.getSpriteManager().removeSpriteTowerByCoordinates(myXPos, myYPos);
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
