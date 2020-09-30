package voogasalad.gameplayer.gui.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameplayer.gui.PlayerVisualization;
import voogasalad.gameplayer.Player;

import java.util.ResourceBundle;

public class SelectedTowerPane extends VBox {

    private static final String RESOURCE_PATH = "resources.player.PlayerViewOptions";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private static final int ICON_SIZE = Integer.parseInt(resourceBundle.getString("DeleteTowerIconSize"));

    private Player player;
    private PlayerVisualization playerVisualization;
    private ActionsProcessor actionsProcessor;

    public SelectedTowerPane(ActionsProcessor actionsProcessor, Player player, PlayerVisualization playerVisualization) {
        this.actionsProcessor = actionsProcessor;
        this.player = player;
        this.playerVisualization = playerVisualization;
        this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     *
     * @param sprite Sprite object to be
     * @param x
     * @param y
     */
    public void removeTower(Sprite sprite, int x, int y) {
        HBox removeTowerBox = new HBox();
        ImageView towerImage = new ImageView(new Image(sprite.getImagePath()));
        towerImage.setFitWidth(ICON_SIZE);
        towerImage.setFitHeight(ICON_SIZE);
        Label removeTowerButton = new Label("Remove Tower");
        removeTowerButton.setOnMouseClicked(e -> {
            actionsProcessor.processSellTowerAction(x,y);
            this.getChildren().remove(removeTowerBox);
            player.executeEngineWithZeroElapsedTime();
        });
        removeTowerButton.setOnMouseEntered(e -> removeTowerButton.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY))));
        removeTowerButton.setOnMouseExited(e -> removeTowerButton.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY, Insets.EMPTY))));
        removeTowerBox.getChildren().addAll(towerImage, removeTowerButton);
        this.getChildren().add(removeTowerBox);
    }

}
