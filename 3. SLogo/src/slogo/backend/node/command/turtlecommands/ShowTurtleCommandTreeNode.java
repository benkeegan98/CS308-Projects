package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class ShowTurtleCommandTreeNode extends TurtleCommandTreeNode {
    public ShowTurtleCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    double doTurtleCommand() {
        this.getMyController().getTurtleTurnup().hideTurtle(false);
        return 1;
    }
}
