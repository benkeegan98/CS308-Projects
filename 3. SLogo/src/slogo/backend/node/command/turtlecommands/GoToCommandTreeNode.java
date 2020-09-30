package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class GoToCommandTreeNode extends TurtleCommandTreeNode {
    public GoToCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        setInputSize(2);
    }

    @Override
    double doTurtleCommand() {
        double startingX = this.getMyController().getTurtleX();
        double startingY = this.getMyController().getTurtleY();
        double newX = getInput(0);
        double newY = getInput(1);
        this.getMyController().setTurtleLocation(newX, newY);
        return Math.sqrt(Math.pow((newX - startingX), 2) + Math.pow((newY - startingY), 2));
    }
}
