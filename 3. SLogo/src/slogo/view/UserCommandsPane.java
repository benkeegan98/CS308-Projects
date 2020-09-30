package slogo.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.backend.Controller;

import java.util.HashMap;
import java.util.Map;

class UserCommandsPane extends Pane {

    private Controller myController;

    UserCommandsPane(Controller controller){
        myController = controller;
    }

    void displayUserCommands(){
        ScrollPane scroller = new ScrollPane();
        VBox pane = new VBox();
        Map<String, Double> userCommandsMap = new HashMap<>(myController.getCommands());
        for(String com : userCommandsMap.keySet()){
            Text command = new Text(com);
            System.out.println(command);
            pane.getChildren().add(command);
        }
        scroller.setContent(pane);
        scroller.setPrefViewportHeight(Visualization.SCROLLER_HEIGHT);
        scroller.setPrefViewportWidth(Visualization.SCROLLER_WIDTH);
        getChildren().add(scroller);
    }
}
