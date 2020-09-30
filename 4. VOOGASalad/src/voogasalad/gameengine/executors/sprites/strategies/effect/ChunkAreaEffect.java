package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.ChunkAreaAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.EffectBuilder;

public class ChunkAreaEffect implements EffectStrategy {

    private EffectBuilder myOriginalBuilder;
    private int myDamageValue;
    private double myRadius;
    private boolean isFinished;

    public ChunkAreaEffect(EffectBuilder builder) {
        myOriginalBuilder = builder;
        myDamageValue = builder.getDamage();
        myRadius = builder.getRadius();
        isFinished = false;
    }

    @Override
    public LevelAction getAction(int spriteId) throws GameEngineException {
        isFinished = true;
        return new ChunkAreaAction(spriteId, myDamageValue, myRadius);
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
