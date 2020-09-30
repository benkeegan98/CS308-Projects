package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.FreezeSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.EffectBuilder;

public class FreezeSpriteEffect implements EffectStrategy {

    private EffectBuilder myOriginalBuilder;
    private int myDamageValue;
    private double myDuration;
    private boolean isFinished;

    public FreezeSpriteEffect(EffectBuilder builder) {
        myOriginalBuilder = builder;
        myDamageValue = builder.getDamage();
        myDuration = builder.getDuration();
        isFinished = false;
    }

    @Override
    public LevelAction getAction(int spriteId) throws GameEngineException {
        isFinished = true;
        return new FreezeSpriteAction(spriteId, myDamageValue, myDuration);
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
