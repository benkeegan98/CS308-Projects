package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.AttackBuilder;

import java.awt.geom.Point2D;

/**
 * Outlines the behavior of a sprite that shoots bullets from a single point on the sprite.
 */
public class ShootingAttack implements AttackStrategy {

    private AttackBuilder myBuilder;
    private double attackRate; //how many ticks until the next shot?
    private double elapsedTimeSinceLastAttack;
    private Integer bulletPrototypeID;

    /**
     * Constructs a ShootingAttack strategy
     * @param attackBuilder a copy of the attack builder that holds the information to create a specific instance of a
     *                      ShootingAttack strategy
     */
    public ShootingAttack(AttackBuilder attackBuilder) {
        myBuilder = attackBuilder;
        attackRate = myBuilder.getAttackRate();
        bulletPrototypeID = myBuilder.getBulletPrototypeId();
    }

    /**
     * Shoots bullets from a single point on the sprite. Called every tick, but only attacks according to attackRate
     * @param elapsedTime time since last call to attack
     * @param currentAngle the current angle the sprite is facing
     * @param actionsRequester the LevelActionsRequester the of the level in which the sprite exists
     * @param currentPos the current position of the sprite
     */
    @Override
    public void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos) {
        elapsedTimeSinceLastAttack += elapsedTime;
        if(elapsedTimeSinceLastAttack >= attackRate){
            elapsedTimeSinceLastAttack = 0;
            shootBullet(currentAngle, actionsRequester, currentPos);
        }
    }

    /**
     * Spawns bullet sprites from a single point on the sprite.
     * @param currentAngle the current angle the sprite is facing
     * @param actionsRequester the LevelActionsRequester the of the level in which the sprite exists
     * @param currentPos the current position of the sprite
     */
    private void shootBullet(double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos){
        LevelAction action = new AddSpriteAction(bulletPrototypeID, currentPos.getX(), currentPos.getY(), Math.toRadians(currentAngle));
        actionsRequester.requestAction(action);
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same attack strategy
     * @throws GameEngineException
     */
    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        return myBuilder.build();
    }
}