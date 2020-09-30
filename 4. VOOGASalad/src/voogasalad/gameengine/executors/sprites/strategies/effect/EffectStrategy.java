package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;

public interface EffectStrategy {
    LevelAction getAction(int spriteId) throws GameEngineException;
    boolean isFinished();
    EffectStrategy makeClone() throws GameEngineException;
}
