package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.ChunkHealthAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.EffectBuilder;

public class ChunkHealthEffect implements EffectStrategy {
    private EffectBuilder myOriginalBuilder;
    private int damageValue;
    private boolean isFinished;

    public ChunkHealthEffect(EffectBuilder builder) {
        damageValue = builder.getDamage();
        myOriginalBuilder = builder;
        isFinished = false;
    }

    @Override
    public LevelAction getAction(int spriteId) throws GameEngineException {
        isFinished = true;
        return new ChunkHealthAction(spriteId, damageValue);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public EffectStrategy makeClone() throws GameEngineException {
        return myOriginalBuilder.build();
    }
}
