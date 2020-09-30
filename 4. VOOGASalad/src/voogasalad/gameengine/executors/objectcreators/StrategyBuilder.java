package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;

/**
 * Enforces the behavior of builder classes responsible for parsing parameters from the XML and builds new strategies
 * using those parameters.
 */
public interface StrategyBuilder {
    /**
     * Calls the constructor of the strategy it's meant to handle
     * @return a strategy of a specified type with the parameters previously parsed
     * @throws GameEngineException
     */
    Object build() throws GameEngineException;
}
