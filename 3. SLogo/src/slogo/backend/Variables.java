package slogo.backend;

import java.util.HashMap;
import java.util.Map;

public class Variables {

    private Map<String, Double> variableMap;

    public Variables() {
        variableMap = new HashMap<>();
    }

    public Double getVariableValue(String variableName) {
        if (variableMap.containsKey(variableName)) {
            return variableMap.get(variableName);
        }
        //TODO: Throw error to frontend here; variable not properly created
        return Double.valueOf(0);
    }

    public boolean variableExists(String variableName) {
        return variableMap.containsKey(variableName);
    }

    public void addVariable(String name, Double value) {
        variableMap.put(name, value);
    }

    public Map<String, Double> getVariables() {
        return variableMap;
    }
}
