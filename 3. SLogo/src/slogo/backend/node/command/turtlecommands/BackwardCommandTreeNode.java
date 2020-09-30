package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class BackwardCommandTreeNode extends TurtleCommandTreeNode {
    public BackwardCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    double doTurtleCommand() {
        double pixels = getInput(0);
        double myX = this.getMyController().getTurtleX() - this.getOffsetX(pixels);
        double myY = this.getMyController().getTurtleY() - this.getOffsetY(pixels);
        this.getMyController().setTurtleLocation(myX, myY);
        return pixels;
    }
}
