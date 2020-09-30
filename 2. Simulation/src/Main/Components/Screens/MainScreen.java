package Main.Components.Screens;

import Main.Main;
import Main.Grid;
import Main.Simulations.Simulation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Samantha Whitt
 * MainScreen class is a subclass of Screen
 * used for setting up the main "menu" screen and its features with buttons
 * example: creating a new main screen to and adding more features that the user defaults to
 */
public class MainScreen extends Screen {
    private List<Button> mainButtons;

    /**
     * constructor for MainScreen that initializes button features and is the default screen
     */
    public MainScreen () {
        mainButtons = setupButtons();
        myScreen = setupScene();
    }

    /**
     * @param myGrid
     */
    @Override
    public void showGrid(Grid myGrid, Simulation mySim) {
        // does nothing, only in sim screen
    }

    /**
     * @return null
     */
    @Override
    public Slider getSpeedSlider() {
        return null;
    }

    /**
     * gets buttons with the simulation names
     * @return list of buttons on main screen
     */
    @Override
    public List<Button> getButtons() {
        return mainButtons;
    }

    /**
     * sets up main menu with text and buttons corresponding to each simulation type
     * @return new main scene
     */
    @Override
    Scene setupScene() {
        Group mainRoot = new Group();

        Text title = new Text();
        TextFont.setHeaderText(title);
        TextFont.setSceneText(title, Main.TITLE, Main.SCENE_WIDTH/2-100, Main.SCENE_HEIGHT/3);
        mainRoot.getChildren().add(title);

        double spacing = (Main.SCENE_WIDTH-(mainButtons.size()*mainButtons.get(0).getPrefWidth()))/(mainButtons.size()+1);
        double x_location = spacing;
        for (Button simButton: mainButtons) {
            simButton.setLayoutX(x_location);
            simButton.setLayoutY(20);
            x_location += simButton.getPrefWidth() + spacing;
            mainRoot.getChildren().add(simButton);
        }
        return new Scene(mainRoot, Main.SCENE_WIDTH, Main.SCENE_HEIGHT, Main.BACKGROUND);
    }

    /**
     * sets up the buttons found on the main screen by parsing in simulation types in resources
     * @return list of buttons on the main screen
     */
    @Override
    List<Button> setupButtons() {
        List<Button> res = new ArrayList<>();
        Enumeration<String> keys = Main.simTypes.getKeys();
        while (keys.hasMoreElements()) {
            Button currButton = new Button();
            String sim = Main.simTypes.getString(keys.nextElement());
            TextFont.setButtonText(currButton, sim);
            currButton.setPrefSize(150, 50);
            res.add(currButton);
        }
        return res;
    }

    @Override
    public void updateShowGrid(Grid myCellGrid, Simulation mySim) {

    }

    /**
     * @param myGrid
     */
//    @Override
//    public void updateShowGrid(Grid myGrid) {
//        // does nothing, only in sim screen
//    }
}
