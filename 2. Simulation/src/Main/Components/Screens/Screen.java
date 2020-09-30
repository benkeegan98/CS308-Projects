package Main.Components.Screens;

import Main.Grid;
import Main.Simulations.Simulation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.util.List;

/**
 * @author Samantha Whitt
 * Screen class is an abstract class for setting up the main and simulation screens
 * example: creating a new screen type like a game over
 **/
public abstract class Screen {
    protected Scene myScreen;

    /**
     * in sim screen, initializes the visual grid given the backend grid
     * also takes user input as to whether or not to show grid lines visually
     * @param myGrid
     */
    public abstract void showGrid(Grid myGrid, Simulation mySim);


    /**
     * @return current screen (main or simulation)
     */
    public Scene getScene() {
        return myScreen;
    }

    /**
     * @return back button of sim screen
     */
    public abstract Slider getSpeedSlider();

    /**
     * @return list of all the buttons on the screen
     */
    public abstract List<Button> getButtons();

    /**
     * initializes scene appropriately
     * @return scene of the current screen (main or simulation)
     */
    abstract Scene setupScene();

    /**
     *
     * @return list of the screen's buttons
     */
    abstract List<Button> setupButtons();

    /**
     * in sim screen, updates the grid per each cell in the backend and visually in the frontend
     * @param myCellGrid
     * @param mySim
     */
    public abstract void updateShowGrid(Grid myCellGrid, Simulation mySim);
}
