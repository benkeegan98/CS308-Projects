package voogasalad.gameauthoringenvironment.gui.mapconfig;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PathInfo {
    private int pathIndex;
    private ArrayList<Pair<Double,Double>> pathPoints;
    private Pair <Double,Double> spawnPoint;
    public PathInfo (int pathIndex, Pair<Double,Double> spawnPoint, ArrayList<Pair<Double,Double>> pathPoints){
        this.pathIndex = pathIndex;
        this.pathPoints = new ArrayList<>(pathPoints);
        this.spawnPoint = new Pair<>(spawnPoint.getKey(),spawnPoint.getValue());

    }

    public void printInfo() {
        System.out.println("PathIndex " + pathIndex + " Path Point List " + pathPoints.toString() + " spawning point" + spawnPoint.toString());

    }
}
