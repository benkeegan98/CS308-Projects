package slogo.backend.node.command.displaycommands;

import javafx.scene.paint.Color;
import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public class SetBackgroundCommandTreeNode extends CommandTreeNode {
    public SetBackgroundCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    public void doAction() {
        int myIndex = (int) Math.floor(getInput(0));
        Color myColor = getMyController().getColor(myIndex);
        getMyController().getTurtleTurnup().setBackgroundFill(myColor);
        this.setReturnValue(myIndex);
    }
}
