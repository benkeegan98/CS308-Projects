package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.effect.EffectStrategy;

public class EffectBuilder implements StrategyBuilder {
    private static final String CLASS_PATH = "voogasalad.gameengine.executors.sprites.strategies.effect.";
    private static final String DEFAULT_TYPE = "NoEffect";

    private int myDamageValue;
    private int myTickCount;
    private double myTickDelay;
    private double myDuration;
    private double myRadius;
    private String myEffectType;

    public EffectBuilder setType(String effectType) {
        myEffectType = effectType;
        return this;
    }

    public String getType() {
        return myEffectType;
    }

    public EffectBuilder setDamage(String valueString) throws GameEngineException {
        try {
            myDamageValue = Integer.parseInt(valueString);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteEffectInitializationFailed");
        }
        return this;
    }

    public int getDamage() {
        return myDamageValue;
    }

    public EffectBuilder setTickCount(String tickCountString) throws GameEngineException {
        try {
            myTickCount = Integer.parseInt(tickCountString);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteEffectInitializationFailed");
        }
        return this;
    }

    public int getTickCount() {
        return myTickCount;
    }

    public EffectBuilder setTickDelay(String tickDelayString) throws GameEngineException {
        try {
            myTickDelay = Double.parseDouble(tickDelayString);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteEffectInitializationFailed");
        }
        return this;
    }

    public double getTickDelay() {
        return myTickDelay;
    }

    public EffectBuilder setDuration(String durationString) throws GameEngineException {
        try {
            myDuration = Double.parseDouble(durationString);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteEffectInitializationFailed");
        }
        return this;
    }

    public double getDuration() { return myDuration; }

    public EffectBuilder setRadius(String radiusString) throws GameEngineException {
        try {
            myRadius = Double.parseDouble(radiusString);
        } catch (NumberFormatException e) {
            throw new GameEngineException(e, "SpriteEffectInitializationFailed");
        }
        return this;
    }

    public double getRadius() {
        return myRadius;
    }

    @Override
    public EffectStrategy build() throws GameEngineException {
        if(myEffectType == null) {
            myEffectType = DEFAULT_TYPE;
        }
        try {
            return (EffectStrategy) Class.forName(CLASS_PATH + myEffectType).getConstructor(EffectBuilder.class).newInstance(this);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteEffectInitializationFailed");
        }
    }
}
