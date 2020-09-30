package voogasalad.gameengine.executors.sprites.strategies.rotation;

import javafx.util.Pair;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.RotationBuilder;

/**
 * Outlines the behavior of a sprite that can only face a limited range of angles
 */
public class LimitedRotation implements RotationStrategy {
    private Double targetAngle;
    private Double rotationSpeed;
    private Pair<Double, Double> validRotationRange;
    private int rotationDirection;
    RotationBuilder myBuilder;


    /**
     * Constructs an instance of a LimitedRotation strategy.
     * @param builder a copy of the rotation builder that holds the information to create a specific instance of a
     *      *                      LimitedRotation strategy
     */
    public LimitedRotation(RotationBuilder builder) {
        myBuilder = builder;
        rotationSpeed = myBuilder.getSpeed();
        validRotationRange = myBuilder.getRotationRange();
        targetAngle = validRotationRange.getKey();
    }

    /**
     * Updates the current angle of the sprite based on its previous angle
     * @param elapsedTime time since updateAngle() was last called
     * @param currentAngle the current angle the sprite is facing
     * @return the updated angle the sprite is facing
     */
    @Override
    public double updateAngle(double elapsedTime, double currentAngle) {
        determineTargetAngle(currentAngle);
        determineRotationDirection(currentAngle);
        double diffAngle = rotationDirection * elapsedTime * rotationSpeed;
        return currentAngle + diffAngle;
    }

    /**
     * Determines the sprite's next target angle - it should be rotating between the upper and lower bounds of the
     * valid rotation range.
     * @param currentAngle the current angle the sprite is facing
     */
    private void determineTargetAngle(Double currentAngle) {
        Double lowerBound = validRotationRange.getKey();
        Double upperBound = validRotationRange.getValue();
        if (currentAngle >= upperBound){
            targetAngle = lowerBound;
        }
        if (currentAngle <= lowerBound){
            targetAngle = upperBound;
        }
    }

    /**
     * Based on current angle, determines which direction the tower should rotate in to reach the target
     * @param currentAngle the angle the sprite is currently facing
     */
    private void determineRotationDirection(Double currentAngle) {
        if (currentAngle.equals(targetAngle)) {
            rotationDirection = 0;
        } else if (currentAngle > targetAngle) {
            rotationDirection = -1;
        } else {
            rotationDirection = 1;
        }
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same rotation strategy
     * @throws GameEngineException
     */
    @Override
    public RotationStrategy makeClone() throws GameEngineException {
        return new LimitedRotation(myBuilder);
    }
}