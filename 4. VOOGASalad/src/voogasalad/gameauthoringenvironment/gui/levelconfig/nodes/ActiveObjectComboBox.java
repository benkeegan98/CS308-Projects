package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.ComboBox;

import java.util.Map;

public class ActiveObjectComboBox extends ComboBox {

    private static Map<String, Map<String, String>> allActiveObjectMap;

    /**
     * @author Marc Jabbour
     * This method is called using reflection and adds all objects made active by the user to a ComboBox
     */
    public ActiveObjectComboBox(){
        for(String type : allActiveObjectMap.keySet()){
            for(String name : allActiveObjectMap.get(type).keySet()){
                this.getItems().add(name);
            }
        }
    }

    /**
     * @author Marc Jabbour
     * This method sets the map of all active objects
     * @param allActiveObjectMapParam
     */
    public ActiveObjectComboBox(Map<String, Map<String, String>> allActiveObjectMapParam){
        allActiveObjectMap = allActiveObjectMapParam;
    }

}
