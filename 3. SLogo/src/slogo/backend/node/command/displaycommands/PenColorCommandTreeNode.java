package slogo.backend.node.command.displaycommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public class PenColorCommandTreeNode extends CommandTreeNode {

    public PenColorCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    public void doAction() {
        this.setReturnValue(getMyController().getCurrentColor());
    }
}
