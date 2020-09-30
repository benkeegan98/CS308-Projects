package GameAuthoringEnvironment;

import javafx.scene.Scene;

import java.io.File;

public interface GameAuthoringEnvironmentInternalAPI {

    public File updateXML();

    public Scene makeScene();

    public void addTowerType();
    public void addEnemyType();
    public void addLevelInfo();

}
