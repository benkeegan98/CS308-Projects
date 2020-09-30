package slogo.view;

import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.backend.Controller;

import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * This class sets up the frontend display of the entire project. The bottom, right, and turtle display panes are set up here.
 */
public class Visualization {

    private static final String RESOURCE_PACKAGE = Visualization.class.getPackageName() + ".styling.";
    private static final String STYLESHEET = "styles.css";
    private final String RIGHT_PANE_BUTTONS = "resources/rightPaneButtons";
    private static final int SCREEN_HEIGHT = 700;
    private static final int SCREEN_WIDTH = 900;
    public static final int COMMAND_BUTTON_SIZE = 25;
    static final int SCROLLER_HEIGHT = 450;
    static final int SCROLLER_WIDTH = 185;

    private HostServices host;
    private Pane lastPane;
    private TurtleTurnup turtleTurnup = new TurtleTurnup();
    private HistoryPane historyPane = new HistoryPane();
    private ErrorPane errorPane = new ErrorPane();
    private Controller control = new Controller(turtleTurnup, errorPane);
    private SettingsPane settingsPane = new SettingsPane(turtleTurnup, control);
    private VariablesPane variablesPane = new VariablesPane(control);
    private UserCommandsPane userCommandsPane = new UserCommandsPane(control);
    private InformationPane informationPane = new InformationPane(control);
    private ComboBox<String> rightPaneButtons;
    private ResourceBundle rightPaneButtonsBundle;

    public Visualization(HostServices myHost, Stage stage) {
        host = myHost;
        rightPaneButtonsBundle = ResourceBundle.getBundle(RIGHT_PANE_BUTTONS);
        stage.setTitle("SLogo");
        stage.setScene(displayScene());
        stage.setResizable(false);
        stage.show();
    }

    private VBox setRightPane() {
        VBox rightPane = new VBox();
        rightPane.setId("right-pane");

        rightPaneButtons = new ComboBox<>();
        rightPaneButtons.setId("right-pane-buttons");
        rightPaneButtonsBundle.getKeys().asIterator().forEachRemaining(key -> {
            rightPaneButtons.getItems().add(rightPaneButtonsBundle.getString(key));
        });
        rightPaneButtons.getSelectionModel().select("Settings");

        rightPaneButtons.setOnAction(event -> {
            String activePaneValue = rightPaneButtons.getValue();
            if(activePaneValue.equals("Settings")) {
                setActiveRightPane(rightPane, settingsPane);
            } else if (activePaneValue.equals("Help")) {
                rightPane.getChildren().remove(lastPane);
                host.showDocument("https://www2.cs.duke.edu/courses/compsci308/fall19/assign/03_parser/commands.php");
                host.showDocument("https://www2.cs.duke.edu/courses/compsci308/fall19/assign/03_parser/commands2_J2W.php");
            } else if (activePaneValue.equals("History")) {
                setActiveRightPane(rightPane, historyPane);
                historyPane.displayHistory();
            } else if (activePaneValue.equals("Variables")) {
                setActiveRightPane(rightPane, variablesPane);
                variablesPane.displayVariables();
            } else if (activePaneValue.equals("User Commands")) {
                setActiveRightPane(rightPane, userCommandsPane);
                userCommandsPane.displayUserCommands();
            } else if(activePaneValue.equals("New Window")) {
                new Visualization(host, new Stage());
            } else if(activePaneValue.equals("Information")){
                setActiveRightPane(rightPane, informationPane);
                informationPane.displayInfo();
            }
        });

        rightPane.getChildren().add(rightPaneButtons);
        setActiveRightPane(rightPane, settingsPane);

        return rightPane;
    }

    private void setActiveRightPane(Pane rightPane, Pane activePane) {
        rightPane.getChildren().remove(lastPane);
        activePane.setId("active-right-pane");
        rightPane.getChildren().add(activePane);
        lastPane = activePane;
    }


    private HBox setCommandPane() {
        HBox commandPane = new HBox();
        commandPane.setId("command-pane");

        TextArea commandBox = new TextArea();
        commandBox.setId("command-box");
        commandBox.setPromptText("Enter Command Here...");

        Pane commandButtons = setCommandButtons(commandBox);

        commandPane.getChildren().addAll(commandButtons, commandBox);
        return commandPane;
    }

    private Pane setCommandButtons(TextArea commandBox) {

        Pane commandButtons = new Pane();

        Button runButton = createRunButton(commandBox);
        createCommandButtons(commandButtons, "forward-button", "fd 20");
        createCommandButtons(commandButtons, "backward-button", "bk 20");
        createCommandButtons(commandButtons, "left-button", "lt 15");
        createCommandButtons(commandButtons, "right-button", "rt 15");

        commandButtons.getChildren().add(runButton);

        return commandButtons;
    }

    private void createCommandButtons(Pane commandButtons, String id, String action) {
        ImageView icon = createImageView(id + ".png", COMMAND_BUTTON_SIZE,COMMAND_BUTTON_SIZE);
        Button button = new Button("", icon);
        button.setId(id);
        button.setOnAction(e -> {
            control.parseAndPerform(action);
            informationPane.removeOldInfo();
            informationPane.displayInfo();
        });
        commandButtons.getChildren().add(button);
    }

    private Button createRunButton(TextArea commandBox) {
        ImageView runIconImg = createImageView("run-button.png", COMMAND_BUTTON_SIZE,COMMAND_BUTTON_SIZE);
        Button runButton = new Button("", runIconImg);
        runButton.setId("run-button");

        runButton.setOnAction(e -> {
            historyPane.addToHistory(commandBox.getText());
            control.parseAndPerform(commandBox.getText());
            if(rightPaneButtons.getValue() != null) {
                switch (rightPaneButtons.getValue()) {
                    case "History":
                        historyPane.displayHistory();
                        break;
                    case "Variables":
                        variablesPane.removeVariables();
                        variablesPane.displayVariables();
                        break;
                    case "User Commands":
                        userCommandsPane.displayUserCommands();
                        break;
                    case "Information":
                        informationPane.removeOldInfo();
                        informationPane.displayInfo();
                        break;
                }
            }
        });
        return runButton;
    }

    private Scene displayScene() {
        AnchorPane root = new AnchorPane();

        HBox commandPane = setCommandPane();
        VBox rightPane = setRightPane();

        root.getChildren().addAll(rightPane, commandPane, turtleTurnup);
        AnchorPane.setRightAnchor(rightPane,0.);
        AnchorPane.setBottomAnchor(commandPane, 0.);

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/" + RESOURCE_PACKAGE.replaceAll("\\.", "/") + STYLESHEET).toExternalForm());

        return scene;
    }

    public TurtleTurnup getTurtleTurnup(){
        return turtleTurnup;
    }

    private ImageView createImageView(String file, int height, int width) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream((file)));
        ImageView iv = new ImageView(image);
        iv.setFitHeight(height);
        iv.setFitWidth(width);

        return iv;
    }
}
