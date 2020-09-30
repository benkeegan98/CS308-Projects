package slogo.backend;

import java.util.Map;

public class UserDefinedCommandsAndVars {
    private Map<String, UserCommand> commandMap;
    private Map<String, Double> variableMap;

    public UserDefinedCommandsAndVars(Map<String, UserCommand> userCommandMap, Map<String, Double> variableMap) {
        commandMap = userCommandMap;
        this.variableMap = variableMap;
    }

    public Map<String, Double> getVariableMap() {
        return variableMap;
    }

    public Map<String, UserCommand> getCommandMap() {
        return commandMap;
    }
}
