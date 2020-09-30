package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public interface MovementStrategy {

    MovementStrategy makeClone() throws GameEngineException;

    Point2D.Double calculateNextPosition(double elapsedTime, Point2D.Double currentPosition);

    void updatePath(List<Point2D.Double> path);

    void updateDirectionalAngle(double angle);

    boolean isMovementFinished();
}
