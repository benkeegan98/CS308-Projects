package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.cost.CostStrategy;

import java.lang.reflect.InvocationTargetException;

/**
 * Parses cost parameters from the XML and builds a new cost strategy using those parameters.
 */
public class CostBuilder implements StrategyBuilder{

    public static final String DEFAULT_TYPE = "BasicCost";
    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.cost.";

    private int myCreateCost;
    private int myDestroyCost;
    private String myType;


    /**
     * Sets create cost and destroy cost to the same value
     * @param cost string that represents the base cost of creating and destroying a sprite
     * @return self
     * @throws GameEngineException
     */
    public CostBuilder setCost(String cost) throws GameEngineException {
        setCreateCost(cost);
        setDestroyCost(cost);
        return this;
    }

    /**
     * Saves string that represents type of cost strategy to be built
     * @param type string that represents type of cost strategy to be built
     * @return self
     */
    public CostBuilder setType(String type) {
        myType = type;
        return this;
    }

    /**
     * Parses cost string. Saved as create cost
     * @param cost string that represents the cost to the player for creating the sprite
     * @return
     * @throws GameEngineException
     */
    public CostBuilder setCreateCost(String cost) throws GameEngineException {
        try {
            myCreateCost = Integer.parseInt(cost);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteCostStrategyInitializationFailed");
        }
        return this;
    }

    /**
     * Parses cost string. Saved as destroy cost
     * @param cost string that represents the cost returned to the player after destroying the sprite
     * @return self
     * @throws GameEngineException
     */
    public CostBuilder setDestroyCost(String cost) throws GameEngineException {
        try {
            myDestroyCost = Integer.parseInt(cost);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteCostStrategyInitializationFailed");
        }
        return this;
    }

    /**
     * @return current set create cost; no default is set
     */
    public int getCreateCost() {
        return myCreateCost;
    }

    /**
     * @return current set destroy cost; no default is set
     */
    public int getDestroyCost() {
        return myDestroyCost;
    }

    /**
     * Calls the constructor of the type it's meant to handle; uses default when no type is set
     * @return a strategy of the specified type with the parameters previously parsed
     * @throws GameEngineException
     */
    @Override
    public CostStrategy build() throws GameEngineException {
        if (myType == null) {
            myType = DEFAULT_TYPE;
        }
        try {
            return (CostStrategy) Class.forName(CLASS_PATH + myType).getConstructor(CostBuilder.class).newInstance(this);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteCostStrategyInitializationFailed");
        }
    }
}
