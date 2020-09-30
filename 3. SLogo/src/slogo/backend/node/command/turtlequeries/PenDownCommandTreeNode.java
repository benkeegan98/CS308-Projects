package slogo.backend.node.command.turtlequeries;


import slogo.backend.Controller;

public class PenDownCommandTreeNode extends TurtleQueryCommandTreeNode {


    public PenDownCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void queryAndSetReturnValue() {
        this.setReturnValue(getMyController().isPenDown() ? 1 : 0);
    }

}
