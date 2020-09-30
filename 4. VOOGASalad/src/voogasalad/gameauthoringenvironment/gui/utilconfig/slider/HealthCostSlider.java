package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;


/**
 * This class is used to create a slider that sets the health or cost in the GAE GUI.
 * This class does not make any assumptions and it will not cause any errors because
 * if no selection is made, it sets the default to 25
 * Example:
 *          HealthCostSlider slider = new HealthCostSlider();
 *          VBox vbox = new VBox(slider);
 *
 * @author Amber Johnson
 */
public class HealthCostSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public HealthCostSlider() {
        this.setMin(0);
        this.setMax(100);
        this.setValue(25);
        this.setMajorTickUnit(25);
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
