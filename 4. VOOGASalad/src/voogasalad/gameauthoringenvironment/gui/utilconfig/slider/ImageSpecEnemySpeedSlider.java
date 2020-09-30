package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;


/**
 * This class is used to create a slider that sets the height, width, and speed of an enemy in the GAE GUI.
 * This class does not make any assumptions and it will not cause any errors because
 * if no selection is made, it sets the default to 130
 * Example:
 *          ImageSpecEnemySpeedSlider slider = new ImageSpecEnemySpeedSlider();
 *          VBox vbox = new VBox(slider);
 *
 * @author Amber Johnson
 */
public class ImageSpecEnemySpeedSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public ImageSpecEnemySpeedSlider() {
        this.setMin(30);
        this.setMax(430);
        this.setValue(130);
        this.setMajorTickUnit(100);
        this.setMinorTickCount(3);
        this.setShowTickLabels(true);
        this.setShowTickMarks(true);
        this.getSliderValue();
        mySliderValue = this.getValue();
    }

    /**
     * a public method to get the value shown of the slider
     * @return a double that represents the current slider value
     */
    @Override
    public double getSliderValue() {
        return mySliderValue;
    }

}
