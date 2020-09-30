package voogasalad.gameauthoringenvironment.bus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.xml.sax.SAXException;
import voogasalad.gameplayer.gui.components.ErrorPane;
import voogasalad.gameauthoringenvironment.gui.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameplayer.Player;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class Bus {

    private final static double IMAGE_HEIGHT = 50;
    private final static double LOGO_WIDTH = 370;
    private final static double LOGO_HEIGHT = 350;
    private final String WRONG_FILE_TYPE_ERROR = "UploadedWrongFileType";

  //  private final static int GAE_WIDTH = 800;
   // private final static int GAE_HEIGHT = 500;


    private Stage currentStage;

    private int width;
    private int height;
    private BorderPane root;
    private Scene busScene;
    private Scene gaeScene;
    private Scene gamePlayerScene;
    private SceneCreator gaeObject;
    private Document createdXML;
    private VBox busRoot;
    private ErrorPane errorPane;

    public Bus(Stage currentStageParam, BorderPane rootParam, int widthParam, int heightParam){
        currentStage = currentStageParam;
        root = rootParam;
        width = widthParam;
        height = heightParam;
        gaeObject = new SceneCreator(widthParam, heightParam, this);
        errorPane = new ErrorPane();
    }

    /**
     * This method returns the initial screen we see when we load up the game
     */
    public Scene getBusScene() {
        return createBusScene();
    }

    private Scene createBusScene() {
        busRoot = new VBox();
        busRoot.setAlignment(Pos.CENTER);
        busRoot.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        ImageView logo = new ImageView("TDLogo.png");
        logo.setFitWidth(LOGO_WIDTH);
        logo.setFitHeight(LOGO_HEIGHT);
        busRoot.getChildren().add(logo);
        busRoot.getChildren().add(createMenuButton("new-game.png", "new-game-hover.png", e -> changeToGAE()));
        busRoot.getChildren().add(createMenuButton("load-game.png", "load-game-hover.png", e -> {
            try {
                loadGameHandler();
            } catch (GameEngineException ex) {
                System.out.println(ex.getMessage());
                //TODO: catch this GameEngineException
            }
        }));
        return new Scene(busRoot,width,height);
    }

//    private Label changeToGAEButton(){
//
////        myButton.setOnMouseClicked(event -> {
////            changeToGAE();
////            //FOR TESTING
////            //currentStage.setScene(levelConfigScene.getScene(root));
////        });
//        return createMenuButton("newgame.png", "newgame-hover.png", e -> changeToGAE());
//    }


    private void changeToGAE(){
        currentStage.setScene(gaeObject.createGAEScene(root));
    }

    private Label createMenuButton(String imagePath, String imagePathHover, Consumer consumer) {
        Label myButton = new Label();
        ImageView image = new ImageView(new Image(imagePath));
        ImageView imageHover = new ImageView(new Image(imagePathHover));
        image.setFitHeight(IMAGE_HEIGHT);
        image.setPreserveRatio(true);
        imageHover.setFitHeight(IMAGE_HEIGHT);
        imageHover.setPreserveRatio(true);
        myButton.setGraphic(image);
        myButton.setOnMouseEntered(e -> myButton.setGraphic(imageHover));
        myButton.setOnMouseExited(e -> myButton.setGraphic(image));
        myButton.setOnMouseClicked(consumer::accept);
        return myButton;
    }


    /**
     * This method is called within SubmitButton, and it changes the scene to that of the Game Player
     * @param createdXML
     * @throws GameEngineException
     */
    public void goToPlayer(Document createdXML) throws GameEngineException {
        new Player(currentStage, createdXML);
    }

    private void loadGameHandler() throws GameEngineException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(currentStage);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(selectedFile);
            goToPlayer(doc);
        } catch (ParserConfigurationException | SAXException | IOException e) {
//            throw new GameEngineException(e, "ConfigurationFailedXML");
            errorPane.errorMessage("UploadedWrongFileType");
            //TODO: dont hard code the error message -- also figure out how to connect error pane up to game engine exception messages

        }
    }

}
