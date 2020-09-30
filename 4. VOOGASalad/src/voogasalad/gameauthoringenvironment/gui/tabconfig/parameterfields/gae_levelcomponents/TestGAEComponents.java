package gae_gui.gae_levelcomponents;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestGAEComponents extends Application {


    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //GAETowerView newTowerPage = new GAETowerView();
        GAE_LevelConfigPage newLevelConfig = new GAE_LevelConfigPage();
    }
}
