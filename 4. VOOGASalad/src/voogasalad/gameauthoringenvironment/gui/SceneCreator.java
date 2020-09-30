package voogasalad.gameauthoringenvironment.gui;

import javafx.scene.control.TabPane;
import voogasalad.gameauthoringenvironment.bus.Bus;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.gui.tabconfig.TabPaneCreator;

import java.util.ResourceBundle;

/**
 *
 */
public class SceneCreator {

    public static final String SPRITE_OPTIONS_RESOURCE = "resources.gae.tabcreation.SpriteOptions";
    public static final String PARAM_FIELD_TYPE_RESOURCE = "resources.gae.tabcreation.ParamToInputType";
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private int width;
    private int height;
    private AddToXML sendToXML;
    public Document createdXML;
    public Bus busInstance;


    /**
     * @author Marc Jabbour
     * This class is referenced in the Bus.java class and creates the GAE Scene by displaying the TabPane created by TabPaneCreator.java
     * @param widthParam
     * @param heightParam
     * @param myBusInstance
     */
    public SceneCreator(int widthParam, int heightParam, Bus myBusInstance){
        sendToXML = new AddToXML();
        busInstance = myBusInstance;
        width = widthParam;
        height = heightParam;
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS_RESOURCE);
        paramFieldType = ResourceBundle.getBundle(PARAM_FIELD_TYPE_RESOURCE);
    }

    /**
     * @author Marc Jabbour
     * Creates the GAE Scene by adding the TabPane to the root
     * @param root
     * @return
     */
    public Scene createGAEScene(BorderPane root){
        TabPane myTabPane = new TabPaneCreator(sendToXML, createdXML, busInstance).getTabPane();
        root.setTop(myTabPane);
        return new Scene(root, width,  height);
    }



    // generates an XML file from current settings when submit button is pressed
//    private Button buttonToCreateXML(){
//        Button myButton = new Button("Submit");
//        myButton.setOnMouseClicked(event -> {
//            try {
//                createdXML = sendToXML.createXML();
//                busInstance.goToPlayer(createdXML);
//
//            } catch (ParserConfigurationException | GameEngineException e) {
//                e.printStackTrace();
//            }
//        });
//        return myButton;
//    }

    public Document getCreatedXML(){
        return createdXML;
    }

}
