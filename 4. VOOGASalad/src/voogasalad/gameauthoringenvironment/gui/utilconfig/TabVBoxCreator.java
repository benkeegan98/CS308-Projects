package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * purpose (why would anyone use it)
 * assumptions (what situations or values might cause it to fail)
 * dependencies (what other classes or packages it depends on)
 * an example of how to use it
 * any other details users should know
 * This class is used for creating a VBox in the Tower, Enemy, and Level tabs.
 * It assumes that someone using this class would want a title and padding added to the VBox.
 * It is not dependent on other classes or methods, except for where it is instantiate.
 * Example:
 *          TabVBoxCreator vbox = new TabVBoxCreator("Example VBox", 250, 10, 10, 10, 10);
 *
 * @author Amber Johnson
 */
public class TabVBoxCreator extends VBox{

    public TabVBoxCreator(String vBoxName, double width, double top, double right, double bottom, double left) {
        Label header = new Label(vBoxName);
        header.setFont(Font.font(14));
        this.getChildren().addAll(header);
        this.setPrefWidth(width);
        this.setPadding(new Insets(top, right, bottom, left));
    }

}
