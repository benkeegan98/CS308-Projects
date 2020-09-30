package voogasalad.gameplayer.gui.components;

import javafx.scene.text.Text;

public class StopWatch extends Text{
    private long startTime;
    private long elapsedSeconds;
    private long elapsedMinutes;
    private long prevElapsedSeconds;

    public StopWatch() {
        elapsedSeconds = 0;
        elapsedMinutes = 0;
        prevElapsedSeconds = 0;
    }

    public String getCurrentTime(){
        long elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = prevElapsedSeconds + elapsedTime / 1000;
        elapsedMinutes = elapsedSeconds / 60;
        long secondsDisplay = elapsedSeconds % 60;
        String timeDisplay = "Elapsed Time: \n " + elapsedMinutes + " : " + secondsDisplay;
        return  timeDisplay;
    }

    public void pauseStopWatch(){
        prevElapsedSeconds = elapsedSeconds;
    }

    public void startStopWatch(){
        startTime = System.currentTimeMillis();
    }
}
