package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PreviewImageButton extends Button {

    private ImageView imageView;
    private String imageString;
    private VBox vBox;
    private static FileChooserButton fileChooserButton;

    public PreviewImageButton(){
        super("Preview Image");
//        this.setOnMouseClicked(event ->{
//            imageString = fileChooserButton.getImageString();
//            //imageView = fileChooserButton.getImageView();
//            System.out.println("PreviewImageButton " + imageString);
//        });
    };

    public static FileChooserButton getFileChooserButton() {
        return fileChooserButton;
    }

    public PreviewImageButton(FileChooserButton fileChooserButtonParam) {
        fileChooserButton = fileChooserButtonParam;
    }

    public String getImageString(){
        return imageString;
    }

    /**
     *
     * @return
     */
    public String clearImageString() {
        return imageString = "";
    }

    public ImageView getImageView() {
        return imageView;
    }
}
