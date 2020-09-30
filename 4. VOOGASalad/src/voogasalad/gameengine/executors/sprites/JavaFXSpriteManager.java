package voogasalad.gameengine.executors.sprites;

import javafx.scene.image.ImageView;
import voogasalad.gameengine.executors.control.action.level.AlterLivesAction;
import voogasalad.gameengine.executors.control.action.level.AlterResourcesAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.*;

public class JavaFXSpriteManager implements SpriteManager {

    private static final int CHUNK_LIVES_VALUE = -1;

    private List<Sprite> myOnScreenSprites;
    private Map<Integer, Sprite> mySpritePrototypes;
    private int mySpriteIdGenerator;
    private LevelActionsRequester myLevelActionsRequester;

    public JavaFXSpriteManager(LevelActionsRequester levelActionsRequester) {
        myOnScreenSprites = new ArrayList<>();
        mySpritePrototypes = new HashMap<>();
        mySpriteIdGenerator = 0;
        myLevelActionsRequester = levelActionsRequester;
    }

    @Override
    public void addSpritePrototype(Sprite sprite) {
        mySpritePrototypes.put(sprite.getPrototypeId(), sprite);
    }


    @Override
    public List<Sprite> getSpritePrototypes(){
        ArrayList<Sprite> spritePrototypes = new ArrayList<>();
        for(Integer prot : mySpritePrototypes.keySet() ){
            spritePrototypes.add(mySpritePrototypes.get(prot));
        }
        return spritePrototypes;
    }

    public Sprite getCopyPrototype(int prototypeId) throws GameEngineException {
        for (Integer index : mySpritePrototypes.keySet()) {
            if (mySpritePrototypes.get(index).getPrototypeId() == prototypeId) {
                return mySpritePrototypes.get(index).makeClone(0,0,0);
            }
        }
        return null;
    }

    @Override
    public List<Sprite> getOnScreenSpritesByArchetype(SpriteArchetype archetype) {
        List<Sprite> archetypeList = new ArrayList<>();
        for (Sprite sprite : myOnScreenSprites) {
            if (sprite.getSpriteArchetype() == archetype) {
                archetypeList.add(sprite);
            }
        }
        return archetypeList;
    }

    @Override
    public Sprite makeSpriteFromPrototype(double xPos, double yPos, int prototypeId) throws GameEngineException {
        int spriteId = mySpriteIdGenerator++;
        Sprite sprite = mySpritePrototypes.get(prototypeId).makeClone(xPos, yPos, spriteId);
        myOnScreenSprites.add(sprite);
        return sprite;
    }


    @Override
    public Sprite removeSpriteById(int spriteId) {
        Sprite toRemove=null;
        for (Sprite sprite : myOnScreenSprites) {
            if (sprite.getId() == spriteId) {
                toRemove = sprite;
            }
        }
        myOnScreenSprites.remove(toRemove);
        return toRemove;
    }

    @Override
    public Sprite getSpriteById(int spriteId) {
        Sprite toReturn = null;
        for (Sprite sprite : myOnScreenSprites) {
            if(sprite.getId() == spriteId) {
                toReturn = sprite;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public Sprite removeSpriteTowerByCoordinates(double xpos, double ypos) {
        Sprite spriteToRemove=null;
        for (Sprite sprite : myOnScreenSprites) {
            ImageView spriteImageView = (ImageView) sprite.getImage();
            if (sprite.getSpriteArchetype() == SpriteArchetype.TOWER && spriteImageView.getBoundsInParent().contains(xpos,ypos)) {
                spriteToRemove = sprite;
                LevelAction action = new AlterResourcesAction(sprite.getDestroyCost());
                myLevelActionsRequester.requestAction(action);
            }
        }
        myOnScreenSprites.remove(spriteToRemove);
        return spriteToRemove;
    }

    @Override
    public List<Sprite> getCopyOnScreenSprites() {
        List<Sprite> listCopy = new ArrayList<>();
        for(Sprite sprite: myOnScreenSprites){
            listCopy.add(sprite);
        }
        return listCopy;
    }

    @Override
    public List<Sprite> getOnsScreenSprites() {
        return myOnScreenSprites;
    }

    @Override
    public List<Sprite> getCopyPrototypesForArchetype(SpriteArchetype archetype) throws GameEngineException {
        List<Sprite> spritePrototypesOfArchetype = new ArrayList<>();
        for (Integer key : mySpritePrototypes.keySet()) {
            if (mySpritePrototypes.get(key).getSpriteArchetype()==archetype) {
                spritePrototypesOfArchetype.add(mySpritePrototypes.get(key).makeClone(0, 0, 0));
            }
        }
        return spritePrototypesOfArchetype;
    }

    @Override
    public void executeSpriteNextState(double elapsedTime) throws GameEngineException {
        handleProjectileCollisions();
        Set<Sprite> spritesToRemove = new HashSet<>();
        for (Sprite sprite : myOnScreenSprites) {
            if(sprite.isMovementFinished()){
                if (sprite.getSpriteArchetype() == SpriteArchetype.ENEMY) {
                    LevelAction action = new AlterLivesAction(CHUNK_LIVES_VALUE);
                    myLevelActionsRequester.requestAction(action);
                }
                spritesToRemove.add(sprite);
                continue;
            } else if(sprite.isDead()){
                if (sprite.getSpriteArchetype() == SpriteArchetype.ENEMY) {
                    LevelAction action = new AlterResourcesAction(sprite.getDestroyCost());
                    myLevelActionsRequester.requestAction(action);
                }
                spritesToRemove.add(sprite);
                continue;
            }
            sprite.updatePosition(elapsedTime);
            sprite.updateShootingAngle(elapsedTime);
            sprite.shoot(elapsedTime, myLevelActionsRequester);
        }
        myOnScreenSprites.removeAll(spritesToRemove);
        System.out.println("executed next sprite state");
    }

    @Override
    public void handleProjectileCollisions() throws GameEngineException {
        List<Sprite> projectileList = getOnScreenSpritesByArchetype(SpriteArchetype.PROJECTILE);
        List<Sprite> enemyList = getOnScreenSpritesByArchetype(SpriteArchetype.ENEMY);
        projectileloop:
            for(Sprite projectile : projectileList) {
                for(Sprite enemy : enemyList) {
                    if(projectile.isDead()) {
                        continue projectileloop;
                    }
                    if(projectile.isColliding(enemy)) {
                        myLevelActionsRequester.requestAction(projectile.getEffectAction(enemy));
                    }
                }
            }
    }
}
