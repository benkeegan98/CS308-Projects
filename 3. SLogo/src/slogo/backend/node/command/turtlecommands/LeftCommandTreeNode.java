package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class LeftCommandTreeNode extends TurtleCommandTreeNode {
    public LeftCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    double doTurtleCommand() {
        int degrees = (int) getInput(0);
        this.getMyController().setTurtleDirection(this.getMyController().getTurtleDirection() - degrees);

        return degrees;
    }
}
