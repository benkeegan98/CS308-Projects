package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;


/**
 * This class is used to create a slider that sets the attack rate in the GAE GUI.
 * This class does not make any assumptions and it will not cause any errors because
 * if no selection is made, it sets the default to 0.25
 * Example:
 *          AttackRateSlider slider = new AttackRateSlider();
 *          VBox vbox = new VBox(slider);
 *
 * @author Amber Johnson
 */
public class AttackRateSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public AttackRateSlider() {
        this.setMin(0.00);
        this.setMax(1);
        this.setValue(0.25);
        this.setMajorTickUnit(0.25);
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

