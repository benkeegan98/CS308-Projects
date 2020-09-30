package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;


/**
 * This class is used to create a slider that sets the speed of a projectile in the GAE GUI.
 * This class does not make any assumptions and it will not cause any errors because
 * if no selection is made, it sets the default to 0.25
 * Example:
 *          ProjectileSpeedSlider slider = new ProjectileSpeedSlider();
 *          VBox vbox = new VBox(slider);
 *
 * @author Amber Johnson
 */
public class ProjectileSpeedSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public ProjectileSpeedSlider() {
        this.setMin(0);
        this.setMax(3);
        this.setValue(0.67);
        this.setMajorTickUnit(0.67);
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
