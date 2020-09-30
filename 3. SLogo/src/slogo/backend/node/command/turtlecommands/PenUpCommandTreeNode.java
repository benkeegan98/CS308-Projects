package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class PenUpCommandTreeNode extends TurtleCommandTreeNode {
    public PenUpCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    double doTurtleCommand() {
        this.getMyController().setPen(false);
        this.getMyController().getTurtleTurnup().updatePenDownSwitch(false);
        return 0;
    }
}
