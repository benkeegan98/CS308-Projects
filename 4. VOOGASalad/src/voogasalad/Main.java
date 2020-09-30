package voogasalad;

import voogasalad.gameauthoringenvironment.bus.Bus;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import voogasalad.gameengine.executors.exceptions.GameEngineException;


public class Main extends Application {

    public static final String TITLE = "Game Authoring Environment";
    public static final String XML = "src/resources/player/MockData.xml";
    private static Bus myBus;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(setBusScene(primaryStage, root));
        primaryStage.show();
        //Player player = new Player(primaryStage, );
    }

    private Scene setBusScene(Stage currentStage, BorderPane root) {
        myBus = new Bus(currentStage, root, 500, 500);
        return myBus.getBusScene();
    }
}


