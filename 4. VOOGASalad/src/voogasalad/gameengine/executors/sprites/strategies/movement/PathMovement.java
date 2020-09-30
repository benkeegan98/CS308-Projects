package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.MovementBuilder;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class PathMovement implements MovementStrategy {
    private MovementBuilder myOriginalBuilder;
    private List<Point2D.Double> myPath;
    private Point2D.Double nextPosition;
    private int nextPositionIndex;
    private boolean reachedEnd;
    private double mySpeed;
    private Point2D.Double myDirection;


    public PathMovement(MovementBuilder builder) throws GameEngineException {
        mySpeed = builder.getSpeed();
        myPath = builder.getPath();
        myOriginalBuilder = builder;
        nextPositionIndex = 0;
    }

    @Override
    public MovementStrategy makeClone() throws GameEngineException {
        return myOriginalBuilder.build();
    }

    @Override
    public Point2D.Double calculateNextPosition(double elapsedTime, Point2D.Double currentPosition) {
        if(reachedEnd) {
            return currentPosition;
        } else if (myDirection == null){
            myDirection = calculateDirection(currentPosition);
        }
        double diffX = myDirection.getX() * elapsedTime;
        double diffY = myDirection.getY() * elapsedTime;
        double updatedX = currentPosition.getX() + diffX;
        double updatedY = currentPosition.getY() + diffY;
        Point2D.Double updatedPosition = new Point2D.Double();
        updatedPosition.setLocation(updatedX, updatedY);
        if(checkDirectionChange(currentPosition, updatedPosition)) {
            Point2D.Double toReturn = new Point2D.Double();
            toReturn.setLocation(nextPosition.getX(), nextPosition.getY());
            changeDirection();
            return toReturn;
        } else {
            return updatedPosition;
        }
    }

    @Override
    public void updatePath(List<Point2D.Double> path) {
        myPath = path;
        if(myPath.size() < 1) {
            reachedEnd = true;
        } else {
            reachedEnd = false;
            nextPosition = myPath.get(nextPositionIndex);
        }
    }

    @Override
    public void updateDirectionalAngle(double angle) {
        //do nothing
    }

    @Override
    public boolean isMovementFinished() {
        return false;
    }

    private Point2D.Double calculateDirection(Point2D.Double currentPosition) {
        Point2D.Double updatedDirection = new Point2D.Double();
        double diffX = nextPosition.getX() - currentPosition.getX();
        double diffY = nextPosition.getY() - currentPosition.getY();
        double hypotenuse = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        double ratio = mySpeed / hypotenuse;
        double updatedX = diffX * ratio;
        double updatedY = diffY * ratio;
        updatedDirection.setLocation(updatedX, updatedY);
        return updatedDirection;
    }

    private boolean checkDirectionChange(Point2D.Double currentPosition, Point2D.Double updatedPosition) {
        boolean passedX = checkInRange(nextPosition.getX(), currentPosition.getX(), updatedPosition.getX());
        boolean passedY = checkInRange(nextPosition.getY(), currentPosition.getY(), updatedPosition.getY());
        return passedX && passedY;
    }

    private boolean checkInRange(double x, double bound1, double bound2) {
        return ((x - bound1) * (x - bound2) <= 0);
    }

    private void changeDirection() {
        if(nextPositionIndex + 1 < myPath.size()) {
            nextPositionIndex++;
            Point2D.Double origin = new Point2D.Double();
            origin.setLocation(nextPosition.getX(), nextPosition.getY());
            nextPosition = myPath.get(nextPositionIndex);
            myDirection = calculateDirection(origin);
        } else {
            reachedEnd = true;
        }
    }
}
