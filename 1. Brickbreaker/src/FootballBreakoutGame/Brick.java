package FootballBreakoutGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick extends ImageView {
    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 25;

    double brickLifeCount;

    public Brick(double xPos, double yPos, Image brickType, double lives) { // eventually pass brick lives in as an argument
        super(brickType);
        setFitHeight(BRICK_HEIGHT);
        setFitWidth(BRICK_WIDTH);
        relocate(xPos, yPos);
        brickLifeCount = lives;
    }
}
