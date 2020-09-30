package slogo.backend.node.command.turtlequeries;


import slogo.backend.Controller;

public class TurtleShowCommandTreeNode extends TurtleQueryCommandTreeNode {


    public TurtleShowCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void queryAndSetReturnValue() {
        this.setReturnValue(getMyController().isTurtleShow() ? 1 : 0);
    }

}
