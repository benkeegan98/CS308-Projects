package voogasalad.gameauthoringenvironment.gui.utilconfig;


import javafx.scene.control.Button;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;

public class SubmitButton extends Button {
    /**
     * This constructor is invoked through reflection, and is referenced as a Node associated with a "Submit" parameter the Parameter Creator's defining properties file
     */
    public SubmitButton(){}

    public SubmitButton(ParameterCreator parameterCreatorInstanceParam){
        super("CREATE");
        this.setOnMouseClicked(event -> {
            parameterCreatorInstanceParam.createSubmitButton();
            parameterCreatorInstanceParam.clearFields();
        });
    }

}
