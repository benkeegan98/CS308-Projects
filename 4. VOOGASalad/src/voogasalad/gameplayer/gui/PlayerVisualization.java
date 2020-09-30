package voogasalad.gameplayer.gui;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameplayer.gui.components.*;
import voogasalad.gameplayer.Player;
import voogasalad.gameplayer.gui.components.button.ButtonController;
import voogasalad.gameplayer.gui.components.button.ButtonCreator;

import java.io.File;
import java.util.*;


/**
 * @author Ben Keegan - bk142, Sumer Vardhan, Michael Castro
 *
 * PlayerVisualization is used to initialize the Player Screen. It needs the stage to build on, a UIActionsProcessor
 * for processing UI actions, the Player object which contains the Game Loop, as well as the game title and initial score.
 *
 */
public class PlayerVisualization extends BorderPane {

    private static final String RESOURCE_PATH = "resources.player.PlayerViewOptions";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private static final String SOUND_BASE_PATH = "sounds/";
    private static final double SCENE_WIDTH = Double.parseDouble(resourceBundle.getString("SceneWidth"));
    private static final double SCENE_HEIGHT = Double.parseDouble(resourceBundle.getString("SceneHeight"));
    private static final double PANEL_POSITION = Double.parseDouble(resourceBundle.getString("RightPanelPosition"));
    private static final String TITLE = resourceBundle.getString("Title");
    private static final int STOPWATCH_FONT_SIZE = Integer.parseInt(resourceBundle.getString("StatusBarFontSize"));
    private static final String INITIAL_TIME = resourceBundle.getString("InitialTime");
    private static final double SHADOW_COLOR = Double.parseDouble(resourceBundle.getString("TimeShadowColor"));
    private static final double SHADOW_YSET = Double.parseDouble(resourceBundle.getString("TimeShadowYOffset"));
    private static final String BACK_TO_GAE = resourceBundle.getString("BackToGAE");
    private static final String INSTRUCTIONS = resourceBundle.getString("Instructions");
    private static final int PANEL_SPACING = Integer.parseInt(resourceBundle.getString("InfoBoxSpacing"));
    private static final double GAEBUTTON_WIDTH = Double.parseDouble(resourceBundle.getString("BackToGAEButtonSize"));
    private static final double TITLE_SIZE = Double.parseDouble(resourceBundle.getString("TitleSize"));
    private static final double TITLEBOX_SIZE = Double.parseDouble(resourceBundle.getString("TitleHolderSize"));
    private static final String START_BUTTON_KEY = "ToggleStart";
    private static final String MUTE_BUTTON_KEY = "ToggleMute";

    private Scene scene;
    private Stage stage;
    private DisplayScreen displayScreen;
    private MediaPlayer soundPlayer;
    private BackgroundImage backgroundImage;
    private Media backgroundSound;
    private VBox panelBox;
    private AccordionCreator accordionCreator;
    private ButtonCreator myButtonCreator;
    private StatusBar statusBar;
    private SelectedTowerPane selectedTowerPane;
    private ActionsProcessor actionsProcessor;
    private StopWatch myStopWatch;
    private Text myStopWatchDisplay;
    private Player myPlayer;
    private boolean isRunning;
    private boolean isMuted;
    private String currentTime;
    private String gameTitle;
    private int gameScore;

    public PlayerVisualization(Stage stage, ActionsProcessor uiActionsProcessor, Player player, String title, Integer score) {
        this.stage = stage;
        this.actionsProcessor = uiActionsProcessor;
        this.myPlayer = player;
        this.isRunning = true;
        this.gameTitle = title;
        this.gameScore = score;
        currentTime = INITIAL_TIME;
        initialize();
    }

    /**
     * Method used for updating the game each keyframe. Updates display screen with current on screen sprites. Updates display
     * info in the status bar, including resources, lives, and the stopwatch
     *
     * @param sprites - current on screen sprites to be updated with
     * @param gameInfoMap - map of game info, incl. resources and lives count with their icon image path string
     */
    public void update(List<Sprite> sprites, Map<String, Integer> gameInfoMap) {
        displayScreen.updateDisplayScreen(sprites);
        statusBar.updateDisplayedInfo(gameInfoMap);
        if(isRunning) {
            currentTime = myStopWatch.getCurrentTime();
        }
        myStopWatchDisplay.setText(currentTime);
    }

    /**
     * method used to set new level upon level change condition specified by user
     *
     * @param towers list of tower sprite prototypes available in next level
     * @param enemies list of enemy sprite prototypes in the next level
     * @param backgroundImagePath string background image path for next level
     * @param backgroundSoundPath string background sound path for next level
     * @param gameInfoMap - map of updated game info for next level, incl. resources and lives
     */
    public void setNewLevel(List<Sprite> towers, List<Sprite> enemies, String backgroundImagePath, String backgroundSoundPath, Map<String, Integer> gameInfoMap){
        myStopWatch = new StopWatch();
        statusBar.updateDisplayedInfo(gameInfoMap);
        displayScreen.updateDisplayScreen(new ArrayList<>());
        int i = 0;
        HashMap<Integer, Integer> idMap = new HashMap<>();
        for(Sprite tower : towers){
            idMap.put(i, tower.getPrototypeId());
            i++;
        }
        accordionCreator.updateAvailableTowersAndEnemies(towers, enemies, idMap);
        setBackgroundImage(backgroundImagePath);
        setBackgroundSound(backgroundSoundPath);
    }

