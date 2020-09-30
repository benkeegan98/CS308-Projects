package slogo.view;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import slogo.backend.Controller;

import java.util.ResourceBundle;

class SettingsPane extends VBox {

    private static final int COLOR_CIRCLE_RAD = 35;
    private static final int COLOR_PICKER_WIDTH = 150;
    private static final int TURTLE_PICKER_RAD = 50;
    private static final int MAX_PEN_THICKNESS = 5;
    private final String LANGUAGE_OPTIONS = "resources/languageOptions";

    private TurtleTurnup myTurtleTurnup;
    private Controller myController;
    private int penWidth = 1;
    private int penUp = 0;
    private Slider penThickness;
    private static CheckBox penDown;
    private ResourceBundle languageOptionsBundle;
    private ComboBox<String> languagePicker;


    SettingsPane(TurtleTurnup turtleTurnup, Controller controller) {
        languageOptionsBundle = ResourceBundle.getBundle(LANGUAGE_OPTIONS);
        getChildren().add(setLanguage());
        getChildren().add(setColorPickerPane("Pen Color"));
        getChildren().add(setColorPickerPane("Background Color"));
        getChildren().add(setTurtleImage());
        myTurtleTurnup = turtleTurnup;
        myController = controller;
        setId("settings-pane");
    }

    private VBox setLanguage() {
        VBox pane = new VBox();
        Label languageLabel = new Label("Language");
        languageLabel.setId("language-label");

        languagePicker = new ComboBox<>();
        languagePicker.setId("language-combo-box");
        languagePicker.getSelectionModel().select("English");

        languageOptionsBundle.getKeys().asIterator().forEachRemaining(key -> {
            languagePicker.getItems().add(languageOptionsBundle.getString(key));
        });
        languagePicker.setOnAction(e -> {
            myController.changeLanguage(languagePicker.getValue());
        });
        pane.getChildren().addAll(languageLabel, languagePicker);
        pane.setId("language-pane");
        return pane;
    }

    private VBox setColorPickerPane(String paneType) {
        VBox pane = new VBox();
        pane.setId("color-picker-pane");
        Label colorLabel = new Label(paneType);
        colorLabel.setId("color-label");

        Color initialColor = Color.WHITE;
        if(paneType.equals("Pen Color")){
            penColorPicker();
            initialColor = Color.BLACK;
        }

        Circle colorCircle = new Circle();
        colorCircle.setRadius(COLOR_CIRCLE_RAD);
        colorCircle.setId("color-circle");

        ColorPicker colorPicker = new ColorPicker(initialColor);
        colorPicker.setMaxWidth(COLOR_PICKER_WIDTH);
        colorPicker.setId("color-picker");

        colorCircle.setFill(colorPicker.getValue());
        colorPicker.setOnAction(e -> {
            colorCircle.setFill(colorPicker.getValue());
            if(paneType.equals("Background Color")) myTurtleTurnup.setBackgroundFill(colorPicker.getValue());
            else if (paneType.equals("Pen Color")) myTurtleTurnup.setPenColor(colorPicker.getValue());
        });

        pane.getChildren().addAll(colorLabel, colorCircle, colorPicker);
        if(paneType.equals("Pen Color")) {
            pane.getChildren().add(penThickness);
            pane.getChildren().add(penDown);
        }

        return pane;
    }

    private VBox setTurtleImage() {
        VBox pane = new VBox();
        Label turtleImageLabel = new Label("Turtle Image");
        turtleImageLabel.setId("turtle-picker-label");

        ImageView currentTurtleImage = new ImageView();
        setCurrentTurtleImage(currentTurtleImage, "turtle_slogo.png");
        currentTurtleImage.setFitHeight(TURTLE_PICKER_RAD);
        currentTurtleImage.setFitWidth(TURTLE_PICKER_RAD);
        currentTurtleImage.setPreserveRatio(true);

        ComboBox<String> turtleImagePicker = new ComboBox<>();
        turtleImagePicker.getSelectionModel().select("Image 1");

        turtleOptions(turtleImagePicker,currentTurtleImage);

        pane.getChildren().addAll(turtleImageLabel, currentTurtleImage, turtleImagePicker);
        pane.setId("turtle-picker-pane");

        return pane;
    }

    private void turtleOptions(ComboBox<String> turtleImagePicker, ImageView currentTurtleImage){
        turtleImagePicker.setId("turtle-picker-combo");
        turtleImagePicker.getItems().addAll("Image 1", "Image 2", "None");
        turtleImagePicker.setOnAction(e -> {
            String turtleImageName = turtleImagePicker.getValue();
            switch (turtleImageName) {
                case "Image 1":
                    myTurtleTurnup.changeTurtleImage("turtle_slogo.png");
                    myTurtleTurnup.hideTurtle(false);
                    setCurrentTurtleImage(currentTurtleImage, "turtle_slogo.png");
                    break;
                case "Image 2":
                    myTurtleTurnup.changeTurtleImage("turtle_slogo1.png");
                    myTurtleTurnup.hideTurtle(false);
                    setCurrentTurtleImage(currentTurtleImage, "turtle_slogo1.png");
                    break;
                case "None":
                    myTurtleTurnup.hideTurtle(true);
                    break;
            }
        });
    }

    private void setCurrentTurtleImage(ImageView currentTurtleImage, String turtleImageIdentifier) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream((turtleImageIdentifier)));
        currentTurtleImage.setImage(image);
    }

    static void updatePenDownSwitch(boolean bool) {
        penDown.setSelected(bool);
    }

    private void penColorPicker() {
        penThickness = new Slider(penUp, MAX_PEN_THICKNESS, penWidth);
        penThickness.setShowTickLabels(true);
        System.out.println(penThickness.getValue());
        penThickness.setId("pen-thickness-slider");
        penDown = new CheckBox("Pen");
        penDown.setSelected(true);
        penDown.setId("pen-down-checkbox");
        penDown.selectedProperty().addListener(e -> {
                    if(!penDown.isSelected()){
                    myController.setPen(false);
                    penThickness.setValue(penUp);
                }
                else{
                    penThickness.setValue(1);
                    myController.setPen(true);
                }
        });

        penThickness.valueProperty().addListener(e -> {
            myTurtleTurnup.setPenWidth(penThickness.getValue());
            if(penThickness.getValue()!= penUp){
                penDown.setSelected(true);
                myController.setPen(true);
            }
        });
    }

}
