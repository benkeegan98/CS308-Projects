package voogasalad.gameengine.executors.sprites.strategies.cost;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

/**
 * Outlines cost behavior of a certain sprite. Must exist within an initialized sprite.
 */
public interface CostStrategy {
    /**
     * @return cost to player for creating this sprite
     */
    int getCreateCost();

    /**
     * @return cost returned to player from destroying this sprite
     */
    int getDestroyCost();

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of itself with the same cost strategy
     * @throws GameEngineException
     */
    CostStrategy makeClone() throws GameEngineException;
}
