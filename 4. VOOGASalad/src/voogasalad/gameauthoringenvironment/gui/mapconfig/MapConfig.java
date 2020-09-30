package voogasalad.gameauthoringenvironment.gui.mapconfig;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.MapButton;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapConfig {

    private static final int window_WIDTH = 1400;
    private static final int window_HEIGHT = 900;
    //private Group root;
    private BorderPane root;
    private Group subRoot;

    private Scene levelConfigScene;
    private SubScene playerField;

    private Stage levelConfigPage;
    private String towerResourcesPath = "gae_gui.gaeresource.";
    private int numberOfPaths = 0;
    private boolean pathCreation = false;
    private boolean settingSpawnPoint = false;
    private Label spawnPointLabel;
    private ArrayList<ArrayList<Pair<Double,Double>>> createdPathList;
    private ArrayList<Pair<Double,Double>> enemyPath;
    private ArrayList<Pair<Double,Double>> spawnPointList;
    private ArrayList<HBox> pathHBoxList;
    private ArrayList<Button> pathButtonList;

    private ArrayList<Label> createdPathLabel;
    private ArrayList<Label> createdSpawnPointsLabel;

    private ArrayList<ImageView> spawnPointViewList;
    private ArrayList<ArrayList<ImageView>> pathPointViewList;
    private ArrayList<ArrayList<ImageView>> pathViewList;
    private ArrayList<ArrayList<ImageView>> spawnPathViewList;


    private Pair<Double,Double> spawnPoint;
    private int selectedPathIndex = 0;
    private int selectedWaveIndex = 0;
    private List<Integer> activeEnemyList ;
    private ArrayList<ArrayList<Integer>> waveComposition;
    private ArrayList<Label> waveCompositionLabel;
    private ArrayList<HBox> waveHBoxList;
    private ArrayList<Button> waveButtonList;
    private ArrayList<ComboBox> wavePathOptions;
    private ObservableList<String> availablePathOptions;


    private HBox mainhbox;
    private VBox controlVBox;
    private int yPosition = 10;
    private int xPosition = window_WIDTH - 160;
    private int spacing = 40;
    private int waveCount = 0;
    //private ImageView spawnPointImageView;
    private Image spawnPointImage;
    private MapButton mapButtonInLevel;
    private Image pathPointImage;
    private Image defaulBackGroundImage;
    private ImageView backgroundImageView;
    private Button pathEditingButton;
    private final static Font titleFont = new Font("Arial", 18);
    private final static Font subtitleFont = new Font("Arial", 14);

    //saved data
    ArrayList<WaveInfo> savedWaves;
    ArrayList<PathInfo> savedPaths;

    ArrayList<Double> spawningTimeList;
    ArrayList<Double> durationList;
    ArrayList<Integer> waveToPathList;

    public MapConfig(MapButton mapButton, List<Integer> listOfActiveEnemies){
        mapButtonInLevel = mapButton;
        activeEnemyList = listOfActiveEnemies;
        waveComposition = new ArrayList<>();
        waveCompositionLabel = new ArrayList<>();

        spawnPointList = new ArrayList<>();
        createdPathList = new ArrayList<>();
        createdPathLabel = new ArrayList<>();
        createdSpawnPointsLabel = new ArrayList<>();
        pathHBoxList = new ArrayList<>();
        pathButtonList = new ArrayList<>();

        waveHBoxList = new ArrayList<>();
        waveButtonList = new ArrayList<>();
        wavePathOptions = new ArrayList<>();
        spawnPointViewList = new ArrayList<>();
        spawnPathViewList = new ArrayList<>();
        pathPointViewList = new ArrayList<>();
        pathViewList = new ArrayList<>();
        spawningTimeList = new ArrayList<>();
        durationList = new ArrayList<>();
        waveToPathList = new ArrayList<>();

        spawnPointImage = new Image(this.getClass().getClassLoader().getResourceAsStream("spawnPoint.jpg"));
        //spawnPointImageView = new ImageView(spawnPointImage);
        pathPointImage = new Image(this.getClass().getClassLoader().getResourceAsStream("pathUnit.png"));
        defaulBackGroundImage = new Image(this.getClass().getClassLoader().getResourceAsStream("defaultBG.jpg"));
        backgroundImageView = new ImageView(defaulBackGroundImage);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(800);

        levelConfigPage = new Stage();
        root = new BorderPane();
        Label previewLabel = new Label("Map and Path Preview");
        previewLabel.setFont(titleFont);
        root.setTop(previewLabel);
        //root = new Group();
        mainhbox = new HBox(10);
        controlVBox = new VBox(10);
        //addNewRouteButton();
        addSelectBackgroundButton();
        addPathCreationVBox();

        //addLabel();
        addInputField();
        addWaveEditingVBox();
        //addTowerTypeDropdown();

        //create subscene root
        subRoot = new Group();
        //subRoot = new HBox(10);
        subRoot.getChildren().add(backgroundImageView);

        playerField = new SubScene(subRoot, 800, 800);
        playerField.setLayoutX(10);
        playerField.setLayoutY(10);
        playerField.setOnMouseClicked(e -> handleMouseClickedSubScene(e.getX(),e.getY()));
        //mainhbox.getChildren().add(playerField);
        //mainhbox.getChildren().add(buttonsvbox);
        //root.getChildren().add(mainhbox);

        root.setCenter(playerField);
        //root.getChildren().add(playerField);
        //root.getChildren().add(spawnPointImageView);
        controlVBox.getChildren().add(createSubmitAndCloseButton());
        root.setRight(controlVBox);


        levelConfigScene = new Scene(root, window_WIDTH, window_HEIGHT);
        levelConfigPage.setTitle("New Map Configuration");
        levelConfigPage.setScene(levelConfigScene);
        levelConfigPage.show();

    }

    private void handleMouseClickedSubScene(double xCoordinate, double yCoordinate){
        if (pathCreation) {
            //createdPathList.get(selectedPathIndex).add(new Pair(xCoordinate,yCoordinate));
            enemyPath.add(new Pair(xCoordinate,yCoordinate));
            ImageView pathPointImageView = new ImageView(pathPointImage);
            ImageView newPathUnit = new ImageView(pathPointImage);


            pathPointImageView.setFitHeight(20);
            pathPointImageView.setFitWidth(20);
            pathPointImageView.setX(xCoordinate);
            pathPointImageView.setY(yCoordinate);

            subRoot.getChildren().add(pathPointImageView);

            createdPathLabel.get(selectedPathIndex).setText(listOfPointsToString(enemyPath));
            pathPointViewList.get(selectedPathIndex).add(pathPointImageView);
            visualizePath();



        }

        if (settingSpawnPoint) {
            ImageView spawnPointImageView = new ImageView(spawnPointImage);

            spawnPoint = new Pair(xCoordinate,yCoordinate);
            spawnPointImageView.setX(xCoordinate);
            spawnPointImageView.setY(yCoordinate);
            spawnPointImageView.setFitHeight(20);
            spawnPointImageView.setFitWidth(20);



            if (selectedPathIndex < spawnPointList.size()) {
                removeSelectedSpawnView(selectedPathIndex);
                spawnPointList.set(selectedPathIndex, spawnPoint);
                spawnPointViewList.set(selectedPathIndex,spawnPointImageView);
                spawnPathViewList.get(selectedPathIndex).clear();


            } else {
                spawnPointList.add(spawnPoint);
                spawnPointViewList.add(spawnPointImageView);
            }
            if (pathPointViewList.get(selectedPathIndex).size()>0) {
                visualizeSegment(spawnPointImageView, pathPointViewList.get(selectedPathIndex).get(0),6,true);
            }
            subRoot.getChildren().add(spawnPointImageView);

            createdSpawnPointsLabel.get(selectedPathIndex).setText(pointToString(spawnPoint));

            settingSpawnPoint = false;
        }

    }

    private void visualizePath() {


        if (pathPointViewList.get(selectedPathIndex).size() >= 1) {
            int startingIndex;
            if (selectedPathIndex < spawnPointViewList.size()) {
                if (spawnPointViewList.get(selectedPathIndex) != null) {
                    startingIndex = 0;
                } else {
                    startingIndex = 1;
                }
            } else {
                startingIndex = 1;
            }
            //System.out.println("Visualization "+ startingIndex);

            //System.out.println("if statemen is " + (pathPointViewList.get(selectedPathIndex).size() >= 2|| startingIndex==0));

            if (pathPointViewList.get(selectedPathIndex).size() >= 2|| startingIndex==0) {
                for (int pathPointIndex = startingIndex; pathPointIndex < pathPointViewList.get(selectedPathIndex).size(); pathPointIndex++) {
                    ImageView startingPoint;
                    ImageView endingPoint;
                    boolean fromSpawn = false;
                    if (pathPointIndex == 0) {
                        fromSpawn = true;
                        startingPoint = spawnPointViewList.get(selectedPathIndex);
                        endingPoint = pathPointViewList.get(selectedPathIndex).get(0);
                    } else {
                        startingPoint = pathPointViewList.get(selectedPathIndex).get(pathPointIndex - 1);
                        endingPoint = pathPointViewList.get(selectedPathIndex).get(pathPointIndex);
                    }
                    //System.out.println("checkpoint "+ fromSpawn);

                    visualizeSegment(startingPoint, endingPoint, 6, fromSpawn);

                }
            }




        }
    }

    private void visualizeSegment(ImageView startingImage, ImageView endingImage, double distanceIncrement, boolean isFromSpawnPoint) {
        double initialXCoordinate = startingImage.getX();
        double initialYCoordinate = startingImage.getY();
        double finalXCoordinate = endingImage.getX();
        double finalYCoordinate = endingImage.getY();
        double xDifference = finalXCoordinate - initialXCoordinate;
        double yDifference = finalYCoordinate - initialYCoordinate;
        double distance = Math.sqrt(Math.pow(xDifference,2) + Math.pow(yDifference,2));
        double myAngle =Math.atan(yDifference/xDifference);

        for (double distanceTraveled = 6; distanceTraveled < distance; distanceTraveled=distanceTraveled+distanceIncrement) {

            ImageView newPathUnit = new ImageView(pathPointImage);
            double yCoordinate;
            double xCoordinate;
            if (xDifference > 0) {
                yCoordinate = initialYCoordinate + Math.sin(myAngle) * distanceTraveled; // this is wrong. need to have a field for angle
                xCoordinate = initialXCoordinate + Math.cos(myAngle) * distanceTraveled;
            } else {
                yCoordinate = initialYCoordinate - Math.sin(myAngle) * distanceTraveled;
                xCoordinate = initialXCoordinate - Math.cos(myAngle) * distanceTraveled;
            }
            newPathUnit.setFitWidth(20);
            newPathUnit.setFitHeight(20);
            newPathUnit.setX(xCoordinate);
            newPathUnit.setY(yCoordinate);
            newPathUnit.toFront();
            subRoot.getChildren().add(newPathUnit);
            if (isFromSpawnPoint){
                spawnPathViewList.get(selectedPathIndex).add(newPathUnit);

            } else {
                pathViewList.get(selectedPathIndex).add(newPathUnit);

            }
            //nextLocation(initialXCoordinate,initialYCoordinate, myAngle, distanceTraveled);

        }
    }


    private void addPathCreationVBox(){
        VBox pathVBox = new VBox(10);
        Label vBoxTitle = new Label("Create And Edit Your Path");
        vBoxTitle.setFont(titleFont);
        ScrollPane pathScrollPane = new ScrollPane();
        FlowPane pathFlowPane = new FlowPane();

        HBox flowPaneLabelHBox = new HBox(20);
        Label pathIndexLabel = new Label ("Select Path To Edit");
        Label pathCoordinateLabel = new Label("Path Points List");
        Label spawnPointCoordinateLabel = new Label("Spawn Point Coordinate");
        pathFlowPane.getChildren().add(spawnPointCoordinateLabel);
        flowPaneLabelHBox.getChildren().add(pathIndexLabel);

        flowPaneLabelHBox.getChildren().add(spawnPointCoordinateLabel);
        flowPaneLabelHBox.getChildren().add(pathCoordinateLabel);
        pathFlowPane.getChildren().add(flowPaneLabelHBox);
        pathScrollPane.setContent(pathFlowPane);
        pathScrollPane.setMaxHeight(150);
        HBox pathButtonHBox = new HBox(10);
        addCreateNewRouteButton(pathButtonHBox, pathFlowPane);
        addSpawnPointButton(pathButtonHBox);
        addPathSelectionButton(pathButtonHBox);
        addPathDeletionButton(pathButtonHBox, pathFlowPane);

        pathVBox.getChildren().add(vBoxTitle);
        pathVBox.getChildren().add(pathButtonHBox);
        pathVBox.getChildren().add(pathScrollPane);
        controlVBox.getChildren().add(pathVBox);

    }

    private void addCreateNewRouteButton(HBox myHBox, FlowPane pathPane){
        Button creatNewRouteButton = new Button("Add A New Path");
        creatNewRouteButton.setOnAction(e -> addNewPathField(pathPane));
        myHBox.getChildren().add(creatNewRouteButton);
    }

    private void changeHighLightedButton(Button nextActivatedButton, Boolean isPathButton) {
        Button lastActiveButton;
        if (isPathButton) {
            lastActiveButton = pathButtonList.get(selectedPathIndex);
        } else {
            lastActiveButton = waveButtonList.get(selectedWaveIndex);
        }
        lastActiveButton.setStyle(null);
        nextActivatedButton.setStyle("-fx-background-color: orange; -fx-border-color: red;");
    }

    private void addNewPathField(FlowPane pathPane){
        HBox newPathHBox = new HBox(75);
        newPathHBox.setId(Integer.toString(numberOfPaths));//Id =number -1
        numberOfPaths ++;

        ArrayList<Pair<Double,Double>> newPath = new ArrayList<>();
        createdPathList.add(newPath);
        Pair<Double,Double> newPoint = new Pair<Double,Double>(0.0,0.0);
        spawnPointList.add(newPoint);
        ArrayList<ImageView> newViewList = new ArrayList<>();
        spawnPointViewList.add(null);
        spawnPathViewList.add(new ArrayList<>());
        pathPointViewList.add(new ArrayList<>());
        pathViewList.add(new ArrayList<>());

        Button pathNameButton = new Button("Path " + numberOfPaths);
        pathNameButton.setOnAction(e-> {
            changeSelectedPath(newPathHBox);

        });

        Label pathListLabel = new Label("Click Start Editing Path Button then Click on Map To Add Points In Path");
        ScrollPane pathScrollList = new ScrollPane(pathListLabel);
        pathScrollList.setMaxWidth(120);


        Label spawnPointLabel = new Label("To Be Set");
        createdPathLabel.add(pathListLabel);
        createdSpawnPointsLabel.add(spawnPointLabel);
        pathButtonList.add(pathNameButton);

        newPathHBox.getChildren().add(pathNameButton);
        newPathHBox.getChildren().add(spawnPointLabel);
        newPathHBox.getChildren().add(pathScrollList);
        //root.getChildren().add(newWaveHBox);
        newPathHBox.setOnMouseClicked(e->changeSelectedPath(newPathHBox));

        pathPane.getChildren().add(newPathHBox);
        pathHBoxList.add(newPathHBox);
        changeSelectedPath(newPathHBox);

        updateAvailablePaths();


    }

    private void changeSelectedPath(HBox pathHBox){

        removeSelectedPathView(selectedPathIndex);
        if (!spawnPointViewList.isEmpty()) {
            removeSelectedSpawnView(selectedPathIndex);
        }
        int newPathIndex = Integer.parseInt(pathHBox.getId());
        changeHighLightedButton(pathButtonList.get(newPathIndex),true);
        selectedPathIndex=newPathIndex;
        if (pathCreation == true) {
            pathEditingButton.fire();
        }


        for (ImageView image : pathPointViewList.get(selectedPathIndex)) {
            subRoot.getChildren().add(image);
        }
        for (ImageView image : pathViewList.get(selectedPathIndex)) {
            subRoot.getChildren().add(image);
        }

        for (ImageView image : spawnPathViewList.get(selectedPathIndex)) {
            subRoot.getChildren().add(image);
        }

        if (!spawnPointViewList.isEmpty()) {
            if (!subRoot.getChildren().isEmpty()) {
                if (spawnPointViewList.get(selectedPathIndex) != null) {
                    if (!subRoot.getChildren().contains(spawnPointViewList.get(selectedPathIndex))) {
                        subRoot.getChildren().add(spawnPointViewList.get(selectedPathIndex));
                    }
                }
                //pathHBox.setBorder(highLightBorder);
            }
        }

    }

    private void deleteSelectedPath(FlowPane pathPane){
        if ((selectedPathIndex < numberOfPaths-1) && numberOfPaths>0){


            numberOfPaths--;
            removeSelectedSpawnView(selectedPathIndex);
            removeSelectedPathView(selectedPathIndex);
            pathPane.getChildren().remove(pathHBoxList.get(selectedPathIndex));
            pathPointViewList.remove(selectedPathIndex);
            pathViewList.remove(selectedPathIndex);
            spawnPointViewList.remove(selectedPathIndex);
            spawnPathViewList.remove(selectedPathIndex);
            createdPathList.remove(selectedPathIndex);
            createdPathLabel.remove(selectedPathIndex);
            createdSpawnPointsLabel.remove(selectedPathIndex);
            spawnPointList.remove(selectedPathIndex);
            pathHBoxList.remove(selectedPathIndex);
            pathButtonList.remove(selectedPathIndex);


            for (int index = 0; index < pathHBoxList.size();index++) {

                int ID = Integer.parseInt(pathHBoxList.get(index).getId());

                if (ID > selectedPathIndex) {
                    int newButtonNumber = ID;
                    ID--;
                    pathHBoxList.get(index).setId(Integer.toString(ID));

                    pathButtonList.get(index).setText("Path " + newButtonNumber);



                }


            }


            updateAvailablePaths();
            changeSelectedPath( pathHBoxList.get(pathHBoxList.size()-1));
        }




    }

    private void removeSelectedPathView(int pathIndex){
        for (ImageView pathImage : pathPointViewList.get(pathIndex)){
            if (subRoot.getChildren().contains(pathImage)){
                subRoot.getChildren().remove(pathImage);
            }
        }
        for (ImageView pathImage : pathViewList.get(pathIndex)){
            if (subRoot.getChildren().contains(pathImage)){
                subRoot.getChildren().remove(pathImage);
            }
        }
        /*for (ImageView pathImage : spawnPathViewList.get(pathIndex)){
            if (subRoot.getChildren().contains(pathImage)){
                subRoot.getChildren().remove(pathImage);
            }
        }*/
    }

    private void removeSelectedSpawnView(int pathIndex) {
        if (subRoot.getChildren().contains(spawnPointViewList.get(pathIndex))){
            subRoot.getChildren().remove(spawnPointViewList.get(pathIndex));
        }
        for (ImageView pathImage : spawnPathViewList.get(pathIndex)){
            if (subRoot.getChildren().contains(pathImage)){
                subRoot.getChildren().remove(pathImage);
            }
        }
    }


    private void addSpawnPointButton(HBox myHBox){
        Button newButton = new Button("Set Spawn Point in Selected Path");

        newButton.setOnAction(e ->{settingSpawnPoint = true;});

        myHBox.getChildren().add(newButton);
    }

    private void addPathSelectionButton(HBox myHBox){
        pathEditingButton= new Button("Define Selected Path");

        pathEditingButton.setOnAction(e ->turnOnOffPathCreation(pathEditingButton));
        /*BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(createdSpawnPointsLabel.get(selectedPathIndex).textProperty());
            }

            @Override
            protected boolean computeValue() {
                return createdSpawnPointsLabel.get(selectedPathIndex).equals("To Be Set");
            }
        };
        */


        //root.getChildren().add(newButton);
        myHBox.getChildren().add(pathEditingButton);

    }

    private void addPathDeletionButton(HBox myHBox, FlowPane pathPane) {
        Button deleteButton = new Button ("Delete Selected Path");
        deleteButton.setOnAction(e->deleteSelectedPath(pathPane));
        myHBox.getChildren().add(deleteButton);
    }


    private void addInputField(){

    }

    private void addWaveEditingVBox(){
        VBox waveVBox = new VBox(10);
        Label vBoxTitle = new Label("Create And Edit Your Wave");
        vBoxTitle.setFont(titleFont);
        HBox waveButtonHBox = new HBox(10);


        ScrollPane waveScrollPane = new ScrollPane();
        FlowPane waveFlowPane = new FlowPane();
        waveScrollPane.setContent(waveFlowPane);
        waveScrollPane.setMaxHeight(150);
        HBox waveLabelHBox = new HBox(50);
        Label selectWaveLabel = new Label("Select Wave To Edit");

        Label enemyListLabel = new Label("Enemy List");
        Label spawnTimeLabel = new Label("Spawn At Time (s)");
        Label durationLabel = new Label("Duration (s)");
        Label pathLabel = new Label("Selected Path");
        waveLabelHBox.getChildren().add(selectWaveLabel);
        waveLabelHBox.getChildren().add(enemyListLabel);
        waveLabelHBox.getChildren().add(spawnTimeLabel);
        waveLabelHBox.getChildren().add(durationLabel);
        waveLabelHBox.getChildren().add(pathLabel);
        waveFlowPane.getChildren().add(waveLabelHBox);

        HBox enemyTypeHBox = new HBox(5);
        for (int enemyIndex : activeEnemyList) {
            Button newEnemyType = new Button("Enemy Type " + enemyIndex );
            newEnemyType.setOnAction(event -> addEnemyToWave(enemyIndex));
            enemyTypeHBox.getChildren().add(newEnemyType);
        }
        Label activeEnemyLabel = new Label("List Of Active Enemy");
        activeEnemyLabel.setFont(subtitleFont);
        waveVBox.getChildren().add(vBoxTitle);
        addWaveButton(waveButtonHBox, waveFlowPane);
        addDeleteWaveButton(waveButtonHBox,waveFlowPane);
        waveVBox.getChildren().add(waveButtonHBox);
        waveVBox.getChildren().add(activeEnemyLabel);
        waveVBox.getChildren().add(enemyTypeHBox);
        waveVBox.getChildren().add(waveScrollPane);

        controlVBox.getChildren().add(waveVBox);





    }
    private void addWaveButton(HBox myHBox, FlowPane myWaveFlowPane){


        Button addWaveButton = new Button("Add A New Wave");
        addWaveButton.setLayoutX(xPosition);
        addWaveButton.setLayoutY(yPosition);
        yPosition+=spacing;
        addWaveButton.setOnAction(e->addNewWaveField(myWaveFlowPane));


        //root.getChildren().add(addWaveButton);
        //root.getChildren().add(enemyTypeHBox);
        //root.getChildren().add(waveLabelHBox);
        myHBox.getChildren().add(addWaveButton);

    }

    private void addEnemyToWave(int enemyIndex) {
        waveComposition.get(selectedWaveIndex).add(enemyIndex);
        waveCompositionLabel.get(selectedWaveIndex).setText(waveComposition.get(selectedWaveIndex).toString());

    }
    private void addNewWaveField(FlowPane myFlowPane){

        ArrayList<Integer> newWaveEnemyList = new ArrayList<>();
        HBox newWaveHBox = new HBox(70);
        newWaveHBox.setId(Integer.toString(waveCount));//ID = index in array
        waveComposition.add(newWaveEnemyList);
        //default value
        spawningTimeList.add(0.0);
        durationList.add(0.0);
        waveToPathList.add(1);


        waveCount ++;



        //Label waveNameLabel = new Label("Wave " + waveCount);
        Button waveNameLabel = new Button("Wave " + waveCount);
        waveNameLabel.setOnAction(e -> changeSelectedWave(newWaveHBox));
        waveNameLabel.setId("Wave "+ waveCount);

        Label waveEnemyListLabel = new Label("Click Active Enemy Type to Add");
        waveCompositionLabel.add(waveEnemyListLabel);

        ScrollPane waveSrollList = new ScrollPane(waveEnemyListLabel);
        waveSrollList.setMaxWidth(100);
        waveSrollList.setId("WaveList "+ waveCount);
        TextField startingTimeField = new TextField();
        startingTimeField.setOnAction(e -> spawningTimeList.set(Integer.parseInt(newWaveHBox.getId().toString()),Double.parseDouble(startingTimeField.getText())) );

        startingTimeField.setId("SpawnTime");
        TextField durationField = new TextField();
        durationField.setId("Duration");
        durationField.setOnAction(e -> durationList.set(Integer.parseInt(newWaveHBox.getId().toString()), Double.parseDouble(startingTimeField.getText()))  );

        ComboBox availablePaths = new ComboBox();
        availablePaths.setId("Path");
        availablePaths.valueProperty().addListener((o, old, neww) -> updateWaveToPath(newWaveHBox,neww.toString()));


        startingTimeField.setMaxWidth(30);
        durationField.setMaxWidth(30);

        waveButtonList.add(waveNameLabel);
        waveHBoxList.add(newWaveHBox);

        availablePathOptions = FXCollections.observableArrayList("None");
        availablePaths.setItems(availablePathOptions);

        wavePathOptions.add(availablePaths);
        updateAvailablePaths();
        newWaveHBox.getChildren().add(waveNameLabel);
        newWaveHBox.getChildren().add(waveSrollList);
        newWaveHBox.getChildren().add(startingTimeField);
        newWaveHBox.getChildren().add(durationField);
        newWaveHBox.getChildren().add(availablePaths);
        //root.getChildren().add(newWaveHBox);
        newWaveHBox.setOnMouseClicked(e-> changeSelectedWave(newWaveHBox));
        myFlowPane.getChildren().add(newWaveHBox);
        changeSelectedWave(newWaveHBox);


    }
    private void updateWaveToPath(HBox waveHBox, String newText){
        waveToPathList.set(Integer.parseInt(waveHBox.getId().toString()), Integer.parseInt(newText.split(" ")[1]));

    }

    private void updateAvailablePaths() {
        ArrayList<String> availablePathListLabel = new ArrayList<>();
        for (int index = 0 ; index < pathHBoxList.size() ; index++){
            availablePathListLabel.add(pathButtonList.get(index).getText());

        }
        availablePathOptions = FXCollections.observableArrayList(availablePathListLabel);

        for (ComboBox combo:wavePathOptions) {
            combo.setItems(availablePathOptions);
        }
    }

    private void changeSelectedWave(HBox waveHBox) {
        int newWaveIndex = Integer.parseInt(waveHBox.getId());
        changeHighLightedButton(waveButtonList.get(newWaveIndex),false);
        selectedWaveIndex = newWaveIndex;
    }

    private void addDeleteWaveButton(HBox waveButtonHBox, FlowPane wavePane) {
        Button deleteWaveButton = new Button ("Delete Selected Wave");
        deleteWaveButton.setOnAction(e->deleteSelectedWave(wavePane));
        waveButtonHBox.getChildren().add(deleteWaveButton);

    }

    private void deleteSelectedWave(FlowPane wavePane) {
        if ((selectedWaveIndex < waveCount - 1) && waveCount > 0) {
            wavePane.getChildren().remove(waveHBoxList.get(selectedWaveIndex));
            waveCount--;
            waveHBoxList.remove(selectedWaveIndex);
            waveButtonList.remove(selectedWaveIndex);
            waveComposition.remove(selectedWaveIndex);
            waveToPathList.remove(selectedWaveIndex);
            durationList.remove(selectedWaveIndex);
            spawningTimeList.remove(selectedWaveIndex);
            waveCompositionLabel.remove(selectedWaveIndex);
            wavePathOptions.remove(selectedWaveIndex);
            for (int index = 0; index< waveHBoxList.size(); index++) {
                int ID = Integer.parseInt(waveHBoxList.get(index).getId());

                if (ID > selectedWaveIndex) {
                    int newButtonNumber = ID;
                    ID--;
                    waveHBoxList.get(index).setId(Integer.toString(ID));

                    waveButtonList.get(index).setText("Wave " + newButtonNumber);



                }

            }
            changeSelectedWave(waveHBoxList.get(waveHBoxList.size()-1));
        }
    }

    private TextField createInputField(String fieldName){
        TextField newInputField = new TextField();
        newInputField.setId(fieldName);
        //newInputField.setOnAction(e -> );
        return newInputField;

    }



    private void turnOnOffPathCreation(Button myButton) {
        pathCreation = !pathCreation;
        if (pathCreation) {
            enemyPath = new ArrayList<>();
            removeSelectedPathView(selectedPathIndex);
            pathPointViewList.get(selectedPathIndex).clear();
            pathViewList.get(selectedPathIndex).clear();
            myButton.setText("Stop Editing Path ");
        } else {
            if (selectedPathIndex < createdPathList.size()) {
                createdPathList.set(selectedPathIndex, enemyPath);
            }
            myButton.setText("Edit Selected Path");
        }

    }


    private void addSelectBackgroundButton(){
        final FileChooser imageChooser = new FileChooser();
        Button newButton = new Button("Select Background Image");
        newButton.setLayoutX(window_WIDTH - 160);
        newButton.setLayoutY(yPosition);
        yPosition+=spacing;
        newButton.setId("NewRoute");
        newButton.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(levelConfigPage);
            if (file != null) {
                Image image1 = new Image(file.toURI().toString());
                backgroundImageView.setImage(image1);
            }
        });

        //root.getChildren().add(newButton);
        controlVBox.getChildren().add(newButton);
    }

    private Button createSubmitAndCloseButton(){
        Button submitClose = new Button("Submit Map");
        submitClose.setOnMouseClicked(event -> {
            savedWaves = new ArrayList<>();
            savedPaths = new ArrayList<>();

            for (int pathIndex = 0; pathIndex < createdPathList.size(); pathIndex++) {
                PathInfo newPath = new PathInfo(pathIndex, spawnPointList.get(pathIndex), createdPathList.get(pathIndex));
                //System.out.println("Save Path " + listOfPointsToString(createdPathList.get(pathIndex)) );
                savedPaths.add(newPath);
            }



            for (int waveIndex = 0; waveIndex < waveHBoxList.size(); waveIndex++) {
                double spawningTime = spawningTimeList.get(waveIndex);
                double totalWaveDuration = durationList.get(waveIndex);
                int pathIndex = waveToPathList.get(waveIndex);
                /*for (Node inputNode : waveHBoxList.get(waveIndex).getChildren()) {
                    if (inputNode instanceof TextField){
                        if (inputNode.getId().equals("SpawnTime")) {
                            spawningTime = Double.parseDouble(((TextField) inputNode).getText());
                        }
                        if (inputNode.getId().equals("Duration")) {
                            totalWaveDuration = Double.parseDouble(((TextField) inputNode).getText());
                        }
                    }
                    if (inputNode instanceof ComboBox) {
                        pathIndex =Integer.parseInt(((ComboBox) inputNode).getValue().toString().split(" ")[1]);
                    }


                } */
                WaveInfo newWave = new WaveInfo(waveIndex, waveComposition.get(waveIndex), spawningTime,totalWaveDuration, pathIndex );
                savedWaves.add(newWave);
            }
            for (PathInfo path : savedPaths) {
                path.printInfo();
            }
            for (WaveInfo wave: savedWaves) {
                wave.printInfo();
            }
            mapButtonInLevel.mapSubmitted();
            levelConfigPage.close();
        });
        return submitClose;
    }

    private String listOfPointsToString(ArrayList<Pair<Double,Double>> pointList) {
        StringBuilder newString = new StringBuilder();
        for (int pointIndex = 0 ; pointIndex < pointList.size(); pointIndex++) {
            Pair<Double,Double> pointToAdd = pointList.get(pointIndex);
            newString.append(pointToString(pointToAdd));
        }
        return newString.toString();
    }

    private String pointToString (Pair<Double,Double> newPoint) {
        StringBuilder newPointString = new StringBuilder();
        newPointString.append("(");
        newPointString.append(Math.round(newPoint.getKey()*100)/100.0);
        newPointString.append(",");
        newPointString.append(Math.round(newPoint.getValue()*100)/100.0);
        newPointString.append(") ");
        return newPointString.toString();
    }



}