    private void initialize() {
        myButtonCreator = new ButtonCreator(new ButtonController(this));
        accordionCreator = new AccordionCreator();
        statusBar = new StatusBar();
        selectedTowerPane = new SelectedTowerPane(actionsProcessor, myPlayer, this);
        panelBox = new VBox(PANEL_SPACING);
        panelBox.getChildren().addAll(myButtonCreator, backToGAE(), showInstructions(), accordionCreator, selectedTowerPane);
        createStopWatchDisplay();
        statusBar.getChildren().addAll(showTitle(),myStopWatchDisplay);
        this.setRight(panelBox);
        this.setTop(statusBar);
        scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
        isMuted = false;
        displayGameScreenAndAttachToAccordion();
        showStage();
    }


    private void displayGameScreenAndAttachToAccordion() {
        displayScreen = new DisplayScreen(actionsProcessor, myPlayer, selectedTowerPane, this);
        displayScreen.setMinWidth(SCENE_WIDTH - (SCENE_WIDTH - PANEL_POSITION));
        displayScreen.setMinHeight(SCENE_HEIGHT - this.getTop().getLayoutY());
        accordionCreator.attachDisplayScreen(displayScreen);
    }

    private void showStage() {
        this.setCenter(displayScreen);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();
    }

    private VBox backToGAE() {
        VBox buttonHolder = new VBox();
        Button button = new Button(BACK_TO_GAE);
        buttonHolder.getChildren().add(button);
        button.setAlignment(Pos.CENTER);
        button.setMinWidth(GAEBUTTON_WIDTH);
        return buttonHolder;
    }

    private Text showInstructions() {
        DropShadow shadow = getDropShadow();
        Text instructions = new Text();
        instructions.setText(INSTRUCTIONS);
        instructions.setFill(Color.BLACK);
        instructions.setEffect(shadow);
        return instructions;
    }

    private VBox showTitle() {
        VBox titleBox = new VBox();
        Text title = new Text(gameTitle);
        InnerShadow innerShadow = new InnerShadow();
        title.setFill(Color.SILVER);
        title.setFont(Font.font(null, FontWeight.BOLD, TITLE_SIZE));
        title.setEffect(getDropShadow());
        title.setEffect(innerShadow);
        titleBox.setMinWidth(TITLEBOX_SIZE);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        return titleBox;
    }

//    private VBox showScore() {
//        VBox scoreBox = new VBox();
//        Text score = new Text(String.valueOf(gameScore));
//        scoreBox.getChildren().add(score);
//        return scoreBox;
//    }

    private DropShadow getDropShadow() {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(SHADOW_YSET);
        shadow.setColor(Color.color(SHADOW_COLOR, SHADOW_COLOR, SHADOW_COLOR));
        return shadow;
    }

    private void createStopWatchDisplay(){
        myStopWatchDisplay = new Text(INITIAL_TIME);
        myStopWatchDisplay.setFont(new Font(STOPWATCH_FONT_SIZE));
    }

    private void setBackgroundImage(String backgroundImagePath){
        backgroundImage = new BackgroundImage(new Image(backgroundImagePath), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(SCENE_WIDTH - (SCENE_WIDTH - PANEL_POSITION), SCENE_HEIGHT, false, false, false, false));
        displayScreen.setBackground(new Background(backgroundImage));
    }

    private void setBackgroundSound(String backgroundSoundPath) {
        if(soundPlayer != null) {
            soundPlayer.pause();
        }
        backgroundSound = new Media(new File(SOUND_BASE_PATH + backgroundSoundPath).toURI().toString());
        soundPlayer = new MediaPlayer(backgroundSound);
        soundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        soundPlayer.setMute(isMuted);
    }

    /**
     * method for toggling mute action when mute button is clicked
     */
    public void toggleMuteAction() {
        isMuted = !isMuted;
        soundPlayer.setMute(isMuted);
        myButtonCreator.toggleImage(MUTE_BUTTON_KEY);
    }

    /**
     * method for toggling start action when start button is clicked. This will start the timeline, start the stopwatch, and play the sound,
     * and another click will toggle all of these off
     */
    public void toggleStartAction() {
        isRunning = !isRunning;
        myButtonCreator.toggleImage(START_BUTTON_KEY);
        if(isRunning) {
            myPlayer.startTimeLine();
            myStopWatch.startStopWatch();
            soundPlayer.play();
        } else {
            myPlayer.pauseTimeline();
            myStopWatch.pauseStopWatch();
            soundPlayer.pause();
        }
    }
}