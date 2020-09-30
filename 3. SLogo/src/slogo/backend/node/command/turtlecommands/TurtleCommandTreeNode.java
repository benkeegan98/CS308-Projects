package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class TurtleCommandTreeNode extends CommandTreeNode {

    public TurtleCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    public void doAction() {
        this.setReturnValue(this.doTurtleCommand());
        Controller myController = this.getMyController();
        if (!myController.updateTurtleTurnup(myController.getTurtleX(), myController.getTurtleY(),
                myController.isPenDown(), myController.getTurtleDirection())) {
            // TODO: on frontend side, add an input to specify which turtle
            this.getMyController().revertTurtleStep();
        }
    }
    //TODO: Make sure these are correct

    protected double getOffsetX(double pixels) {
        return Math.sin(Math.toRadians(this.getMyController().getTurtleDirection())) * pixels;
    }

    protected double getOffsetY(double pixels) {
        return Math.cos(Math.toRadians(this.getMyController().getTurtleDirection())) * pixels;
    }

    protected double getInput(int inputIndex) {
        this.getChildren().get(inputIndex).doAction();
        return this.getChildren().get(inputIndex).getReturnValue();
    }

    abstract double doTurtleCommand();
}
