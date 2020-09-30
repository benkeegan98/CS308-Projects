package Main;

import Main.Components.Screens.MainScreen;
import Main.Components.Screens.Screen;
import Main.Components.Screens.SimScreen;
import Main.Simulations.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;

/**
 * @author Samantha Whitt, Ben Keegan, Amber Johnson, Robert Duvall
 * Main class handles running the entire application:
 * switches between screens/simulations, runs the animation to do so, initializes button actions,
 * calls backend to update cells in updating backend-wise and visually
 * example: creating a window that switches between screens via buttons
 **/
public class Main extends Application {
    public static final String TITLE = "Cell Society";
    public static final int SCENE_WIDTH = 1200, SCENE_HEIGHT = 600;
    public static final Paint BACKGROUND = Color.LIGHTGREY;
    public static final ResourceBundle simTypes = ResourceBundle.getBundle("SimulationTypes");
    public static final ResourceBundle buttonNames = ResourceBundle.getBundle("ButtonNames");
    public static final ResourceBundle configTypes = ResourceBundle.getBundle("ConfigurationTypes");
    public static final ResourceBundle sideFeatures = ResourceBundle.getBundle("SideFeatures");
    public static final ResourceBundle chartInfo = ResourceBundle.getBundle("ChartInfo");
    public static final int MIN_SPEED = 1;
    public static final int MAX_SPEED = 10;

    private Screen myScreen;
    private Screen myMainScreen;
    private Stage myStage;
    private Timeline animation;
    private Simulation mySimulation;
    private Boolean runSim = false;
    private Slider mySlider;
    private Grid myCellGrid = null;
    private double FRAMES_PER_SECOND = 5, MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private int GRID_SIZE = 50;

    /**
     * initializes the stage and screen to main screen
     * @param primaryStage
     */
    @Override
    public void start (Stage primaryStage) {
        myStage = primaryStage;
        myMainScreen = new MainScreen();
        myScreen = myMainScreen;
        for (Button simButton : myMainScreen.getButtons()) {
            simButton.setOnAction(e -> myStage.setScene(switchToSimScene(simButton.getText())));
        }
        primaryStage.setScene(myMainScreen.getScene());
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        myStage.show();

        gameLoop();
    }

    /**
     * attaches a game loop to the animation's timeline and continuously calls step()
     */
    private void gameLoop() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    /**
     * changes properties of the app components over time constantly
     * runs simulation accordingly
     * @param elapsedTime
     */
    private void step (double elapsedTime) {
        if (runSim && mySimulation != null && myCellGrid != null) {
            updateShowCells();
        }
    }

    /**
     * initializes a simulation screen, sets sim button functions, shows initial cell grid,
     * sets the stage to new simulation scene
     * only called if corresponding button in main is pressede
     * @return new simulation scene
     */
    private Scene switchToSimScene(String simType) {

        myScreen = new SimScreen(simType);

        switch (simType) {
            case "Game of Life" -> mySimulation = new Life(GRID_SIZE, GRID_SIZE);
            case "Spreading Fire" -> mySimulation = new Fire(GRID_SIZE, GRID_SIZE);
            case "Percolation" -> mySimulation = new Percolation(GRID_SIZE, GRID_SIZE);
            case "Segregation" -> mySimulation = new Segregation(GRID_SIZE, GRID_SIZE);// pass simulation back from main as an argument when we update
            case "Predator and Prey" -> mySimulation = new PredatorPrey(GRID_SIZE, GRID_SIZE);
        }

        myCellGrid = mySimulation.getGrid();
        myScreen.showGrid(myCellGrid, mySimulation);
        for (Button simButton: myScreen.getButtons()) {
            String buttonText = simButton.getText();
            if (buttonText.equals(buttonNames.getString("back"))) {
                simButton.setOnAction(e -> myStage.setScene(switchToMainScene()));
            } else if (buttonText.equals(buttonNames.getString("play"))) {
                simButton.setOnAction(e -> runSim = true);
            } else if (buttonText.equals(buttonNames.getString("pause"))) {
                simButton.setOnAction(e -> runSim = false);
            } else if (buttonText.equals(buttonNames.getString("step"))) {
                simButton.setOnAction(e -> updateShowCells());
            }
        }
        mySlider = myScreen.getSpeedSlider();
        mySlider.setValue(FRAMES_PER_SECOND);
        mySlider.valueProperty().addListener((ov, old_val, new_val) -> FRAMES_PER_SECOND = new_val.doubleValue());
        return myScreen.getScene();
    }

    /**
     * calls method in simulation screen to update each cell in the backend and frontend
     */
    private void updateShowCells() {
        myScreen.updateShowGrid(myCellGrid, mySimulation);
    }

    /**
     * sets the stage to the main screen, which was the first screen
     * only called on if back button is pressed from a simulation screen
     * @return main scene
     */
    private Scene switchToMainScene() {
        runSim = false;
        return myMainScreen.getScene();
    }

    /**
     * starts the program
     * @param args
     */
    public static void main (String[] args) {
        launch(args);
    }
}
