package voogasalad.gameengine.executors.sprites.strategies.attack;

import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.awt.geom.Point2D;

/**
 * Outlines the attack behavior of a certain sprite. Must exist within an initialized sprite.
 */

public interface AttackStrategy {

    /**
     * Attacks according to the different combination of factors defined by the attack strategy
     * @param elapsedTime time since last call to attack
     * @param currentAngle the current angle the sprite is facing
     * @param actionsRequester the LevelActionsRequester the of the level in which the sprite exists
     * @param currentPos the current position of the sprite
     */
    void attack(double elapsedTime, double currentAngle, LevelActionsRequester actionsRequester, Point2D.Double currentPos)
            throws GameEngineException;


    /**
     * Makes a clone of itself in order to fabricate many sprites with the same strategy characteristics
     * @return a clone of the same attack strategy
     * @throws GameEngineException
     */
    AttackStrategy makeClone() throws GameEngineException;
}