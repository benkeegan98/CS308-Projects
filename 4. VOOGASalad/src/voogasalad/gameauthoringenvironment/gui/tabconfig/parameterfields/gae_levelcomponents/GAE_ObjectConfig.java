package gae_gui.gae_levelcomponents;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class GAE_ObjectConfig extends Group {
    private String towerResourcesPath = "gae_gui.gaeresource.";
    private ResourceBundle myProperties;
    private String tabName;
    private VBox mainVBox ;
    private HBox mainHBox;
    private VBox buttonVBox;
    private Image defaultObjectImage;
    private ImageView defaultObjectImageView;
    public GAE_ObjectConfig(String tabLabel, ResourceBundle myProperties ){
        this.myProperties = ResourceBundle.getBundle(towerResourcesPath+"towerAttributes.properties");
        defaultObjectImage = new Image(this.getClass().getClassLoader().getResourceAsStream("defaultObjectImage.png"));
        defaultObjectImageView = new ImageView(defaultObjectImage);
        defaultObjectImageView.setFitWidth(100);
        defaultObjectImageView.setFitHeight(100);
        mainVBox = new VBox();
        buttonVBox = new VBox();
        mainHBox = new HBox();
        Button saveObject = new Button();
        mainHBox.getChildren().add(defaultObjectImageView);
        mainHBox.getChildren().add(buttonVBox);
        mainVBox.getChildren().add(mainHBox);
        mainVBox.getChildren().add(saveObject);
        createInputFields();


    }

    private void createInputFields(){
        for (String key: myProperties.keySet()){
            String attributeName = myProperties.getString(key);
            HBox attributeHBox = new HBox();
            Label attributeLabel = new Label(attributeName);
            TextField attributeField = new TextField();
            attributeHBox.getChildren().add(attributeLabel);
            attributeHBox.getChildren().add(attributeField);
            buttonVBox.getChildren().add(attributeHBox);
        }


    }


}
