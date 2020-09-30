package gae_gui.gae_levelcomponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

public class GAE_LevelConfigPage {

    private static final int window_WIDTH = 1000;
    private static final int window_HEIGHT = 600;
    private Group root;
    private Group subRoot;
    private Scene levelConfigScene;
    private SubScene playerField;

    private Stage levelConfigPage;
    private String towerResourcesPath = "gae_gui.gaeresource.";
    private int numberOfPaths = 0;
    private boolean pathCreation = false;
    private boolean settingSpawnPoint = false;
    private Label spawnPointLabel;
    private ArrayList<Pair<Double,Double>> enemyPath;
    private Pair<Double,Double> spawnPoint;
    private ArrayList<Integer> activeEnemyList ;
    private ArrayList<ArrayList<Integer>> waveComposition;
    private ArrayList<Label> waveCompositionLabel;
    private HBox mainhbox;
    private VBox buttonsvbox;
    private int yPosition = 10;
    private int xPosition = window_WIDTH - 160;
    private int spacing = 40;
    private int waveCount = 0;
    private ImageView spawnPointImageView;
    private Image spawnPointImage;
    private Image defaultBackground;
    private ImageView backgroundImageView;

    private Image pathPointImage;

    public GAE_LevelConfigPage(){
        activeEnemyList = new ArrayList<>(Arrays.asList(1,2,3));
        waveComposition = new ArrayList<>();
        waveCompositionLabel = new ArrayList<>();
        spawnPointImage = new Image(this.getClass().getClassLoader().getResourceAsStream("spawnPoint.jpg"));
        spawnPointImageView = new ImageView(spawnPointImage);
        pathPointImage = new Image(this.getClass().getClassLoader().getResourceAsStream("pathPoint.png"));
        spawnPointImageView.setFitHeight(20);
        spawnPointImageView.setFitWidth(20);
        defaultBackground = new Image(this.getClass().getClassLoader().getResourceAsStream("defaultBG.jpg"));
        backgroundImageView = new ImageView(defaultBackground);
        levelConfigPage = new Stage();
        //root = new GridPane();
        root = new Group();
        mainhbox = new HBox(10);
        buttonsvbox = new VBox(10);
        //addNewRouteButton();
        addSelectBackgroundButton();
        createNewRoute();

        //addLabel();
        addInputField();
        addWaveButton();
        //addTowerTypeDropdown();



        //create subscene root
        subRoot = new Group();
        subRoot.getChildren().add(backgroundImageView);
        playerField = new SubScene(subRoot, 500, 500);

        playerField.setLayoutX(10);
        playerField.setLayoutY(10);
        playerField.setOnMouseClicked(e -> handleMouseClickedSubScene(e.getX(),e.getY()));
        //mainhbox.getChildren().add(playerField);
        //mainhbox.getChildren().add(buttonsvbox);
        //root.getChildren().add(mainhbox);

        root.getChildren().add(playerField);

        root.getChildren().add(spawnPointImageView);

        levelConfigScene = new Scene(root, window_WIDTH, window_HEIGHT);
        levelConfigPage.setTitle("New Level Configuration");
        levelConfigPage.setScene(levelConfigScene);
        levelConfigPage.show();

    }

    private void handleMouseClickedSubScene(double xCoordinate, double yCoordinate){
        if (pathCreation) {
            enemyPath.add(new Pair(xCoordinate,yCoordinate));
            ImageView pathPointImageView = new ImageView(pathPointImage);
            pathPointImageView.setFitHeight(20);
            pathPointImageView.setFitWidth(20);
            pathPointImageView.setX(xCoordinate);
            pathPointImageView.setY(yCoordinate);
            root.getChildren().add(pathPointImageView);
            System.out.println("x coordinate " + xCoordinate +" y coordinate " + yCoordinate);

        }

        if (settingSpawnPoint) {
            spawnPoint = new Pair(xCoordinate,yCoordinate);
            spawnPointLabel.setText(spawnPoint.toString());
            spawnPointImageView.setX(xCoordinate);
            spawnPointImageView.setY(yCoordinate);

            settingSpawnPoint = false;
        }

    }

