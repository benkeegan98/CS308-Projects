package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class ClearScreenCommandTreeNode extends TurtleCommandTreeNode {
    public ClearScreenCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    double doTurtleCommand() {
        double startingX = this.getMyController().getTurtleX();
        double startingY = this.getMyController().getTurtleY();
        this.getMyController().setTurtleLocation(0, 0);
        this.getMyController().setTurtleDirection(0);
        this.getMyController().getTurtleTurnup().clearLines();
        return Math.sqrt(Math.pow((startingX), 2) + Math.pow((startingY), 2));

    }

    @Override
    public void doAction() {
        this.getMyController().setPen(false);
        this.setReturnValue(this.doTurtleCommand());
        Controller myController = this.getMyController();
        myController.getTurtleTurnup().moveTurtle(myController.getTurtleX(), myController.getTurtleY(),
                myController.isPenDown(), myController.getTurtleDirection());
        this.getMyController().setPen(true);
    }
}
