package slogo;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import slogo.view.Visualization;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
    /**
     * Start of the program.
     */

    @Override
    public void start(Stage stage) {
        HostServices myHost = getHostServices();
        new Visualization(myHost, stage);

    }

    public static void main (String[] args) {
        launch(args);
        //Controller controller = new Controller(new TurtleTurnup());
        //controller.parseAndPerform("repeat 10 [ SUM 50 50 SUM 50 50 ] fd 10 fd 10");
    }
}

