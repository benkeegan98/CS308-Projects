package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class SetHeadingCommandTreeNode extends TurtleCommandTreeNode {
    public SetHeadingCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    double doTurtleCommand() {
        double newDirection = getInput(0);
        this.getMyController().setTurtleDirection(newDirection);
        return Math.abs(this.getMyController().getTurtleDirection() - newDirection);
    }
}
