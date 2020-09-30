package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class TickHealthAction implements LevelAction {

    private int mySpriteId;
    private int myDamageValue;
    private double myTickDelay;
    private int myMaxTick;
    private int ticksRemaining;
    private boolean isFinished;
    private double timeSinceLastTick;

    /**
     * Purpose:
     * Assumptions:
     * @param spriteId
     * @param damageValue
     * @param tickCount
     * @param tickDelay
     */
    public TickHealthAction(int spriteId, int damageValue, int tickCount, double tickDelay) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        myMaxTick = tickCount;
        ticksRemaining = tickCount;
        isFinished = false;
        timeSinceLastTick = 0;
        myTickDelay = tickDelay;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite toAct = level.getSpriteManager().getSpriteById(mySpriteId);
        if(toAct == null || toAct.isDead()) {
            isFinished = true;
        } else if(tickNow(level.getStatusManager().getElapsedTimeSinceLastFrame())) {
            toAct.chunkHealth(myDamageValue);
            ticksRemaining -= 1;
            if(ticksRemaining <= 0) {
                isFinished = true;
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
        return isFinished;
    }

    private boolean tickNow(double elapsedTime) {
        if(ticksRemaining == myMaxTick) {
            return true;
        }
        timeSinceLastTick += elapsedTime;
        if(timeSinceLastTick >= myTickDelay) {
            timeSinceLastTick -= myTickDelay;
            return true;
        } else {
            return false;
        }
    }
}
