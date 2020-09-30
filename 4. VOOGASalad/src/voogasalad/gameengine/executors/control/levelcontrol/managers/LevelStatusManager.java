package voogasalad.gameengine.executors.control.levelcontrol.managers;

import voogasalad.gameengine.executors.control.levelcontrol.Status;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class LevelStatusManager {

    public static final Status INITIAL_GAME_SCENE_STATUS = Status.INACTIVE;

    private double myTotalElapsedTime;
    private double myElapsedTimeSinceLastFrame;
    private int myResources;
    private int myLives;
    private Status myStatus;
    private int myScore;

    /**
     * Purpose:
     * Assumptions:
     */
    public LevelStatusManager() {
        myResources = 0;
        myLives = 0;
        myTotalElapsedTime = 0;
        myElapsedTimeSinceLastFrame = 0;
        myScore = 0;
        myStatus = INITIAL_GAME_SCENE_STATUS;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param resources
     * @param lives
     * @param score
     * @param totalElapsedTime
     * @param elapsedTimeSinceLastFrame
     * @param status
     */
    public LevelStatusManager (int resources, int lives, int score, double totalElapsedTime, double elapsedTimeSinceLastFrame, Status status) {
        myTotalElapsedTime = totalElapsedTime;
        myElapsedTimeSinceLastFrame = elapsedTimeSinceLastFrame;
        myResources = resources;
        myScore = score;
        myLives = lives;
        myStatus = status;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param status
     */
    public void setGameSceneStatus(Status status) {
        myStatus = status;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public Status getGameSceneStatus() {
        return myStatus;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param value
     */
    public void setResources(int value) {
        myResources = value;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param value
     */
    public void setLives(int value) {
        myLives = value;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param elapsedTime
     */
    public void notifyNewCycle(double elapsedTime) {
        myElapsedTimeSinceLastFrame = elapsedTime;
        myTotalElapsedTime += elapsedTime;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param value
     * @return
     */
    public int alterScoreByValue(int value) {
        myScore += value;
        return myScore;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getScore() {
        return myScore;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public double getTotalElapsedTime() {
        return myTotalElapsedTime;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public double getElapsedTimeSinceLastFrame() {
        return myElapsedTimeSinceLastFrame;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getResources() {
        return myResources;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public int getLives() {
        return myLives;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param value
     * @return
     */
    public int alterResourcesByValue(int value) {
        myResources += value;
        return myResources;
    }

    /**
     * Purpose:
     * Assumptions:
     * @param value
     * @return
     */
    public int alterLivesByValue(int value) {
        myLives += value;
        return myLives;
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    public LevelStatusManager getCopyOfStatusManager() {
        return new LevelStatusManager(myResources, myLives, myScore, myTotalElapsedTime, myElapsedTimeSinceLastFrame, myStatus);
    }
}
