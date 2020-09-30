package voogasalad.gameengine.executors.sprites.strategies.health;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.HealthBuilder;

/**
 * A health strategy for a sprite that has no health and cannot die
 */
public class NoHealth implements HealthStrategy {


    /**
     * Constructs NoHealth strategy
     * @param healthBuilder a copy of the cost builder that holds the information to create a specific instance of a
     *                      NoHealth strategy
     */
    public NoHealth(HealthBuilder healthBuilder) {
        //do nothing
    }

    /**
     * @return null; there is no health to return
     */
    @Override
    public Integer getHealth() {
        return null;
    }

    /**
     * Do not update health, because there is no health to update.
     * @param value the value to be added to health
     */
    @Override
    public void addHealth(int value) {
        // do nothing
    }

    /**
     * Do not update health, because there is no health to update.
     * @param value the value to be subtracted from health
     */
    @Override
    public void chunkHealth(int value) {
        // do nothing
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of itself with the same health strategy
     * @throws GameEngineException
     */
    @Override
    public HealthStrategy makeClone() throws GameEngineException {
        return new NoHealth(new HealthBuilder());
    }
}
