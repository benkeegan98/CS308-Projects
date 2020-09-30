package voogasalad.gameengine.executors.objectcreators;

import javafx.util.Pair;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;

/**
 * Parses rotation parameters from the XML and builds a new rotation strategy using those parameters
 */
public class RotationBuilder implements StrategyBuilder {
    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.rotation.";

    public static final Pair<Double, Double> DEFAULT_ROTATION_RANGE = new Pair(0.0, 360.0);
    public static final String DEFAULT_TYPE = "NoRotation";

    private Double mySpeed;
    private Pair<Double, Double> myValidRotationRange;
    private String myType;

    /**
     * Parses a string that represents the rotation speed of a sprite
     * @param speedString string that represents the rotation speed of a sprite
     * @return self
     * @throws GameEngineException
     */
    public RotationBuilder setSpeed(String speedString) throws GameEngineException {
        try {
            mySpeed = Double.parseDouble(speedString);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteRotationInitializationFailed");
        }
        return this;
    }

    /**
     * Saves string that represents the type of RotationStrategy to be built
     * @param typeString string that represents the type of RotationStrategy to be built
     * @return self
     */
    public RotationBuilder setType(String typeString) {
        myType = typeString;
        return this;
    }

    /**
     * Parses rotationRangeString into a pair
     * @param rotationRangeString string that represents a pair of angles, the upper and lower bounds of a limited rotation
     *                            strategy
     * @return self
     */
    public RotationBuilder setValidRotationRange(String rotationRangeString) {
        try {
            String[] range = rotationRangeString.split(",");
            Double minAngle = Double.parseDouble(range[0]);
            Double maxAngle = Double.parseDouble(range[1]);
            myValidRotationRange = new Pair(minAngle, maxAngle);
        } catch (NumberFormatException e) {
            myValidRotationRange = DEFAULT_ROTATION_RANGE;
        }
        return this;
    }

    /**
     * @return currently set valid rotation range; if none has been set, does not return the default until AFTER build() is called
     */
    public Pair<Double, Double> getRotationRange() {
        return myValidRotationRange;
    }

    /**
     * @return currently set rotation speed; no default is set
     */
    public Double getSpeed() {
        return mySpeed;
    }

    /**
     * @return currently set type of rotation strategy; if none has been set, does not return the default until AFTER build() is called
     */
    public String getType() {
        return myType;
    }

    /**
     * Calls a rotation strategy constructor of the specified type. If none is set by the XML, it uses the default.
     * @return a strategy of a specified type with the parameters previously parsed
     * @throws GameEngineException
     */
    public RotationStrategy build() throws GameEngineException {
        if (myType == null) {
            myType = DEFAULT_TYPE;
        }
        try{
            return (RotationStrategy) Class.forName(CLASS_PATH + myType).getConstructor(RotationBuilder.class).newInstance(this);
        } catch(Exception e){
            throw new GameEngineException(e, "SpriteRotationInitializationFailed");
        }
    }

}
