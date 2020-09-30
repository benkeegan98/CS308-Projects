package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ObjectPreviewAndActive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameLevelComboBox extends ComboBox {
    private String current;
    private List<Integer> allLevels;
    private String currentLevel;
    private LevelConfigPane levelConfigPaneInstance;
    private List<ObjectPreviewAndActive> activeObjectObjects;
    private List<Map<String, Map<String, String>>> activeObjectsForLevel;
    private int selectedLevel;
    private int highestLevel;
    private int localHighest;

    /**
     * @author Marc Jabbour
     * This class creates the ComboBox that displays the created levels in the game and allows the user to return to old levels and re-edit them
     * @param levelConfigPaneInstanceParam
     */
    public GameLevelComboBox(LevelConfigPane levelConfigPaneInstanceParam){
        levelConfigPaneInstance = levelConfigPaneInstanceParam;
        allLevels = new ArrayList<>();
        selectedLevel = 1;
        highestLevel = 1;
        localHighest = highestLevel;
        this.setValue("1");
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ComboBox thisInstance = (ComboBox) event.getSource();
                String selectedLevel = thisInstance.getValue().toString();
                if(! (selectedLevel.equals(String.valueOf(highestLevel)))){
                    updateLevelConfigFields(selectedLevel);
                }

            }
        });
    }

    private void updateLevelConfigFields(String selectedLevelParam){
        activeObjectsForLevel = levelConfigPaneInstance.getActiveObjectsForLevel(Integer.parseInt(selectedLevelParam));
        activeObjectObjects = levelConfigPaneInstance.getActiveObjectObjects();
        levelConfigPaneInstance.saveInfoForLevel();
        if(! (activeObjectsForLevel == null)){

            for(Map map : activeObjectsForLevel){
                getAssociateObjectObject(map);
            }
        }
        selectedLevel = Integer.parseInt(selectedLevelParam);
        this.setValue(selectedLevel);


    }

    /**
     * @author Marc Jabbour
     * This method is invoked when a new level is created, and it adds each created level to the level-choosing ComboBox
     * @param previousLevel
     * @param currentLevelParam
     */
    public void addToComboBox(int previousLevel, int currentLevelParam){
        localHighest = highestLevel;
        highestLevel = currentLevelParam;
        selectedLevel = highestLevel;
        allLevels.add(previousLevel);
        this.setValue(String.valueOf(highestLevel));
        this.getItems().add(previousLevel);

    }

    private void getAssociateObjectObject(Map<String, Map<String, String>> activeObjectMapForAppropriateLevel){
        String[] allActiveObjectsInLevel = Arrays.copyOf(activeObjectMapForAppropriateLevel.keySet().toArray(), activeObjectMapForAppropriateLevel.keySet().toArray().length, String[].class);
        for(ObjectPreviewAndActive objectObject : activeObjectObjects){
            if(Arrays.asList(allActiveObjectsInLevel).contains(objectObject.getName())){
                objectObject.reactivate(activeObjectMapForAppropriateLevel, objectObject.getName());
            }
        }
    }

    public int getSelectedLevel(){
        return selectedLevel;
    }

}
