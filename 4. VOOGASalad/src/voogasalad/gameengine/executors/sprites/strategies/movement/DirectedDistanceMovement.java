package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.MovementBuilder;

import java.awt.geom.Point2D;
import java.util.List;

public class DirectedDistanceMovement implements MovementStrategy{

    private MovementBuilder myOriginalBuilder;
    private double myAngle;
    private double myDistance;
    private double mySpeed;
    private double myDistanceTravelled;
    private boolean isMovementFinished;

    public DirectedDistanceMovement(MovementBuilder builder) {
        myOriginalBuilder = builder;
        myAngle = 0;
        myDistance = builder.getDistance();
        mySpeed = builder.getSpeed();
        myDistanceTravelled = 0;
        isMovementFinished = false;
    }

    @Override
    public void updateDirectionalAngle(double angle) {
        myAngle = angle;
    }

    @Override
    public MovementStrategy makeClone() throws GameEngineException {
        return myOriginalBuilder.build();
    }

    @Override
    public Point2D.Double calculateNextPosition(double elapsedTime, Point2D.Double currentPosition) {
        Point2D.Double oldPosition = new Point2D.Double();
        oldPosition.setLocation(currentPosition.getX(), currentPosition.getY());
        currentPosition.setLocation(currentPosition.getX() + currentPosition.getX() * Math.cos(myAngle)*elapsedTime*mySpeed, currentPosition.getY() + currentPosition.getY()*Math.sin(myAngle)*elapsedTime*mySpeed);
        calculateDistanceTravelled(oldPosition, currentPosition);
        if(myDistanceTravelled >= myDistance){
            isMovementFinished = true;
        }
        return currentPosition;
    }

    private void calculateDistanceTravelled(Point2D.Double oldPosition, Point2D.Double currentPosition){
        myDistanceTravelled = myDistanceTravelled + Math.sqrt((Math.pow((currentPosition.getX() - oldPosition.getX()), 2) + Math.pow((currentPosition.getY() - oldPosition.getY()), 2)));
    }

    @Override
    public void updatePath(List<Point2D.Double> path) {
        //do nothing
    }

    @Override
    public boolean isMovementFinished() {
        return isMovementFinished;
    }
}
