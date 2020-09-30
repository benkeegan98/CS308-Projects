package voogasalad.gameauthoringenvironment.gui.mapconfig;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WaveInfo {
    private int waveIndex;
    private ArrayList<Integer> enemyList;
    private double spawningTime;
    private double totalWaveDuration;
    private int pathIndex;
    public WaveInfo(int waveIndex, ArrayList<Integer> enemyList, double spawningTime, double totalWaveDuration, int pathIndex){
        this.waveIndex = waveIndex;
        this.enemyList = new ArrayList<>(enemyList);
        this.spawningTime = spawningTime;
        this.totalWaveDuration = totalWaveDuration;
        this.pathIndex = pathIndex;
    }

    public void saveToXML() {

    }

    public void printInfo() {
        System.out.println("waveIndex " + waveIndex + " Enemy List " + enemyList.toString() + " spawning time" + spawningTime + " total wave duration " + totalWaveDuration + " path index " + pathIndex);

    }
}
