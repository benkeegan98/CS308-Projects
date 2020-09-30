package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import voogasalad.gameauthoringenvironment.gui.utilconfig.*;
import voogasalad.gameauthoringenvironment.gui.utilconfig.slider.*;

public class FieldTextReturnFactory {

    /**
     * @author Marc Jabbour
     * This Factory class is used to obtain data from different Node objects
     * @param node
     * @return
     */
    public String getAppropriateText(Object node){
        if(node instanceof javafx.scene.control.TextField){
            return ((TextField) node).getText();
        }
        if(node instanceof ComboBox){
            return (String) ((ComboBox) node).getValue();
        }
        if(node instanceof AttackRateSlider) {
            return Double.toString(((AttackRateSlider) node).getSliderValue());
        }
        if(node instanceof HealthCostSlider) {
            return Double.toString(((HealthCostSlider) node).getSliderValue());
        }
        if(node instanceof ImageSpecEnemySpeedSlider){
            return Double.toString(((ImageSpecEnemySpeedSlider) node).getSliderValue());
        }
        if(node instanceof ProjectileDistanceSlider) {
            return Double.toString(((ProjectileDistanceSlider) node).getSliderValue());
        }
        if(node instanceof ProjectileSpeedSlider) {
            return Double.toString(((ProjectileSpeedSlider) node).getSliderValue());
        }
        if(node instanceof RotationRangeSlider) {
            return Double.toString(((RotationRangeSlider) node).getSliderValue());
        }
        if(node instanceof FileChooserButton) {
            return ((FileChooserButton) node).getImageString();
        }
        if(node instanceof PreviewImageButton) {
            return ((PreviewImageButton) node).getImageString();
        }

        return "Field Type not recognized";
    }
}
