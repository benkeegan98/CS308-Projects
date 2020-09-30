package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;


/**
 * This class is used to create a slider that sets the range of rotation in the GAE GUI.
 * This class does not make any assumptions and it will not cause any errors because
 * if no selection is made, it sets the default to 180 degrees.
 * Example:
 *          RotationRangeSlider slider = new RotationRangeSlider();
 *          VBox vbox = new Vbox(slider);
 * @author Amber Johnson
 */
public class RotationRangeSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public RotationRangeSlider() {
        this.setMin(0);
        this.setMax(720);
        this.setValue(180);
        this.setMajorTickUnit(180);
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
