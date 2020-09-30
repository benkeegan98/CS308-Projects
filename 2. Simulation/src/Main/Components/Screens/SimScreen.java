package Main.Components.Screens;

import Main.Components.Cell.Cell;
import Main.Main;
import Main.Grid;
import Main.Simulations.Simulation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * @author Samantha Whitt, Ben Keegan
 *  SimScreen class is a subclass of Screen
 *  used for setting up the specific simulation screen and its features, and showing the grid while updating it
 *  appropriately
 *  example: creating a new simulation screen for a new simulation
 */
public class SimScreen extends Screen {
    private List<Button> perButtons;
    private Group simRoot;
    private Boolean showLines = false;
    private Slider speedSlider = null;
    private String myTitle;
    private Shape[][] cellsDisplayed;
    private Cell[][] simCells;

    private final int CELL_SIZE = 10;
    /**
     * constructor for SimScreen that initializes the simulation button features and sets the screen to the simulation
     */
    public SimScreen(String simName) {
        myTitle = simName;
        myScreen = setupScene();
    }

    /**
     * shows/initializes the Grid
     * @param myGrid
     */
    @Override
    public void showGrid(Grid myGrid, Simulation mySim) {
        simCells = myGrid.getGridArray();
        cellsDisplayed = new Shape[simCells.length][simCells[0].length];
        double x;
        double y = 50;
        for (int i=0; i<simCells.length; i++) {
            x = 10;
            for (int j=0; j<simCells[0].length; j++) {
                Rectangle currCell = new Rectangle(CELL_SIZE, CELL_SIZE, simCells[i][j].getColor());
                currCell.setX(x);
                currCell.setY(y);
                if (showLines) {
                    currCell.setStroke(Color.LIGHTGREY);
                }
                cellsDisplayed[i][j] = currCell;
                simRoot.getChildren().add(cellsDisplayed[i][j]);
                x += 10;
            }
            y += 10;
        }
    }

    /**
     * calculates the next state then sets the state to the next state
     * @param myGrid
     * @param mySim
     */
    @Override
    public void updateShowGrid(Grid myGrid, Simulation mySim) {

        // set all the nextStates of each cell in the whole grid
        for(int i = 0; i < cellsDisplayed.length; i++) {
            for(int j = 0; j < cellsDisplayed[i].length; j++) {
                mySim.updateNextState(myGrid.getGridArray()[i][j]); // set next states
            }
        }
        // set myState of each cell to be nextState, then color the cell based on its new state
        for(int i = 0; i < cellsDisplayed.length; i++) {
            for (int j = 0; j < cellsDisplayed[i].length; j++) {
                myGrid.getGridArray()[i][j].goToNextState(); // sets all the myStates to be their nextStates
                if (showLines) {
                    cellsDisplayed[i][j].setStroke(Color.LIGHTGREY);
                }
                cellsDisplayed[i][j].setFill(myGrid.getGridArray()[i][j].getColor());
            }
        }
    }

    /**
     * @return simulation's back button to return to main screen
     */
    @Override
    public Slider getSpeedSlider() {
        return speedSlider;
    }

    /**
     * @return list of simulation's buttons
     */
    @Override
    public List<Button> getButtons() {
        return perButtons;
    }

    /**
     * sets up the scene with buttons/interactive user components
     * @return new sim screen
     */
    @Override
    Scene setupScene() {
        simRoot = new Group();
        perButtons = setupButtons();

        Text simTitle = new Text();
        TextFont.setHeaderText(simTitle);
        TextFont.setSceneText(simTitle, myTitle, 10, 40);
        simRoot.getChildren().add(simTitle);

        SideFeatures mySideFeatures = new SideFeatures();
        CheckBox gridLines = mySideFeatures.getCheckBox();
        gridLines.setOnAction(e -> {
            if (gridLines.isSelected()) {
                showLines = true;
            } else {
                showLines = false;
            }
        });
        speedSlider = mySideFeatures.getSlider();
        List<Node> sideFeatures = mySideFeatures.getSideFeatures();

        int x = 520;
        int y = 200;
        for (Node sideFeature: sideFeatures) {
            sideFeature.setLayoutX(x);
            sideFeature.setLayoutY(y);
            simRoot.getChildren().add(sideFeature);
            y += 40;
        }

        CellChart myCellChart = new CellChart();
        myCellChart.setX(Main.SCENE_WIDTH-500);
        myCellChart.setY(100);

        simRoot.getChildren().add(myCellChart.getCellChart());

        return new Scene(simRoot, Main.SCENE_WIDTH, Main.SCENE_HEIGHT, Main.BACKGROUND);
    }

    /**
     * sets up the buttons based on the button names resource file
     * @return list of all the simulation's buttons
     */
    @Override
    List<Button> setupButtons() {
        List<Button> res = new ArrayList<>();

        Enumeration<String> keys = Main.buttonNames.getKeys();
        double x_location = 10;
        while (keys.hasMoreElements()) {
            Button currButton = new Button();
            String simFeature = Main.buttonNames.getString(keys.nextElement());
            TextFont.setButtonText(currButton, simFeature);
            currButton.setPrefSize(100, 10);
            currButton.setLayoutX(x_location);
            currButton.setLayoutY(Main.SCENE_HEIGHT-40);
            x_location += currButton.getPrefWidth();
            simRoot.getChildren().add(currButton);
            res.add(currButton);
        }
        return res;
    }
}
