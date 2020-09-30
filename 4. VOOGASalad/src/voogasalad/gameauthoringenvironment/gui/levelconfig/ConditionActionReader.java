package voogasalad.gameauthoringenvironment.gui.levelconfig;

import javafx.scene.control.ComboBox;
import voogasalad.gameauthoringenvironment.gui.levelconfig.nodes.ConditionActionComboBox;

import java.util.Map;
import java.util.ResourceBundle;

public class ConditionActionReader {
    private static final String CONDITION_ACTION_OPTIONS = "resources.gae.conditionaction.ConditionAction";
    private ResourceBundle conditionAction;
    private ConditionActionComboBox conditionComboBox;
    private ConditionActionComboBox actionComboBox;

    /**
     * @author Marc Jabbour
     * This method adds a list of all available conditions and actions to their respective ComboBoxes, based on a properties file
     * @param allActiveObjectMap
     */
    public ConditionActionReader(Map<String, Map<String, String>> allActiveObjectMap){
        conditionComboBox = new ConditionActionComboBox(allActiveObjectMap);
        actionComboBox = new ConditionActionComboBox(allActiveObjectMap);

        conditionAction = ResourceBundle.getBundle(CONDITION_ACTION_OPTIONS);
        conditionAction.getKeys().asIterator().forEachRemaining(key -> {
            if (key.contains("Condition")) {
                conditionComboBox.getItems().add(conditionAction.getString(key));
            }
            else{
                actionComboBox.getItems().add(conditionAction.getString(key));
            }
        });
    }

    public ComboBox getConditions(){
        return conditionComboBox;
    }

    public ComboBox getActions(){
        return actionComboBox;
    }

}
