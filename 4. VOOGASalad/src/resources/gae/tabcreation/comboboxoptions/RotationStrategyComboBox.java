package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ResourceBundle;

/**
 * node closs extending combobox for rotation strategy
 * author. Xiaoyang
 */
public class RotationStrategyComboBox extends ComboBox {
    private String strategyResourcePath = "resources.gae.tabcreation.comboboxoptions.Strategy";
    private ResourceBundle comboRB;
    private static final String rotationStrategy = "RotationStrategy";

    public RotationStrategyComboBox () {
        comboRB = ResourceBundle.getBundle(strategyResourcePath);
        String[] items = comboRB.getString(rotationStrategy).split(",");
        ObservableList<String> choices= FXCollections.observableArrayList(items);

        this.setItems(choices);
    }
}
