package gae_gui.gae_levelcomponents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class GAE_LevelViewCheckbox extends CheckBox {
    List<Pair<Double,Double>> pathSegmentPoints;
    String label;
    public GAE_LevelViewCheckbox(){
        super();
        pathSegmentPoints = new ArrayList<>();
        initializeAction();


    }
    private void initializeAction() {
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateAction();
            }
        });
    }

    private void updateAction(){
        if (this.isIndeterminate() == false) {
            if (this.isSelected() == true) {
                System.out.println("checkbox state" + this.isIndeterminate() + " " + this.isSelected());
            }
        };
    }

    public GAE_LevelViewCheckbox(String name){
        super(name);
        label = name;
        pathSegmentPoints = new ArrayList<>();

    }


}
