package slogo.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;


class HistoryPane extends Pane {

    ArrayList<String> history = new ArrayList<>();

    HistoryPane() {
        setId("history-pane");
        displayHistory();
    }

    void addToHistory(String command){
        //add to check no error with this command
        history.add(command);
    }

    void displayHistory(){
        ScrollPane scroller = new ScrollPane();
        VBox pane = new VBox();
        for(int i = 0; i<history.size(); i++){
            Text hist = new Text(" - " + history.get(i));
            pane.getChildren().add(hist);
        }
        scroller.setContent(pane);
        scroller.setPrefViewportHeight(Visualization.SCROLLER_HEIGHT);
        scroller.setPrefViewportWidth(Visualization.SCROLLER_WIDTH);
        getChildren().add(scroller);
    }
}

