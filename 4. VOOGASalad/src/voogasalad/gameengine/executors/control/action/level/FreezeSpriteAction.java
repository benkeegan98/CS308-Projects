package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Purpose: Freezes sprite's movement for given amount of time
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Can be combined with FreezeSpriteEffect from a sprite that affects other sprites
 * Other details: N/A
 */
public class FreezeSpriteAction implements LevelAction {

    private int mySpriteId;
    private int myDamageValue;
    private double myDuration;
    private boolean isFinished;

    /**
     * Purpose: Creates the FreezeSpriteAction
     * Assumptions: N/A
     * @param spriteId ID of sprite to be affected
     * @param damageValue value to be decremented from sprite's health
     * @param duration duration to freeze sprite's movement
     */
    public FreezeSpriteAction(int spriteId, int damageValue, double duration) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        myDuration = duration;
        isFinished = false;
    }

    /**
     * Purpose: Execute the desired freeze sprite action, chunking sprite health and freeze its movement for a given duration
     * Assumptions: N/A
     * @param level level on which to execute action
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite toAct = level.getSpriteManager().getSpriteById(mySpriteId);
        if(!(toAct == null || toAct.isDead())) {
            toAct.chunkHealth(myDamageValue);
            if(!toAct.isDead()) {
                toAct.delayMovement(myDuration);
            }
        }
        isFinished = true;
    }

    /**
     * Purpose: Allows the level to poll the action to see if it has completed
     * Assumptions: N/A
     * @return the isFinished value of the action set after the action has finished acting
     */
    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
