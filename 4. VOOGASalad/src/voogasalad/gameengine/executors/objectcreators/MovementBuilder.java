package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovementBuilder implements StrategyBuilder {

    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.movement.";

    private String myType;
    private double mySpeed;
    private List<Point2D.Double> myPath;
    private double myDistance;

    public MovementBuilder setType(String typeString) {
        myType = typeString.strip();
        return this;
    }

    public String getType() {
        return myType;
    }

    public MovementBuilder setPath(String pathString) {
        myPath = ConfigurationTool.parsePath(pathString);
        return this;
    }

    public List<Point2D.Double> getPath() {
        return myPath;
    }

    public MovementBuilder setSpeed(String speedString) throws GameEngineException {
        try {
            mySpeed = Double.parseDouble(speedString.strip());
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
        return this;
    }

    public MovementBuilder setDistance(String distanceString) throws GameEngineException {
        try {
            myDistance = Double.parseDouble(distanceString);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
        return this;
    }

    public double getSpeed() {
        return mySpeed;
    }

    public double getDistance() {
        return myDistance;
    }

    @Override
    public MovementStrategy build() throws GameEngineException {
        try {
            return (MovementStrategy) Class.forName(CLASS_PATH + myType).getConstructor(MovementBuilder.class).newInstance(this);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteMovementInitializationFailed");
        }
    }
}
