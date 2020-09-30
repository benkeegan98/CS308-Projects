package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.MovementBuilder;

import java.awt.geom.Point2D;
import java.util.List;

public class NoMovement implements MovementStrategy {

    public NoMovement(MovementBuilder builder) {
        //do nothing
    }

    @Override
    public MovementStrategy makeClone() throws GameEngineException {
        return new NoMovement(new MovementBuilder());
    }

    @Override
    public Point2D.Double calculateNextPosition(double elapsedTime, Point2D.Double currentPosition) {
        return currentPosition;
    }

    @Override
    public void updatePath(List<Point2D.Double> path) {
        //do nothing
    }

    @Override
    public void updateDirectionalAngle(double angle) {
        //do nothing
    }

    @Override
    public boolean isMovementFinished() {
        return false;
    }
}
