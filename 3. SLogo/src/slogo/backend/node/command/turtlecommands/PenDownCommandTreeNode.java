package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class PenDownCommandTreeNode extends TurtleCommandTreeNode {
    public PenDownCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    double doTurtleCommand() {
        this.getMyController().setPen(true);
        this.getMyController().getTurtleTurnup().updatePenDownSwitch(true);
        return 0;
    }
}
