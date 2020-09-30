package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.RotationBuilder;


/**
 * Outlines the behavior of a sprite that does not rotate. Mostly exists for the sake of polymorphism.
 */
public class NoRotation implements RotationStrategy {

    /**
     * Constructs a NoAttack strategy. Initializes nothing, because it defines no behavior.
     * @param builder exists for the sake of polymorphism.
     */
    public NoRotation(RotationBuilder builder) {
    }

    /**
     * Always returns the angle fed into it, because the sprite does not rotate.
     * @param elapsedTime time since updateAngle() was last called
     * @param currentAngle the current angle the sprite is facing
     * @return
     */
    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        return currentAngle;
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same rotation strategy
     * @throws GameEngineException
     */
    @Override
    public RotationStrategy makeClone() throws GameEngineException {
        return new NoRotation(new RotationBuilder());
    }
}
