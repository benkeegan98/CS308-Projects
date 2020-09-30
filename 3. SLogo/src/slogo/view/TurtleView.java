package slogo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is the frontend display of the turtle.
 */
public class TurtleView extends ImageView {

    private static final String INITIAL_TURTLE_IMAGE = "turtle_slogo.png";

    public TurtleView() {
        changeTurtleImage(INITIAL_TURTLE_IMAGE);
    }

    void changeTurtleImage(String name){
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(name));
        setImage(image);
    }

    void moveTurtle(double x, double y, boolean pen, double direction){
        setX(x + TurtleTurnup.CENTER_X);
        setY(TurtleTurnup.CENTER_Y - y);
        setRotate(direction);
    }
}
