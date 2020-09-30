package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import voogasalad.gameauthoringenvironment.gui.utilconfig.*;
import voogasalad.gameauthoringenvironment.gui.utilconfig.slider.*;

public class ClearFieldsFactory {

    public void clearField(Object node){
        if(node instanceof javafx.scene.control.TextField){
            ((TextField) node).clear();
        }
        if(node instanceof ComboBox){
            ((ComboBox) node).getItems().clear();
        }
        if(node instanceof AttackRateSlider) {
            ((Slider) node).setValue(0.25);
        }
        if(node instanceof HealthCostSlider) {
            ((Slider) node).setValue(25);
        }
        if(node instanceof ImageSpecEnemySpeedSlider) {
            ((Slider) node).setValue(70);
        }
        if(node instanceof ProjectileDistanceSlider) {
            ((Slider) node).setValue(0.25);
        }
        if(node instanceof ProjectileSpeedSlider) {
            ((Slider) node).setValue(0.67);
        }
        if(node instanceof RotationRangeSlider) {
            ((Slider) node).setValue(180);
        }
        if(node instanceof RotationSpeedSlider) {
            ((Slider) node).setValue(90);
        }
        if(node instanceof FileChooserButton) {
            ((FileChooserButton) node).clearImageString();
        }
        if(node instanceof PreviewImageButton) {
            ((PreviewImageButton) node).clearImageString();
        }
    }
}
