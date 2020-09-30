package voogasalad.gameengine.executors.sprites.strategies.health;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

/**
 * Outlines health behavior of a certain sprite. Must exist within an initialized sprite.
 */
public interface HealthStrategy {
    /**
     * @return a sprite's current health
     */
    Integer getHealth();

    /**
     * Adds to the sprite's health
     * @param value the value to be added to health
     */
    void addHealth(int value);

    /**
     * Removes from the sprite's health
     * @param value the value to be subtracted from health
     */
    void chunkHealth(int value);

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of itself with the same health strategy
     * @throws GameEngineException
     */
    HealthStrategy makeClone() throws GameEngineException;
}
