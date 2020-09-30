package FootballBreakoutGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameMain extends Application {
    public static final String BACKGROUND = "soccer-field.jpg";
    public static final String TROPHY = "ucl-trophy.png";
    public static final int PADDLE_SPEED = 60;
    public static final double GAME_WIDTH = 600;
    public static final double GAME_HEIGHT = 780;
    public static final int PADDLE_YPOS = 720;
    public static final int PADDLE_XPOS = 250;
    public static final double PADDLE_WIDTH = 90;
    public static final double PADDLE_HEIGHT = 15;
    public static final int BALL_STARTX = 280;
    public static final int BALL_STARTY = 680;
    private static final String PADDLE_IMAGE = "lfc-paddle.png";

    private Stage window;
    private ImageView paddle;
    private int levelCount, lifeCount;
    private Ball matchBall;
    private Scene splashScreen;


    @Override
    public void start (Stage stage) {
        window = stage;
        splashScreen = gameSetup();
        window.setScene(splashScreen);
        window.setTitle("Football Champions Breakout");
        window.setResizable(false);
        window.show();
    }

    private Scene gameSetup() {
        Pane root = new Pane();

        setFieldBackground(root);

        Text title = new Text("Football Champions Breakout");
        title.setFont(Font.font("SansSerif", FontWeight.BOLD, 34));
        title.setFill(Color.WHITE);
        title.relocate((GAME_WIDTH / 2 - title.getBoundsInLocal().getWidth() / 2), 100);

        Image trophyImage = new Image(this.getClass().getClassLoader().getResourceAsStream(TROPHY));
        ImageView trophy = new ImageView(trophyImage);
        trophy.setX(GAME_WIDTH / 2 - trophy.getBoundsInLocal().getWidth() / 2);
        trophy.setY(150);

        Text instructions = new Text("Instructions: \n " +
                " - Press Space Bar to Fire Ball from Starting Position \n" +
                " - Press Arrow Keys to Move Paddle \n" +
                " - If the ball falls past your paddle, you lose one life \n" +
                " - You have 5 lives \n" +
                " - Goalkeeper brick has 3 lives, Defence has 2, rest of team has 1 \n" +
                " - Each level, the opponent lives increment by 1 \n" +
                " - Beat the 5 teams to become Champions of Europe");
        instructions.setFont(Font.font("SansSerif", FontWeight.MEDIUM, 16));
        instructions.setFill(Color.WHITE);
        instructions.relocate(100, 460);

        Button playGame = new Button("Play");
        Scene game = level();
        playGame.setOnAction(e -> window.setScene(game));
        playGame.relocate(280, 680);

        root.getChildren().addAll(title, trophy, instructions, playGame);

        return new Scene(root, GAME_WIDTH, GAME_HEIGHT);
    }

    private Scene level() {
        Pane root = new Pane();
        setFieldBackground(root);
        lifeCount = 5;

        paddle = initializePaddle(root);
        initializeBall(root);

        ArrayList<Brick> activeBricks = new ArrayList<>();
        levelCount = 1;
        populateWithBricks(root, activeBricks, levelCount);

        Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
        setKeyFunctions(scene);

        startGameLoop(root, paddle, activeBricks);

        return scene;

    }

    private Scene gameOver() {
        Pane root = new Pane();
        setFieldBackground(root);

        ImageView gameOverScreen = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("game-over-screen.png")));
        gameOverScreen.setFitHeight(200);
        gameOverScreen.setFitWidth(500);
        gameOverScreen.relocate(50, 150);
        root.getChildren().add(gameOverScreen);

        Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) window.setScene(splashScreen);
        });
        return scene;
    }

    private Scene youWin() {
        Pane root = new Pane();
        setFieldBackground(root);

        ImageView youWinScreen = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("you-win.jpg")));
        youWinScreen.setFitHeight(270);
        youWinScreen.setFitWidth(480);
        youWinScreen.relocate(30, 100);

        Text youWinMessage = new Text("YOU WIN! \n" +
                "Press Space to Continue");
        youWinMessage.setFill(Color.WHITE);
        youWinMessage.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
        youWinMessage.relocate(50, 450);

        root.getChildren().addAll(youWinScreen, youWinMessage);

        Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) window.setScene(splashScreen);
        });

        return scene;
    }

    public ImageView initializePaddle(Pane root) {
        Image paddleImage = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        ImageView paddle = new ImageView(paddleImage);
        paddle.setFitHeight(PADDLE_HEIGHT);
        paddle.setFitWidth(PADDLE_WIDTH);
        paddle.setX(PADDLE_XPOS);
        paddle.setY(PADDLE_YPOS);
        root.getChildren().add(paddle);
        return paddle;
    }

    private void initializeBall(Pane root) {
        matchBall = new Ball();
        matchBall.setX(BALL_STARTX);
        matchBall.setY(BALL_STARTY);
        root.getChildren().add(matchBall);
    }

    public void setFieldBackground(Pane root) {
        Image footballField = new Image(this.getClass().getClassLoader().getResourceAsStream(BACKGROUND));
        ImageView myBackground = new ImageView(footballField);
        myBackground.setFitHeight(GAME_HEIGHT);
        myBackground.setFitWidth(GAME_WIDTH);
        root.getChildren().add(myBackground);
    }

    public void populateWithBricks(Pane root, ArrayList<Brick> brickList, int level) {
        String brickView = "";
        switch(level) {
            case 1:
                brickView = "bvb-brick.png";
                break;
            case 2:
                brickView = "psg-brick.png";
                break;
            case 3:
                brickView = "juve-brick.png";
                break;
            case 4:
                brickView = "barca-brick";
                break;
            case 5:
                brickView = "mancity-brick.png";
                break;
            case 6:
                window.setScene(youWin());
                break;
        }

        Image brickType = new Image(this.getClass().getClassLoader().getResourceAsStream(brickView));

        for(int row = 0; row < 4; row++) {
            for(int pos = 0; pos < 7; pos += 2) {
                if (row == 0 && pos == 0) brickList.add(new Brick(260, 50, brickType, levelCount + 2));
                if (row == 1) brickList.add(new Brick(pos * 80 + 20, 100, brickType, levelCount + 1));
                if (pos != 6 && (row == 2 || row == 3)) brickList.add (new Brick(pos*80 + 100, row*100, brickType, levelCount));
            }
        }

        for(Brick brick : brickList) root.getChildren().add(brick);
    }

    public void startGameLoop(Pane root, ImageView gamePaddle, ArrayList<Brick> gameBricks) {
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10),
                e -> {
                    matchBall.setX(matchBall.getX() + matchBall.velX);
                    matchBall.setY(matchBall.getY() + matchBall.velY);
                    matchBall.bounceOffPaddleAndEdges(gamePaddle, GAME_WIDTH);
                    matchBall.updateBricks(root, gameBricks);

                    if (gameBricks.isEmpty()) {
                        root.getChildren().remove(matchBall);
                        levelCount++;
                        populateWithBricks(root, gameBricks, levelCount);
                        initializeBall(root);
                    }

                    if(matchBall.getY() > GAME_HEIGHT) {
                        lifeCount--;
                        matchBall.setX(BALL_STARTX);
                        matchBall.setY(BALL_STARTY);
                        matchBall.velX = 0;
                        matchBall.velY = 0;
                        if (lifeCount == 0) {
                            window.setScene(gameOver());
                        }
                    }
                }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    public void setKeyFunctions(Scene scene) {
        scene.setOnKeyPressed(e ->
        {
            switch (e.getCode()) {
                case RIGHT:
                    paddle.setX(paddle.getX() + PADDLE_SPEED);
                    break;
                case LEFT:
                    paddle.setX(paddle.getX() - PADDLE_SPEED);
                    break;
                case SPACE:
                    if(matchBall.getX() == BALL_STARTX && matchBall.getY() == BALL_STARTY) {
                        matchBall.velX = Math.random() < 0.5 ? Math.random() * 3 : Math.random() * -3;
                        matchBall.velY = -5;
                    }
                default:
                    break;
            }
        });
    }

    public static void main(String[] args) { launch(args); }

}



