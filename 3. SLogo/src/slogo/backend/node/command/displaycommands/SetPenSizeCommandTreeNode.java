package slogo.backend.node.command.displaycommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public class SetPenSizeCommandTreeNode extends CommandTreeNode {
    public SetPenSizeCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    public void doAction() {
        int myThickness = (int) Math.floor(getInput(0));
        getMyController().getTurtleTurnup().setPenWidth(myThickness);
        this.setReturnValue(myThickness);
    }
}
