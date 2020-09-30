package FootballBreakoutGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class Ball extends ImageView {
    public static final String FOOTBALL = "football.png";
    public static final double BALL_SIZE = 30;
    public static final Image FOOTBALL_IMAGE = new Image(Ball.class.getClassLoader().getResourceAsStream(FOOTBALL), BALL_SIZE, BALL_SIZE, false, false);

    double velX;
    double velY;

    public Ball() {
        // initialize instance variables
        super(FOOTBALL_IMAGE);
        setFitWidth(BALL_SIZE);
        setFitHeight(BALL_SIZE);
        setX(0);
        setY(0);
        velX = 0;
        velY = 0;
    }

    public void bounceOffPaddleAndEdges(ImageView paddle, double windowWidth) {
        if (GameMain.PADDLE_YPOS < getBoundsInParent().getMaxY() && getX() > paddle.getX() && getX() < paddle.getX() + GameMain.PADDLE_WIDTH) {
            velY = velY * -1;
            double ballCenter = getX() + BALL_SIZE / 2;
            if (ballCenter > paddle.getX() && ballCenter < paddle.getX() + GameMain.PADDLE_WIDTH / 3) {
                velX = -1 * ((Math.random() * 1.5) + 0.5);
            } else if (ballCenter > paddle.getBoundsInLocal().getMaxX() - GameMain.PADDLE_WIDTH / 3 && ballCenter < paddle.getBoundsInLocal().getMaxX()) {
                velX = ((Math.random() * 1.5) + 0.5);
            }
        }
        if (getX() < 0 || getBoundsInLocal().getMaxX() > windowWidth) velX *= -1;
        if (getY() < 0) velY *= -1;
    }

    public void updateBricks(Pane root, ArrayList<Brick> gameBricks) {
        for(Brick brick : gameBricks) {
            if (intersects(brick.getBoundsInParent())) {
                velY *= -1;
                brick.brickLifeCount--;
                if (brick.brickLifeCount == 0) {
                    gameBricks.remove(brick);
                    root.getChildren().remove(brick);
                    break;
                }
            }
        }
    }
}
