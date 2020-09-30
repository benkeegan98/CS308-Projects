package Main.Components.Screens;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Samantha Whitt
 * This class serves as a helpful static one to standardize fonts/text
 * Example: creating a new header
 */
public class TextFont {
    /**
     * sets text to be large
     * @param headerText
     */
    public static void setHeaderText(Text headerText) {
        headerText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
    }

    /**
     * sets text at specific location and centers text accordingly
     * @param text
     * @param textName
     * @param x_location
     * @param y_location
     */
    public static void setSceneText(Text text, String textName, int x_location, int y_location) {
        text.setText(textName);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(x_location);
        text.setY(y_location);
    }

    /**
     * sets text to be normal size, centered, and wrap around if the button is too short for the text
     * @param button
     * @param buttonText
     */
    public static void setButtonText(Button button, String buttonText) {
        button.wrapTextProperty().setValue(true);
        button.setText(buttonText);
        button.setTextAlignment(TextAlignment.CENTER);
    }
}
