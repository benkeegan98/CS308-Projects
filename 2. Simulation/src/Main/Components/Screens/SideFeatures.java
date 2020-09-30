package Main.Components.Screens;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Samantha Whitt
 * This class contains the side bar features for each simulation screen
 * Example: creating a new side feature that goes in between the grid and chart
 */
public class SideFeatures {
    private List<Node> mySideFeatures;
    private Slider speedSlider;
    private CheckBox checkLines;

    /**
     * constructor for side features that initialize the overall list of the side features to be
     * appropriately positioned on the screen in SimScreen
     */
    public SideFeatures() {
        mySideFeatures = new ArrayList<>();
        setupSide();
    }

    /**
     * @return list of all the side components
     */
    public List<Node> getSideFeatures() {
        return mySideFeatures;
    }

    /**
     * needed for affecting speed in main class
     * @return slider
     */
    public Slider getSlider() {
        return speedSlider;
    }

    /**
     * needed for changing whether or not you see the grid lines
     * @return checkbox for lines
     */
    public CheckBox getCheckBox() {
        return checkLines;
    }

    /**
     * sets up all the components on the side screen
     * example: create a new label, slider, etc. -- order matters!
     */
    private void setupSide() {
        Label configType = createLabel(Main.sideFeatures.getString("config"));
        ComboBox configStates = createComboBox(Main.configTypes);
        Label speedLabel = createLabel(Main.sideFeatures.getString("speed"));
        speedSlider = createSlider(Main.MIN_SPEED, Main.MAX_SPEED, true);
        checkLines = createCheckBox(Main.sideFeatures.getString("grid"));
    }

    /**
     * creates a combo box dropdown of given options from a resource bundle
     * @param bundle
     * @return combo box of the simulation options
     */
    private ComboBox createComboBox(ResourceBundle bundle) {
        ArrayList<String> myOptions = new ArrayList<>();
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            myOptions.add(bundle.getString(keys.nextElement()));
        }
        ComboBox currComboBox = new ComboBox(FXCollections.observableArrayList(myOptions));
        mySideFeatures.add(currComboBox);
        return currComboBox;
    }

    /**
     * creates a new slider depending on what the coder wants for a min/max value and whether or not they
     * want to show the tick marks
     * @param min
     * @param max
     * @param showTicks
     * @return slider with minimum amount and maximum amount
     */
    private Slider createSlider(double min, double max, boolean showTicks) {
        Slider currSlider = new Slider();
        currSlider.setShowTickLabels(showTicks);
        currSlider.setMin(min);
        currSlider.setMax(max);
        mySideFeatures.add(currSlider);
        return currSlider;
    }

    /**
     * creates a label with the given text
     * @param labelText
     * @return label with the given text
     */
    private Label createLabel(String labelText) {
        Label currLabel = new Label(labelText);
        mySideFeatures.add(currLabel);
        return currLabel;
    }

    /**
     * creates a checkbox with the given name
     * @param checkBoxName
     * @return checkbox with given name
     */
    private CheckBox createCheckBox(String checkBoxName) {
        CheckBox currCheckBox = new CheckBox();
        currCheckBox.setText(checkBoxName);
        mySideFeatures.add(currCheckBox);
        return currCheckBox;
    }
}
