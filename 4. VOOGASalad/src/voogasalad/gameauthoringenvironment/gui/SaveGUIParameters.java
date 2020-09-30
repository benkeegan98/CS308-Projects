package voogasalad.gameauthoringenvironment.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * This class is instantiated in order to map user input to the input field type.
 * It is used for reviewing user input after a Tower, Enemy, or Obstacle is created.
 * This class implements user input error checking and depends on the UserInputException class.
 * Example:
 *          SaveGUIParameters myParams = new SaveGUIParameters(labelList, valueList);
 *          Map<String, String} myMap = myParams.getMap();
 *          myMap.checkInput();
 *
 * @author Amber Johnson
 */
public class SaveGUIParameters {

    private Map<String, String> vBoxMap;

    public SaveGUIParameters(List labels, List values) {
        vBoxMap = new HashMap<>();
        this.vBoxMap = makeMap(labels, values);
    }


    /**
     * a public method to get the map created by the private makeMap(listParam, listParam) method
     * @return
     */
    public Map<String, String> getMap(){
        return vBoxMap;
    }


    //a helper method to make a map of input fields and values from two lists
    private Map makeMap(List labels, List values) {

        Iterator iterLabels = labels.iterator();
        Iterator iterValues = values.iterator();
        while (iterLabels.hasNext() && iterValues.hasNext()) {
            vBoxMap.put((String) iterLabels.next(), (String) iterValues.next());
        }

        for (Map.Entry s : vBoxMap.entrySet()) {
            System.out.println(s);
        }

        return vBoxMap;
    }



}
