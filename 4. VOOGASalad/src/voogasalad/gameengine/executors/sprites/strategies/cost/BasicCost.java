package voogasalad.gameengine.executors.sprites.strategies.cost;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.CostBuilder;

/**
 * Defines behavior for a sprite whose cost remains constant throughout the game
 */
public class BasicCost implements CostStrategy {

    private int myCreateCost;
    private int myDestroyCost;
    private CostBuilder myBuilder;

    /**
     * Constructs a BasicCost strategy
     * @param costBuilder a copy of the cost builder that holds the information to create a specific instance of a
     *                    BasicCost strategy
     */
     public BasicCost(CostBuilder costBuilder) {
         myBuilder = costBuilder;
         myCreateCost = myBuilder.getCreateCost();
         myDestroyCost = myBuilder.getDestroyCost();
     }

    /**
     * @return cost to player for creating this sprite
     */
    @Override
    public int getCreateCost() {
        return myCreateCost;
    }

    /**
     * @return cost returned to player for destroying this sprite
     */
    @Override
    public int getDestroyCost() {
        return myDestroyCost;
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of itself with the same cost strategy
     * @throws GameEngineException
     */
    @Override
    public CostStrategy makeClone() throws GameEngineException {
        return myBuilder.build() ;
    }
}
