package voogasalad.gameauthoringenvironment.gui.tabconfig;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

/**
 *
 */
public class TabFieldCreator extends Group {
    private static final String RESOURCE_PATH = "resources.gae.";
    private static final String DEFAULT_IMAGE_NAME = "ghost1.png";
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    private ResourceBundle myResource;
    private String tabName;
    private VBox mainVBox ;
    private HBox mainHBox;
    private VBox buttonVBox;
    private Image defaultImage;
    private ImageView defaultImageView;

    public TabFieldCreator(String tabLabel, ResourceBundle resource){
        this.myResource = resource;
        defaultImage = new Image(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE_NAME));
        defaultImageView = new ImageView(defaultImage);
        defaultImageView.setFitWidth(WIDTH);
        defaultImageView.setFitHeight(HEIGHT);
        mainVBox = new VBox();
        buttonVBox = new VBox();
        mainHBox = new HBox();
        Button saveObject = new Button();
        mainHBox.getChildren().add(defaultImageView);
        mainHBox.getChildren().add(buttonVBox);
        mainVBox.getChildren().add(mainHBox);
        mainVBox.getChildren().add(saveObject);
        createInputFields();
        this.getChildren().add(mainVBox);
    }

    //
    private void createInputFields(){
        for (String key: myResource.keySet()){
            String attributeName = myResource.getString(key);
            HBox attributeHBox = new HBox();
            Label attributeLabel = new Label(attributeName);
            TextField attributeField = new TextField();
            attributeHBox.getChildren().add(attributeLabel);
            attributeHBox.getChildren().add(attributeField);
            buttonVBox.getChildren().add(attributeHBox);
        }
    }

}
