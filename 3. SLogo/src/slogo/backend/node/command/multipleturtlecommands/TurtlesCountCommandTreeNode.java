package slogo.backend.node.command.multipleturtlecommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public class TurtlesCountCommandTreeNode extends CommandTreeNode {
    public TurtlesCountCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    public void doAction() {
        this.setReturnValue(getMyController().getTurtles().size());
    }
}
