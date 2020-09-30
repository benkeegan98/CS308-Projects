package voogasalad.gameengine.executors.sprites.strategies.health;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.HealthBuilder;

/**
 * A health strategy for a sprite that has health and can die
 */
public class Health implements HealthStrategy {
    private Integer myHealth;
    private HealthBuilder myHealthBuilder;

    /**
     * Constructs Health strategy using parameters from healthBuilder
     * @param healthBuilder a copy of the cost builder that holds the information to create a specific instance of a
     *                      Health strategy
     */
    public Health(HealthBuilder healthBuilder) {
        myHealthBuilder = healthBuilder;
        myHealth = myHealthBuilder.getHealthValue();
    }

    /**
     * @return the sprite's current health
     */
    @Override
    public Integer getHealth() {
        return myHealth;
    }

    /**
     * Adds to the sprite's health
     * @param value the value to be added to health
     */
    @Override
    public void addHealth(int value) {
        myHealth += value;
    }

    /**
     * Removes from the sprite's health
     * @param value the value to be subtracted from health
     */
    @Override
    public void chunkHealth(int value) {
        myHealth -= value;
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of itself with the same health strategy
     * @throws GameEngineException
     */
    @Override
    public HealthStrategy makeClone() throws GameEngineException {
        return myHealthBuilder.build();
    }
}
