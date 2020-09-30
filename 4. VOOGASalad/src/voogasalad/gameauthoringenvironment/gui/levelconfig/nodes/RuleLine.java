package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import voogasalad.gameauthoringenvironment.gui.levelconfig.ConditionActionReader;

import java.util.Map;

public class RuleLine extends HBox {
    ConditionActionReader conditionActionComboBox;

    /**
     * @author Marc Jabbour
     * This constructor creates an HBox representing all the Rules and Conditions of the game
     * It takes in a map of active objects so that if a condition need specify game-objects, it can only select from active ones
     * @param allActiveObjectMapParam
     */
    public RuleLine(Map<String, Map<String, String>> allActiveObjectMapParam){
        super(10);
        conditionActionComboBox = new ConditionActionReader(allActiveObjectMapParam);
        this.getChildren().addAll(new Label("Condition: "), conditionActionComboBox.getConditions(), new Label("  --->   Action: "), conditionActionComboBox.getActions());
    }

}
