package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelConditionsManager;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelStatusManager;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelWaveManager;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.control.levelcontrol.Wave;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelActionsManager;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.util.Collection;
import java.util.List;

public class LevelBuilder {

    private LevelStatusManager myLevelStatusManager;
    private SpriteManager mySpriteManager;
    private LevelWaveManager myLevelWaveManager;
    private LevelActionsManager myLevelActionsManager;
    private LevelConditionsManager myLevelConditionsManager;
    private SpriteProductsFactory mySpriteProductsFactory;
    private int myLevelId;
    private String myBackgroundPath;
    private String mySoundPath;
    private LevelActionsRequester myLevelActionsRequester;

    public LevelBuilder(int id) throws GameEngineException {
        myLevelId = id;
        mySpriteProductsFactory = new SpriteProductsFactory();
        myLevelActionsRequester = new LevelActionsRequester();
        myLevelStatusManager = new LevelStatusManager();
        myLevelWaveManager = new LevelWaveManager();
        myLevelActionsManager = new LevelActionsManager();
        myLevelConditionsManager = new LevelConditionsManager();
        mySpriteManager = mySpriteProductsFactory.makeSpriteManager(myLevelActionsRequester);
    }

    public String getBackgroundPath() {
        return myBackgroundPath;
    }

    public LevelActionsRequester getLevelActionsRequester() {
        return myLevelActionsRequester;
    }

    public LevelStatusManager getStatusManager() {
        return myLevelStatusManager;
    }

    public LevelWaveManager getWaveManager() {
        return myLevelWaveManager;
    }

    public LevelActionsManager getActionsManager() {
        return myLevelActionsManager;
    }

    public LevelConditionsManager getConditionsManager() {
        return myLevelConditionsManager;
    }

    public SpriteManager getSpriteManager() {
        return mySpriteManager;
    }

    public int getLevelId() {
        return myLevelId;
    }

    public String getSoundPath() {
        return mySoundPath;
    }

    public LevelBuilder setBackgroundPath(String backgroundPath) {
        myBackgroundPath = backgroundPath;
        return this;
    }

    public LevelBuilder setWaves(Collection<Wave> waves) {
        myLevelWaveManager.addWavesCollection(waves);
        return this;
    }

    public LevelBuilder setResources(int resources) {
        myLevelStatusManager.setResources(resources);
        return this;
    }

    public LevelBuilder setLives(int lives) {
        myLevelStatusManager.setLives(lives);
        return this;
    }

    public LevelBuilder setConditions(Collection<LevelCondition> conditions) {
        myLevelConditionsManager.addLevelConditionsCollection(conditions);
        return this;
    }

    public LevelBuilder setSpritePrototypes(List<Sprite> spritePrototypes) {
        for (Sprite sprite : spritePrototypes) {
            mySpriteManager.addSpritePrototype(sprite);
        }
        return this;
    }

    public LevelBuilder setSoundPath(String soundPath) {
        mySoundPath = soundPath;
        return this;
    }

    public Level build() {
        return new Level(this);
    }
}
