package voogasalad.gameengine.executors.sprites.strategies.effect;

import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.action.level.TickHealthAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.EffectBuilder;

public class TickHealthEffect implements EffectStrategy {

    private EffectBuilder myOriginalBuilder;
    private int myDamageValue;
    private int myTickCount;
    private double myTickDelay;
    private boolean isFinished;

    public TickHealthEffect(EffectBuilder builder) {
        myOriginalBuilder = builder;
        myDamageValue = builder.getDamage();
        myTickCount = builder.getTickCount();
        myTickDelay = builder.getTickDelay();
        isFinished = false;
    }

    @Override
    public LevelAction getAction(int spriteId) throws GameEngineException {
        isFinished = true;
        return new TickHealthAction(spriteId, myDamageValue, myTickCount, myTickDelay);
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
