package voogasalad.gameengine.executors.sprites;

import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.awt.geom.Point2D;
import java.util.List;

public interface Sprite {
    Sprite makeClone(double x, double y, int spriteId) throws GameEngineException;
    double getX();
    double getY();
    int getId();
    int getHealth();
    SpriteArchetype getSpriteArchetype();
    Object getImage();
    void updatePosition(double elapsedTime);
    String getImagePath();
    int getPrototypeId();
    void updateShootingAngle(double elapsedTime);
    void shoot(double elapsedTime, LevelActionsRequester levelActionsRequester) throws GameEngineException;
    void updatePath(List<Point2D.Double> path);
    void updateMovementAngle(double angle);
    boolean isMovementFinished();
    void chunkHealth(int damage);
    boolean isDead();
    int getCreateCost();
    int getDestroyCost();
    void updateImage(String newImagePath);
    boolean isColliding(Sprite sprite);
    void updateAttackStrategy(AttackStrategy updatedStrategy);
    void setHasBeenClicked(boolean bool);
    boolean getHasBeenClicked();
    LevelAction getEffectAction(Sprite sprite) throws GameEngineException;
    void delayMovement(double duration);
    double distanceTo(Sprite sprite);
}
