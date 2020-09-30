package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.AttackBuilder;

import java.awt.geom.Point2D;

/**
 * Outlines the case of no attack behavior. Mainly exists for the sake of polymorphism in sprite actions.
 */
public class NoAttack implements AttackStrategy {

    /**
     * Constructs a NoAttack strategy. Initializes nothing, because it defines no behavior.
     * @param attackBuilder exists for the sake of polymorphism.
     */
    public NoAttack(AttackBuilder attackBuilder){ }

    /**
     * No attack behavior. When attack is called, nothing happens.
     * @param elapsedTime time since last call to attack
     * @param currentAngle the current angle the sprite is facing
     * @param actionsRequester the LevelActionsRequester the of the level in which the sprite exists
     * @param currentPos the current position of the sprite
     */
    @Override
    public void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos) {
        //will never attack
    }

    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same attack strategy
     * @throws GameEngineException
     */
    @Override
    public AttackStrategy makeClone() throws GameEngineException {
        return new NoAttack(new AttackBuilder());
    }
}
