package voogasalad.gameengine.executors.utils;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Originally used to verify and pass parameters to strategy constructors.
 */
public class Verifier {
    //TODO: I'm not completely satisfied with how we're doing verification right now; let's rework how this process works later.
    public static final String STRATEGY_PARAMETERS_IDENTIFIER_RESOURCE_PATH = "resources.engine.StrategyParameters";

    public static Object verifyAndGetStrategyParameter(Map<String, Object> parameterMap, String key) throws GameEngineException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(STRATEGY_PARAMETERS_IDENTIFIER_RESOURCE_PATH);
        String[] keyValuePair = resourceBundle.getString(key).split(",");
       return verifyAndGetHelper(parameterMap, keyValuePair);
    }

    public static Object verifyValidKey(Object object, Class<?> expectedType) throws GameEngineException {
        if (object != null && (object.getClass().equals(expectedType))) {
            return object;
        } else {
            for (Class<?> classInterface : object.getClass().getInterfaces()) {
                if(classInterface.equals(expectedType)) {
                    return object;
                }
            }
        }
        throw new GameEngineException("InvalidValueInStrategyInitialization");
    }

    private static Object verifyAndGetHelper(Map<String, Object> parameterMap, String[] keyValuePair) throws GameEngineException {
        try {
            return verifyValidKey(parameterMap.get(keyValuePair[0]), Class.forName(keyValuePair[1]));
        } catch (ClassNotFoundException | NullPointerException | GameEngineException e) {
            throw new GameEngineException("InvalidValueInStrategyInitialization");
        }
    }
}