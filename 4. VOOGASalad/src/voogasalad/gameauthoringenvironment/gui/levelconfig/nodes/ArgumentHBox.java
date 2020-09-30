package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;

public class ArgumentHBox extends HBox {
    private static final String CONDITION_ARGUMENT_TYPES = "resources.gae.conditionaction.ConditionArgumentTypes";
    private ResourceBundle conditionArgumentTypes;
    private String condition;
    private boolean isPopulated;

    public ArgumentHBox(String argument, Map<String, Map<String, String>> allActiveObjectMap, String conditionParam) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        condition = conditionParam;
        conditionArgumentTypes = ResourceBundle.getBundle(CONDITION_ARGUMENT_TYPES);
        if(appropriateNode(allActiveObjectMap) != null) {
            this.getChildren().add(new Label(argument + " -----> "));
            this.getChildren().add(appropriateNode(allActiveObjectMap));
            isPopulated = true;
        } else {
            isPopulated = false;
        }
    }


    /**
     * @author Marc Jabbour
     * This method returns a Node object based on what was specified as its associated Node object in a properties file
     * @param allActiveObjectMap
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public Node appropriateNode(Map<String, Map<String, String>> allActiveObjectMap) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ActiveObjectComboBox activeObjectComboBox = new ActiveObjectComboBox(allActiveObjectMap);
        if (!conditionArgumentTypes.getString(condition).equals("")) {
            Class cls = Class.forName(conditionArgumentTypes.getString(condition));
            Node myField = (Node) cls.getConstructor().newInstance();
            return myField;
        }
        return null;
    }

    public boolean getIsPopulated() {
        return isPopulated;
    }
}
