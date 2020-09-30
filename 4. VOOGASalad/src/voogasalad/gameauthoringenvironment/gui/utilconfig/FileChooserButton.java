package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class FileChooserButton extends Button {

    private static Stage newStage = new Stage();
    private String imageString;


    /**
     * @author Marc Jabbour
     * This class represents a button, which on click will allow the user to select a file
     */
    public FileChooserButton() {
        super("Select Image");
        createButton();
        this.getImageString();
    }

    /**
     * This method returns the selected-Image string
     */
    public String getImageString() {
        return imageString;
    }

    /**
     * This method clears a selected-Image string
     * @return
     */
    public String clearImageString() {
        return imageString = "";
    }

    //TODO: add exception
    private void createButton() {
        final FileChooser imageChooser = new FileChooser();
        this.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(newStage);
            imageString = file.toURI().toString();
            System.out.println("FileChooserButton " + imageString);
        });
    }
}

