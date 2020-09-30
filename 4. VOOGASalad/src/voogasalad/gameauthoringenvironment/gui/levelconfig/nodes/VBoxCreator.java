package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class VBoxCreator extends VBox {
    FlowPane createdObjects;
    String type;

    /**
     * @author Marc Jabbour
     * This constructor creates a VBox for each game object, with the parameter "typeParam" coming from a properties file
     * @param typeParam
     * @param width
     * @param height
     */
    public VBoxCreator(String typeParam, int width, int height){
        type = typeParam;
        Label createdLabel = createLabel(type, Color.CHOCOLATE);
        ScrollPane scrollPane = createScrollWithFlowPane(height);
        createVBoxLayout(width, height);
        this.getChildren().addAll(createdLabel, scrollPane);
    }

    private void createVBoxLayout(int width, int height){
        this.setMaxHeight(height);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefWidth(width);
        this.setStyle("-fx-border-color: black;\n");
    }


    private ScrollPane createScrollWithFlowPane(int height){
        createdObjects = new FlowPane();
        createdObjects.setAlignment(Pos.CENTER);
        ScrollPane sp = new ScrollPane(createdObjects);
        sp.setPrefHeight(height);
        sp.setMaxHeight(sp.getPrefHeight());
        sp.setFitToWidth(true);
        return sp;
    }

    private Label createLabel(String type, Paint color){
        Label createdLabel = new Label("Created " + type);
        createdLabel.setTextFill(color);
        return createdLabel;
    }

    /**
     * This method adds a game object icon to the appropriate HBox
     * @param icon
     * @param objectType
     */
    public void addToObjectHBox(Node icon, String objectType){
        if(objectType.equals(type)){
            createdObjects.getChildren().add(icon);
        }
    }

}
