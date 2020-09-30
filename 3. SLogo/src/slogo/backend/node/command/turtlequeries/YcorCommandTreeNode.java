package slogo.backend.node.command.turtlequeries;


import slogo.backend.Controller;

public class YcorCommandTreeNode extends TurtleQueryCommandTreeNode {


    public YcorCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void queryAndSetReturnValue() {
        this.setReturnValue(this.getMyController().getTurtleY());
    }

}
