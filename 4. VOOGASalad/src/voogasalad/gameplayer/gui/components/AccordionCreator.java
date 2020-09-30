package voogasalad.gameplayer.gui.components;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.TilePane;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameplayer.gui.components.DisplayScreen;

import java.util.HashMap;
import java.util.List;

public class AccordionCreator extends Accordion {
    public static final int ITEM_HEIGHT = 75;
    public static final int ITEM_WIDTH = 75;
    public static final int NO_COLUMNS = 3;
    public static final int HORIZONTAL_SPACING = 10;
    public static final int VERTICAL_SPACING = 10;
    public static final String TOWER = "Towers";
    private static final String ENEMY = "Enemies";
    private TilePane hBoxTowers;
    private TilePane hBoxEnemies;
    private DisplayScreen displayScreen;


    public AccordionCreator() {
        createAccordion();
    }

    private void createAccordion() {
        hBoxTowers = new TilePane(HORIZONTAL_SPACING, VERTICAL_SPACING);
        hBoxEnemies = new TilePane(HORIZONTAL_SPACING, VERTICAL_SPACING);
        hBoxTowers.setPrefColumns(NO_COLUMNS);
        hBoxEnemies.setPrefColumns(NO_COLUMNS);
        TitledPane towerPane = new TitledPane(TOWER, hBoxTowers);
        TitledPane enemyPane = new TitledPane(ENEMY, hBoxEnemies);
        getPanes().add(towerPane);
        getPanes().add(enemyPane);
    }

    public void attachDisplayScreen(DisplayScreen ds){
        this.displayScreen = ds;
    }

    public void updateAvailableTowersAndEnemies(List<Sprite> towers, List<Sprite> enemies, HashMap<Integer, Integer> idMap){
        hBoxTowers.getChildren().clear();
        hBoxEnemies.getChildren().clear();
        int i = 0;
        for(Sprite tower: towers){
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(tower.getImagePath()), ITEM_HEIGHT, ITEM_WIDTH, true, false);
            ImageView imageView = new ImageView(image);
            hBoxTowers.getChildren().add(imageView);
            imageView.setFitHeight(ITEM_HEIGHT);
            imageView.setFitWidth(ITEM_WIDTH);
            int id = idMap.get(i);
            imageView.setOnDragDetected((EventHandler<javafx.event.Event>) event -> {
                displayScreen.setImageDraggedID(id);
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(image);
                db.setContent(content);
                event.consume();
            });
            i++;
        }

        for(Sprite enemy: enemies){
            ImageView image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(enemy.getImagePath())));
            hBoxEnemies.getChildren().add(image);
            image.setFitHeight(ITEM_HEIGHT);
            image.setFitWidth(ITEM_WIDTH);
        }
    }

}