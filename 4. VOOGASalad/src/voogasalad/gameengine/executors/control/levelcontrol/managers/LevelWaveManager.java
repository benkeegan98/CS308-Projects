package voogasalad.gameengine.executors.control.levelcontrol.managers;

import voogasalad.gameengine.executors.control.levelcontrol.Wave;

import java.util.*;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class LevelWaveManager {
    private List<Wave> myWaves;
    private int myIndex;

    /**
     * Purpose:
     * Assumptions:
     */
    public LevelWaveManager() {
        myIndex = 0;
        myWaves = new ArrayList<>();
    }

    /**
     * Purpose:
     * Assumptions:
     * @param waves
     */
    public void addWavesCollection(Collection<Wave> waves) {
        myWaves.addAll(waves);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Wave getNextWave() {
        int index = myIndex;
        myIndex++;
        return myWaves.get(index);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public boolean hasNextWave() {
//        System.out.println("Index:" + myIndex + " " + myWaves);
        return myIndex < myWaves.size();
    }

}
