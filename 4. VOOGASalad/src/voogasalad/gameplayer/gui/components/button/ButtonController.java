package voogasalad.gameplayer.gui.components.button;

import voogasalad.gameplayer.gui.PlayerVisualization;

/**
 * ButtonController class used for reflectively constructing the methods assigned as handlers to their respective buttons
 * in ButtonCreator
 *
 * This class is used as a middle way for allowing ButtonCreator to access certain functionality that is held in other
 * classes
 */
public class ButtonController {

    private PlayerVisualization playerVisualization;

    public ButtonController(PlayerVisualization playerVisualization) {
        this.playerVisualization = playerVisualization;
    }

    /**
     * button handler for our toggle start button
     */
    public void toggleStart() { playerVisualization.toggleStartAction(); }

    /**
     * button handler for our toggle mute button
     */
    public void toggleMute() { playerVisualization.toggleMuteAction(); }
}
