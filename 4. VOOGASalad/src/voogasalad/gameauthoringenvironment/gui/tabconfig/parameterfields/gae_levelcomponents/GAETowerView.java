package gae_gui.gae_levelcomponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class GAETowerView {

    private static final int window_WIDTH = 700;
    private static final int window_HEIGHT = 300;
    private GridPane root;
    private ResourceBundle myHelpContents;
    private Scene towerEditScene;
    private TextFlow myTextFlow;
    private Stage towerPreferencePage;
    private ResourceBundle towerAtrributesLabel;
    private ResourceBundle towerTypeLabel;
    private ResourceBundle towerLevelLabel;
    private String towerResourcesPath = "gae_gui.gaeresource.";
    private TowerAttributes towerAttributes;
    private int rowIndex;


   public GAETowerView(){
       towerAttributes = new TowerAttributes();
       towerAtrributesLabel = ResourceBundle.getBundle(towerResourcesPath+"towerAttributes");
       towerLevelLabel = ResourceBundle.getBundle(towerResourcesPath + "towerLevels");

       towerPreferencePage = new Stage();
       root = new GridPane();
       addLabel();
       addInputField();
       addTowerTypeDropdown();

       towerEditScene = new Scene(root, window_WIDTH, window_HEIGHT);
       towerPreferencePage.setScene(towerEditScene);
       towerPreferencePage.show();

   }

   private void addInputField(){
       int rowIndex = 4;
       int columnIndex = 2;

       for (String levelKey : towerLevelLabel.keySet()){

           Label newLabel = new Label(towerLevelLabel.getString(levelKey));
           root.add(newLabel, columnIndex,rowIndex);
           int innerRowIndex = rowIndex+1;
           for (String key : towerAtrributesLabel.keySet()) {
               TextField newTextField = createInputField(towerLevelLabel.getString(levelKey)+ " " + towerAtrributesLabel.getString(key));
               root.add(newTextField,columnIndex,innerRowIndex);
               innerRowIndex++;
           }
           columnIndex++;
           //rowIndex++;

       }


   }

   private TextField createInputField(String fieldName){
       TextField newInputField = new TextField();
       newInputField.setId(fieldName);
       //newInputField.setOnAction(e -> );
       return newInputField;

   }

   private void addLabel(){
       Label title = new Label("New Tower Attributes");
       title.setLayoutX(400);
       title.setLayoutY(50);
       Label towerTypeLabel = new Label("Turret Type");

       root.add(title,3,1);
       root.add(towerTypeLabel,1,2);
        rowIndex=5;
       for (String key : towerAtrributesLabel.keySet()) {
           int columnIdx = 1;
           String labelName = towerAtrributesLabel.getString(key);
           Label newLabel = new Label(labelName);
           root.add(newLabel,columnIdx,rowIndex);
           rowIndex++;
       }

   }

   private void addTowerTypeDropdown() {
       ComboBox<String> combo = new ComboBox<>();
       //ChoiceBox<String> combo = new ChoiceBox<>();
       combo.setId("Tower Type");
       towerTypeLabel = ResourceBundle.getBundle(towerResourcesPath+"towerTypes");
       ObservableList<String> choices = FXCollections.observableArrayList();
       for (String key : towerTypeLabel.keySet()){
           choices.add(towerTypeLabel.getString(key));
       }
       combo.setItems(choices);
       root.add(combo,2,2);
       //combo.valueProperty().addListener((o, old, neww) -> updateLabel(label, neww));
   }


   /*
    private HBox createTopHBox() {
        HBox top = new HBox();
        top.setSpacing(20);
        Enumeration<String> dropDownLabels = dropDownResources.getKeys();

        while(dropDownLabels.hasMoreElements()) {
            LabeledComboBox toAdd = new LabeledComboBox(dropDownLabels.nextElement(), actionController, dropDownResources, dropDownActionResources);
            dropDowns.put(toAdd.getLabel(), toAdd);
            top.getChildren().add(toAdd);
        }

        top.setLayoutY(50);
        return top;
    }
*/
    public GridPane getGridPane() {
        return root;
    }
}
