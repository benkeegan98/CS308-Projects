package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class HomeCommandTreeNode extends TurtleCommandTreeNode {
    public HomeCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    double doTurtleCommand() {
        double startingX = this.getMyController().getTurtleX();
        double startingY = this.getMyController().getTurtleY();
        this.getMyController().setTurtleLocation(0, 0);
        return Math.sqrt(Math.pow((startingX), 2) + Math.pow((startingY), 2));
    }
}
