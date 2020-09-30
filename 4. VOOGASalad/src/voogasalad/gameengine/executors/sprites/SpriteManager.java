package voogasalad.gameengine.executors.sprites;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;
import java.util.Map;

public interface SpriteManager {
    void addSpritePrototype(Sprite sprite);

    Sprite makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException;

    List<Sprite> getCopyOnScreenSprites();

    List<Sprite> getOnsScreenSprites();

    List<Sprite> getSpritePrototypes();

    List<Sprite> getOnScreenSpritesByArchetype(SpriteArchetype archetype);

    Sprite removeSpriteById(int spriteId);

    Sprite getSpriteById(int spriteId);

    List<Sprite> getCopyPrototypesForArchetype(SpriteArchetype archetype) throws GameEngineException;

    void executeSpriteNextState(double elapsedTime) throws GameEngineException;

    Sprite removeSpriteTowerByCoordinates(double xpos, double ypos);

    void handleProjectileCollisions() throws GameEngineException;

    Sprite getCopyPrototype(int prototypeId) throws GameEngineException;

    }