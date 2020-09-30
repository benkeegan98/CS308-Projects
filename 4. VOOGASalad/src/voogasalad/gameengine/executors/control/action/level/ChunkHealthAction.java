package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Purpose: Chunks health of Sprite based on spriteID
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Can be combined with ChunkHealthEffect strategy from a sprite that affects other sprites
 * Other details: N/A
 */
public class ChunkHealthAction implements LevelAction {
    private int mySpriteId;
    private int myDamageValue;
    private boolean isFinished;

    /**
     * Purpose: Creates the ChunkHealthAction
     * Assumptions: N/A
     * @param spriteId the id of the sprite to be affected
     * @param damageValue the value to decrement sprite health by
     */
    public ChunkHealthAction(int spriteId, int damageValue) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        isFinished = false;
    }

    /**
     * Purpose: Executes the chunk health action on the indicated sprite
     * Assumptions:
     * @param level the level on which this action is acting on
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite toAct = level.getSpriteManager().getSpriteById(mySpriteId);
        if(!(toAct == null || toAct.isDead())) {
            toAct.chunkHealth(myDamageValue);
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
