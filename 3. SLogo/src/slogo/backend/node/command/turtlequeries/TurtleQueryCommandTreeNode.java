package slogo.backend.node.command.turtlequeries;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class TurtleQueryCommandTreeNode extends CommandTreeNode {


    public TurtleQueryCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    public void doAction() {
        this.queryAndSetReturnValue();
    }

    abstract void queryAndSetReturnValue();
}
