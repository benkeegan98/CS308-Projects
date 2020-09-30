package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class HideTurtleCommandTreeNode extends TurtleCommandTreeNode {
    public HideTurtleCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    double doTurtleCommand() {
        this.getMyController().getTurtleTurnup().hideTurtle(true);
        return 0;
    }
}
