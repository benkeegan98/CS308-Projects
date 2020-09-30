package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class ForwardCommandTreeNode extends TurtleCommandTreeNode {
    public ForwardCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    double doTurtleCommand() {
        double pixels = getInput(0);
        double myX = this.getOffsetX(pixels) + this.getMyController().getTurtleX();
        double myY = this.getOffsetY(pixels) + this.getMyController().getTurtleY();
        this.getMyController().setTurtleLocation(myX, myY);
        return pixels;
    }
}
