package slogo.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import slogo.backend.Controller;

import java.util.HashMap;

class VariablesPane extends Pane {

    private Controller myController;
    private VBox pane;

    VariablesPane(Controller controller){
        myController = controller;
        Text instructions = new Text("Enter a number next to the\nvariable you'd like to edit and\npress enter to save the changes.");
        instructions.setId("instructions");
        getChildren().add(instructions);
    }

    void displayVariables(){
        ScrollPane scroller = new ScrollPane();
        pane = new VBox();
        HashMap variableMap = new HashMap<>(myController.getVariables());
        for(Object var : variableMap.keySet()){
            HBox line = new HBox();
            Text variable = new Text(var + " " + variableMap.get(var));
            TextField editor = new TextField();
            editor.setId("variable-box");
            line.getChildren().addAll(variable,editor);
            editor.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER){
                    pane.getChildren().remove(line);
                    line.getChildren().removeAll(variable,editor);
                    double newVal = Double.parseDouble(editor.getText());
                    myController.setVariable((String)var, newVal);
                    Text newVariable = new Text(var + " " + newVal);
                    line.getChildren().addAll(newVariable, editor);
                    pane.getChildren().add(line);
                }
            });
            System.out.println(variable);
            pane.getChildren().add(line);
        }
        scroller.setContent(pane);
        scroller.setPrefViewportHeight(Visualization.SCROLLER_HEIGHT);
        scroller.setPrefViewportWidth(Visualization.SCROLLER_WIDTH);
        getChildren().add(scroller);
    }

    void removeVariables(){
        getChildren().remove(pane);
    }

}
