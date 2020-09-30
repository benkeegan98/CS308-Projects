package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;
/**
 * Class: AddSpriteAction
 * Purpose: Adds a sprite to the current sprites on screen
 * Assumptions: N/A
 * Dependencies:N/A
 * Example of how to use: Used by the ActionsRequester to process and UI place tower action
 * Other details:
 */
public class AddSpriteAction implements LevelAction {
    private int myPrototypeIndex;
    private double myXPos;
    private double myYPos;
    private Double myAngle;

    /**
     * Purpose: Constructor for action to add a sprite on screen based on a prototype index and an x and y coordinate
     * Assumptions: N/A
     * @param prototypeIndex the index of the prototype of which this sprite will be one of
     * @param xpos the x position to add the sprite
     * @param ypos the y position to add the sprite
     */
    public AddSpriteAction(int prototypeIndex, double xpos, double ypos) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
    }

    /**
     * Purpose: Constructor for action to add a sprite at a specific angle when sprite angles matter
     * Assumptions: N/A
     * @param prototypeIndex the index of the prototype of which this sprite will be one of
     * @param xpos the x position to add the sprite
     * @param ypos the y position to add the sprite
     * @param angle the angle of which the sprite will be facing
     */
    public AddSpriteAction(int prototypeIndex, double xpos, double ypos, double angle) {
        myPrototypeIndex = prototypeIndex;
        myXPos = xpos;
        myYPos = ypos;
        myAngle = angle;
    }

    /**
     * Purpose: Executes the add sprite action on the level given
     * Assumptons:N/A
     * @param level the level of which the action will be acting upon
     * @throws GameEngineException if the sprite to be added fails to create
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        Sprite spritePrototype = level.getSpriteManager().getCopyPrototype(myPrototypeIndex);
        if (spritePrototype.getCreateCost() <= level.getStatusManager().getResources()) {
            Sprite sprite = level.getSpriteManager().makeSpriteFromPrototype(myXPos, myYPos, myPrototypeIndex);
            int scoreToSubtract = sprite.getCreateCost();
            level.getStatusManager().alterResourcesByValue(-scoreToSubtract);
            if (myAngle != null) {
                sprite.updateMovementAngle(myAngle);
            }
        }
    }

    /**
     * Purpose: Boolean to check if the action has finished
     * Assumptions: This action takes 1 frame to add sprites
     * @return true
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
