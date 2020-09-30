package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.AttackBuilder;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;


/**
 * An attack strategy that that consists of shooting bullets from multiple points around the sprite
 */
public class MultipleGunsAttack implements AttackStrategy {

    private AttackBuilder myBuilder;
    private double attackRate; //how many ticks until the next shot?
    private double elapsedTimeSinceLastAttack;
    private Integer bulletPrototypeID;
    private ArrayList<Double> shootingPositions;

    /**
     * Constructs a MultipleGunsAttack strategy
     * @param attackBuilder a copy of the attack builder that holds the information to create a specific instance of a
     *                      MultipleGunsAttack strategy
     */
    public MultipleGunsAttack(AttackBuilder attackBuilder){
        myBuilder = attackBuilder;
        attackRate = myBuilder.getAttackRate();
        bulletPrototypeID = myBuilder.getBulletPrototypeId();
        shootingPositions = myBuilder.getMyShootingPositions();
    }

    /**
     * Shoots bullets from multiple points around the sprite. Called every tick, but only attacks according to attackRate
     * @param elapsedTime time since last call to attack
     * @param currentAngle the current angle the sprite is facing
     * @param actionsRequester the LevelActionsRequester the of the level in which the sprite exists
     * @param currentPos the current position of the sprite
     * @throws GameEngineException
     */
    @Override
    public void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos) throws GameEngineException {
        elapsedTimeSinceLastAttack += elapsedTime;
        if(elapsedTimeSinceLastAttack >= attackRate){
            elapsedTimeSinceLastAttack = 0;
            shootBullet(currentAngle, actionsRequester, currentPos);
        }
    }

    /**
     * Spawns bullet sprites from every point on the sprite according to initialized shootingPositions
     * @param currentAngle the current angle the sprite is facing
     * @param actionsRequester the LevelActionsRequester the of the level in which the sprite exists
     * @param currentPos the current position of the sprite
     */
    private void shootBullet(double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos){
        for(double gun : shootingPositions){
            double shootFrom = (gun + currentAngle) % 360;
            LevelAction action = new AddSpriteAction(bulletPrototypeID, currentPos.getX(), currentPos.getY(), Math.toRadians(shootFrom));
            actionsRequester.requestAction(action);
            System.out.println("Shot at angle " + shootFrom);
        }
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same attack strategy
     * @throws GameEngineException
     */
    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        return new MultipleGunsAttack(myBuilder);
    }
}
