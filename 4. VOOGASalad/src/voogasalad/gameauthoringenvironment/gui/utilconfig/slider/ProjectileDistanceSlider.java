package voogasalad.gameauthoringenvironment.gui.utilconfig.slider;

import javafx.scene.control.Slider;


/**
 * This class is used to create a slider that sets the projectile distance in the GAE GUI.
 * This class does not make any assumptions and it will not cause any errors because
 * if no selection is made, it sets the default to 70
 * Example:
 *          ProjectileDistanceSlider slider = new AttackRateSlider();
 *          VBox vbox = new VBox(slider);
 *
 * @author Amber Johnson
 */
public class ProjectileDistanceSlider extends Slider implements SliderConfiguration {

    private double mySliderValue;

    public ProjectileDistanceSlider() {
        this.setMin(20);
        this.setMax(300);
        this.setValue(70);
        this.setMajorTickUnit(70);
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
    };
}
