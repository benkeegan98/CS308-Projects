package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.SaveGUIParameters;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.utilconfig.FileChooserButton;
import voogasalad.gameauthoringenvironment.gui.utilconfig.PreviewImageButton;
import voogasalad.gameauthoringenvironment.gui.utilconfig.SubmitButton;
import voogasalad.gameauthoringenvironment.gui.utilconfig.TabVBoxCreator;

import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ParameterCreator extends BorderPane{

    private static final int window_WIDTH = 500;
    private static final int window_HEIGHT = 500;
    private static final String SUBMITBUTTONCLASS = new SubmitButton().getClass().toString().split("class ")[1];
    private BorderPane root;
    private ObjectPreviewAndActive objectSpecificRoot;
    private Stage newStage;
    private String[] properties;
    private ResourceBundle paramFieldType;
    private VBox configVBox;
    private VBox previewVBox;
    private String gameObjectName;
    private List<Node> allNodes;
    private List<String> fieldTypes;
    private FieldTextReturnFactory fieldFactory;
    private List<Label> labelList;
    private List<String> labelText;
    private List<String> labelValue;
    private AddToXML xmlObject;
    private LevelConfigPane levelConfigPane;
    private Map<String, Map<String, String>> allActiveObjects;
    private ClearFieldsFactory clearFieldsFactory;
    private List<ObjectPreviewAndActive> allActiveObjectObjects;
    private String imageString;
    private ImageView imageView;
    double imageViewWidth = 200;
    double imageViewHeight = 200;
    private FileChooserButton fileChooserButton;

    /**
     *
     * Incorrect user input or null input fields would cause instantiation of this class to fail
     * Depends on FieldTextReturnFactory.java, ClearFieldsFactory.java, SaveGUIParameters.java
     * Example from TabPaneCreator:
     * Tab objectTab = new Tab(key, new ParameterCreator(key,
     *                                                  typeToParams.getString(key).split(","),
     *                                                  paramFieldType,
     *                                                  levelConfigPane,
     *                                                  allActiveObjects,
     *                                                  allActiveObjectObjects));
     *
     * @param gameObjectNameParam
     * @param propertiesParam
     * @param paramFieldTypeParam
     * @param levelConfigPaneParam
     * @param allActiveObjectMapParam
     * @param allActiveObjectObjectsParam
     *
     * This class creates the BorderPane which represents Enemy,Tower,Obstacle tabs and a new instance
     * of it is called upon for every element in a properties file using reflection
     *
     * @author Marc Jabbour
     * @author Amber Johnson
     */
    public ParameterCreator(String gameObjectNameParam, String[] propertiesParam, ResourceBundle paramFieldTypeParam,
                            LevelConfigPane levelConfigPaneParam, Map<String, Map<String, String>> allActiveObjectMapParam,
                            List<ObjectPreviewAndActive> allActiveObjectObjectsParam) throws ParserConfigurationException {

        allActiveObjectObjects = allActiveObjectObjectsParam;
        allActiveObjects = allActiveObjectMapParam;
        fileChooserButton = new FileChooserButton();
        clearFieldsFactory = new ClearFieldsFactory();
        fieldFactory = new FieldTextReturnFactory();
        labelList = new ArrayList<>();
        labelText = new ArrayList<>();
        labelValue = new ArrayList<>();
        fieldTypes = new ArrayList<>();
        allNodes = new ArrayList<>();
        root = new BorderPane();
        xmlObject = new AddToXML();
        properties = propertiesParam;
        paramFieldType = paramFieldTypeParam;
        gameObjectName = gameObjectNameParam;
        levelConfigPane = levelConfigPaneParam;
        storeAllFieldTypes();
        addInputFields();
        addImagePreview();

        ScrollPane configScroll = new ScrollPane();
        configScroll.setPrefHeight(window_HEIGHT);
        configScroll.setMaxHeight(configScroll.getPrefHeight());
        configScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        configScroll.setContent(configVBox);
        this.setRight(configScroll);
        this.setLeft(previewVBox);
    }

    /**
     * @author Marc Jabbour
     * a public method to reset the contents of the Tower, Enemy, and Obstacle tabs
     */
    public void clearFields(){
        allNodes
                .stream()
                .forEach(node -> clearFieldsFactory.clearField(node));
    }

    /**
     * @author Marc Jabbour
     * a public method to set the action of the Submit button in the Tower, Enemy, and Obstacle tabs
     */
    public void createSubmitButton(){
        allNodes
                .stream()
                .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));

        SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
        String myLabel = xmlObject.addToSendToXMLMap(myGuiParameters.getMap(), gameObjectName);
        addToAppropriateField(gameObjectName, createObjectIcon(myGuiParameters.getMap(), myLabel));
    }

    //a helper method to add input fields to the appropriate VBox
    private void addInputFields() {
        configVBox = new TabVBoxCreator("Configure Parameters", 200, 20, 50, 50, 10);
        configVBox.setMaxHeight(300);

        for (int j = 0; j < properties.length; j++) {
            Label label = new Label(properties[j]); //for SaveGuiParameters
            labelList.add(label);
            labelText.add(label.getText());
            configVBox.getChildren().add(label);
            configVBox.getChildren().add(createObjectFromString(paramFieldType.getString(properties[j])));
        }
    }


    // a helper method to preview a user-selected image in the GAE GUI
    private void addImagePreview() {
        previewVBox = new TabVBoxCreator("Image Preview",200, 20, 10, 50, 50);
        for (int i = 0; i < allNodes.size(); i++) {
            Node currentNode = allNodes.get(i);
            String nodeLabel = labelText.get(i);
            if (nodeLabel.equals("ImageHeight")) {
                currentNode.setOnMouseClicked(e -> {
                    imageViewHeight = Double.parseDouble((new FieldTextReturnFactory()).getAppropriateText(currentNode));
                    System.out.println(imageViewHeight);
                });
            };
            if (nodeLabel.equals("ImageWidth")) {
                currentNode.setOnMouseClicked(e -> {
                    imageViewWidth = Double.parseDouble((new FieldTextReturnFactory()).getAppropriateText(currentNode));
                    System.out.println(imageViewWidth);
                });
            };
            if (currentNode instanceof FileChooserButton) {
                fileChooserButton = (FileChooserButton) currentNode;
            }
            if (currentNode instanceof PreviewImageButton) {
                PreviewImageButton button = (PreviewImageButton) currentNode;
                button.setOnAction(event -> {
                    if (imageString == null) {
                        setImageSpecs();
                    }
                    else {
                        imageView.setImage(null);
                        setImageSpecs();
                    }
                });
            }
        }
    }

    // a helper method to format the ImageView and add it to the appropriate VBox
    private void setImageSpecs() {
        imageString = fileChooserButton.getImageString();
        imageView = new ImageView(imageString);
        imageView.setX(50);
        imageView.setY(100);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageViewHeight);
        imageView.setFitWidth(imageViewWidth);
        previewVBox.getChildren().add(imageView);
    }

    //a helper method to implement reflection
    private Node createObjectFromString(String type){
        try{
            if (type.equals(SUBMITBUTTONCLASS)) {
                SubmitButton myField = new SubmitButton(this);
                return myField;
            }
            else {
                Class cls = Class.forName(type);
                Node myField = (Node) cls.getConstructor().newInstance();
                allNodes.add(myField);
                return myField;
            }

        } catch (IllegalAccessException e) {
            throw new Error(e);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        } catch (ClassNotFoundException e) {
            throw new Error(e);
        } catch (InstantiationException e) {
            throw new Error(e);
        } catch (InvocationTargetException e) {
            throw new Error(e);
        }
    }

    //a helper method to add the input field type (ComboBox, TextField, etc.) to a List
    private void storeAllFieldTypes(){
        for(String key : paramFieldType.keySet()){
            String typesOfFields = paramFieldType.getString(key);
            if(!(fieldTypes.contains(typesOfFields))){
                fieldTypes.add(typesOfFields);
            }
        }
    }

    //a helper method to make a button for the new Tower, Enemy, or Obstacle
    private Button createObjectIcon(Map<String, String> objectContentMap, String objectName){
        Button icon = new Button(objectName);
        icon.setOnMouseClicked(event -> {
            newStage = new Stage();
            ObjectPreviewAndActive createdObject = new ObjectPreviewAndActive(objectName, objectContentMap, window_HEIGHT, window_WIDTH, newStage, allActiveObjects, icon);
            objectSpecificRoot = createdObject;
            allActiveObjectObjects.add(createdObject);
            Scene newScene = new Scene(objectSpecificRoot, window_WIDTH, window_HEIGHT);
            newStage.setScene(newScene);
            newStage.show();
        });

        return icon;
    }

    //a helper method to add the Tower, Enemy, or Obstacle button to the Level tab
    private void addToAppropriateField(String gameObjectNameParam, Button icon){
        levelConfigPane.addIconToVBox(gameObjectNameParam, icon);
    }

}

