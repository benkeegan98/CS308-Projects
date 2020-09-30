package slogo.backend.node.command.turtlequeries;


import slogo.backend.Controller;

public class XcorCommandTreeNode extends TurtleQueryCommandTreeNode {


    public XcorCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void queryAndSetReturnValue() {
        this.getMyController().getTurtleY();
        this.setReturnValue(this.getMyController().getTurtleX());
    }

}
