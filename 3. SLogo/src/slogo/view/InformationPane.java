package slogo.view;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.backend.Controller;

class InformationPane extends Pane {

    private static final String TURTLE_LOCATION = "Turtle Location: ";
    private static final String TURTLE_HEADING = "Turtle Direction: ";
    private static final String PEN_IN_USE = "Pen Down: ";

    private Text turtleLoc;
    private Text turtleDirection;
    private Text penInUse;
    private Controller myController;
    private VBox infoBox;

    InformationPane(Controller controller){
        infoBox = new VBox();
        infoBox.setId("turtle-info-box");
        myController = controller;
        infoBox.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().add(infoBox);
    }

    void displayInfo(){
        turtleLoc = new Text(TURTLE_LOCATION + myController.getTurtleX() + ", " + myController.getTurtleY());
        turtleDirection = new Text(TURTLE_HEADING + myController.getTurtleDirection());
        penInUse = new Text(PEN_IN_USE + myController.isPenDown());
        infoBox.getChildren().addAll(turtleLoc, turtleDirection, penInUse);
    }

    void removeOldInfo(){
        infoBox.getChildren().clear();
    }

}
