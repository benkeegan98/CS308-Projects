package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.HashSet;
import java.util.Set;
/**
 * Purpose: Chunks the health of all enemy sprites in a particular radius
 * Assumptions: N/A
 * Dependencies: N/A
 * Example of how to use: Can be combined with ChunkAreaEffect strategy from a sprite that affects other sprites
 * Other details: N/A
 */
public class ChunkAreaAction implements LevelAction {

    private int mySpriteId;
    private int myDamageValue;
    private double myRadius;
    private boolean isFinished;

    /**
     * Purpose: Creates the ChunkAreaAction
     * Assumptions: N/A
     * @param spriteId the id of the sprite who is at the center of the radius
     * @param damageValue the damage value of which enemies in the radius will be chunked by
     * @param radius the radius of covered
     */
    public ChunkAreaAction(int spriteId, int damageValue, double radius) {
        mySpriteId = spriteId;
        myDamageValue = damageValue;
        myRadius = radius;
        isFinished = false;
    }

    /**
     * Purpose: Executes the chunk area action on the sprites
     * Assumptions: N/A
     * @param level the level of which this action will be executed on
     */
    @Override
    public void execute(Level level) {
        Sprite root = level.getSpriteManager().getSpriteById(mySpriteId);
        if(checkValid(root)) {
            Set<Sprite> toChunk = new HashSet<>();
            toChunk.add(root);
            for(Sprite sprite : level.getSpriteManager().getOnScreenSpritesByArchetype(SpriteArchetype.ENEMY)) {
                if(root.distanceTo(sprite) <= myRadius) {
                    toChunk.add(sprite);
                }
            }
            for(Sprite sprite : toChunk) {
                sprite.chunkHealth(myDamageValue);
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

    private boolean checkValid(Sprite toCheck) {
        return !(toCheck == null || toCheck.isDead());
    }
}
