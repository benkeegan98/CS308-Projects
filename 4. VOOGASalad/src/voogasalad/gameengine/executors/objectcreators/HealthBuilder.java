package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;

/**
 * Parses health parameters from the XML and builds a new cost strategy using those parameters.
 */
public class HealthBuilder implements StrategyBuilder {
    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.health.";
    public static final String DEFAULT_TYPE = "NoHealth";

    private int myHealthValue;
    private String myType;

    /**
     * Saves string type
     * @param type string that represents type of cost strategy to be built
     * @return self
     */
    public HealthBuilder setType(String type) {
        myType = type;
        return this;
    }

    /**
     * Parses string that represents the starting health of the sprite
     * @param healthValue string that represents the starting health of the sprite
     * @return self
     * @throws GameEngineException
     */
    public HealthBuilder setHealthValue(String healthValue) throws GameEngineException {
        try {
            myHealthValue = Integer.parseInt(healthValue);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteHealthInitializationFailed");
        }
        return this;
    }

    /**
     * @return type of cost strategy to be built
     */
    public String getType() {
        return myType;
    }

    /**
     * @return starting health of the sprite
     */
    public int getHealthValue() {
        return myHealthValue;
    }

    /**
     * Calls the constructor of the type it's meant to handle; uses default when no type is set
     * @return a strategy of the specified type with the parameters previously parsed
     * @throws GameEngineException
     */
    @Override
    public HealthStrategy build() throws GameEngineException {
        if (myType == null) {
            myType = DEFAULT_TYPE;
        }
        try {
            return (HealthStrategy) Class.forName(CLASS_PATH + myType).getConstructor(HealthBuilder.class).newInstance(this);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteHealthInitializationFailed");
        }
    }
}
