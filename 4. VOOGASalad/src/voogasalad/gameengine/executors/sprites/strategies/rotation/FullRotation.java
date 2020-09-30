package voogasalad.gameengine.executors.sprites.strategies.rotation;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.RotationBuilder;

/**
 * Outlines the behavior of a sprite that rotates a full 360 degrees without changing direction
 */
public class FullRotation implements RotationStrategy {
    private Double rotationSpeed;
    private RotationBuilder myBuilder;

    /**
     * Constructs a FullRotation strategy based on the information in the builder
     * @param rotationBuilder initializes instance variables based on information parsed from the XML and saved in the
     *                        builder
     */
    public FullRotation(RotationBuilder rotationBuilder) {
        myBuilder = rotationBuilder;
        rotationSpeed = rotationBuilder.getSpeed();
    }

    /**
     * Updates the current angle of the sprite based on the previous angle
     * @param elapsedTime time since updateAngle() was last called
     * @param currentAngle the current angle the sprite is facing
     * @return updated angle
     */
    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        double diffAngle = elapsedTime * rotationSpeed;
        return (currentAngle + diffAngle) % 360;
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same rotation strategy
     * @throws GameEngineException
     */
    @Override
    public RotationStrategy makeClone() throws GameEngineException{
        return new FullRotation(myBuilder);
    }
}
