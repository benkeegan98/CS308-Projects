package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.Button;

public class ActivateButton extends Button {

    public ActivateButton(int windowWidth){
        super("Click Here to Activate");
        this.setStyle("-fx-background-color: #00ff00; -fx-border-color:black;");
        this.setPrefWidth(windowWidth);
        this.setOnMouseClicked(event -> {
            System.out.println("TEST");
        });
    }
}
