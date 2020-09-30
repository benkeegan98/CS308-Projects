package voogasalad.gameplayer.gui.components.button;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import voogasalad.gameplayer.gui.components.button.ButtonController;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ButtonCreator extends HBox {
    private static final String BUTTON_RESOURCE_PATH = "resources.player.ButtonResource";
    private static final ResourceBundle BUTTON_RESOURCE_BUNDLE = ResourceBundle.getBundle(BUTTON_RESOURCE_PATH);
    private static final String BUTTON_ACTION_PATH = "resources.player.ButtonAction";
    private static final ResourceBundle BUTTON_ACTION_BUNDLE = ResourceBundle.getBundle(BUTTON_ACTION_PATH);

    private ButtonController myButtonController;
    private Map<String, Integer> choiceMap;
    private Map<String, Button> buttonMap;

    public ButtonCreator(ButtonController buttonController) {
        myButtonController = buttonController;
        initializeMap();
        createButtons();
    }

    private void initializeMap() {
        choiceMap = new HashMap<>();
        buttonMap = new HashMap<>();
    }

    private void createButtons() {
        getChildren().clear();
        for(String key : Collections.list(BUTTON_RESOURCE_BUNDLE.getKeys())) {
            String[] optionResources = BUTTON_RESOURCE_BUNDLE.getString(key).split(";");
            choiceMap.put(key, 1);
            Button button = new Button();
            buttonMap.put(key, button);
            ImageView image = new ImageView(new Image(optionResources[choiceMap.get(key)]));
            image.setFitWidth(80);
            image.setFitHeight(80);
            button.setGraphic(image);
            button.setOnAction(e -> callAction(key));
            button.setPrefHeight(getHeight());
            button.setMinWidth(100);
            getChildren().add(button);
        }
    }

    private void callAction(String key) {
        String methodName = BUTTON_ACTION_BUNDLE.getString(key);
        try {
            myButtonController.getClass().getDeclaredMethod(methodName).invoke(myButtonController);
        } catch (Exception e) {
            // TODO: catch this error
        }
    }

    /**
     * method for toggling the image of one of the buttons upon click
     *
     * @param key string key for a button in our button resource bundle
     */
    public void toggleImage(String key) {
        String[] optionResources = BUTTON_RESOURCE_BUNDLE.getString(key).split(";");
        toggleChoice(key);
        Button toToggle = buttonMap.get(key);
        ImageView newImage = new ImageView(new Image(optionResources[choiceMap.get(key)]));
        newImage.setFitWidth(80);
        newImage.setFitHeight(80);
        toToggle.setGraphic(newImage);
    }

    private void toggleChoice(String key) {
        if(choiceMap.get(key) == 0) {
            choiceMap.put(key, 1);
        } else {
            choiceMap.put(key, 0);
        }
    }

}