    private void addInputField(){

    }
    private void addWaveButton(){
        HBox enemyTypeHBox = new HBox(5);
        for (int enemyIndex : activeEnemyList) {
            Button newEnemyType = new Button("Enemy Type " + enemyIndex );
            newEnemyType.setOnAction(event -> addEnemyToWave(enemyIndex));
            enemyTypeHBox.getChildren().add(newEnemyType);
        }
        enemyTypeHBox.setLayoutX(xPosition-140);
        enemyTypeHBox.setLayoutY(yPosition);
        yPosition+=spacing;
        root.getChildren().add(enemyTypeHBox);

        Button addWaveButton = new Button("Add A New Wave");
        addWaveButton.setLayoutX(xPosition);
        addWaveButton.setLayoutY(yPosition);
        yPosition+=spacing;
        addWaveButton.setOnAction(e->addNewWaveField());
        root.getChildren().add(addWaveButton);
        HBox waveLabelHBox = new HBox(80);
        waveLabelHBox.setLayoutX(xPosition-300);
        waveLabelHBox.setLayoutY(yPosition);
        yPosition+=spacing;
        Label enemyListLabel = new Label("Enemy List");
        Label spawnTimeLabel = new Label("Spawn Time");
        Label durationLabel = new Label("Duration");
        waveLabelHBox.getChildren().add(enemyListLabel);
        waveLabelHBox.getChildren().add(spawnTimeLabel);
        waveLabelHBox.getChildren().add(durationLabel);
        root.getChildren().add(waveLabelHBox);
    }

    private void addEnemyToWave(int enemyIndex) {
        waveComposition.get(waveCount-1).add(enemyIndex);
        waveCompositionLabel.get(waveCount-1).setText(waveComposition.get(waveCount-1).toString());

    }
    private void addNewWaveField(){
        ArrayList<Integer> newWaveEnemyList = new ArrayList<>();
        waveComposition.add(newWaveEnemyList);
        waveCount ++;
        HBox newWaveHBox = new HBox(5);
        newWaveHBox.setLayoutY(yPosition);
        yPosition+=spacing;
        newWaveHBox.setLayoutX(xPosition-320);
        Label waveNameLabel = new Label("Wave " + waveCount);
        Label waveEnemyListLabel = new Label("Click Enemy Type to Add");
        waveCompositionLabel.add(waveEnemyListLabel);
        TextField startingTimeField = new TextField();
        TextField durationField = new TextField();
        newWaveHBox.getChildren().add(waveNameLabel);
        newWaveHBox.getChildren().add(waveEnemyListLabel);
        newWaveHBox.getChildren().add(startingTimeField);
        newWaveHBox.getChildren().add(durationField);
        root.getChildren().add(newWaveHBox);



    }


    private TextField createInputField(String fieldName){
        TextField newInputField = new TextField();
        newInputField.setId(fieldName);
        //newInputField.setOnAction(e -> );
        return newInputField;

    }

    private void addNewRouteButton(){
        Button applyButton = new Button("Add New Route");
        applyButton.setId("NewRoute");
        applyButton.setOnAction(e ->createNewRoute());
        //root.getChildren().add(applyButton);
        buttonsvbox.getChildren().add(applyButton);
    }


    private void createNewRoute(){
        numberOfPaths ++;
        addSpawnPointButton();
        addPathSelectionButton();
    }

    private void addSpawnPointButton(){
        Button newButton = new Button("Add New Spawn Point");
        newButton.setLayoutX(window_WIDTH - 160 );
        newButton.setLayoutY(yPosition);
        yPosition+= spacing;
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->settingSpawnPoint = true);
        spawnPointLabel = new Label("To Be Set");
        spawnPointLabel.setLayoutX(window_WIDTH - 220);
        spawnPointLabel.setLayoutY(10);
        root.getChildren().add(spawnPointLabel);
        root.getChildren().add(newButton);
        //root.getChildren().add(spawnPointLabel);
        //buttonsvbox.getChildren().add(newButton);
    }

    private void turnOnOffPathCreation(Button myButton) {
        pathCreation = !pathCreation;
        if (pathCreation) {
            enemyPath = new ArrayList<>();
            myButton.setText("Stop Making Path ");
        } else {
            myButton.setText("Add New Path");
        }

    }

    private void addPathSelectionButton(){
        Button newButton = new Button("Start Making New Path");
        newButton.setLayoutX(window_WIDTH - 160);
        newButton.setLayoutY(yPosition);
        yPosition+=spacing;
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->turnOnOffPathCreation(newButton));
        root.getChildren().add(newButton);
        //buttonsvbox.getChildren().add(newButton);

    }

    private void addSelectBackgroundButton(){
        final FileChooser imageChooser = new FileChooser();
        Button newButton = new Button("Select Background Image");
        newButton.setLayoutX(window_WIDTH - 160);
        newButton.setLayoutY(yPosition);
        yPosition+=spacing;
        newButton.setId("NewRoute");
        newButton.setOnAction(e ->createNewRoute());
        newButton.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(levelConfigPage);
            if (file != null) {
                Image image1 = new Image(file.toURI().toString());
                backgroundImageView.setImage(image1);
                backgroundImageView.setFitWidth(500);
                backgroundImageView.setFitHeight(500);
                //backgroundImageView.setImage();
            }
        });

        root.getChildren().add(newButton);
        //buttonsvbox.getChildren().add(newButton);
    }


}
