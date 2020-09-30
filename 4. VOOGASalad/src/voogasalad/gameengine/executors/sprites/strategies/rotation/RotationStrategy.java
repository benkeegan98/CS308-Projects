package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

/**
 * Outlines the rotation behavior of a certain sprite. Must exist within an initialized sprite.
 */

public interface RotationStrategy {

    /**
     * Updates the current angle the sprite is facing (which affects the shooting direction)
     * @param elapsedTime time since updateAngle() was last called
     * @param currentAngle the current angle the sprite is facing
     * @return
     */
    double updateAngle(double elapsedTime, double currentAngle);

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same rotation strategy
     * @throws GameEngineException
     */
    RotationStrategy makeClone() throws GameEngineException;

}