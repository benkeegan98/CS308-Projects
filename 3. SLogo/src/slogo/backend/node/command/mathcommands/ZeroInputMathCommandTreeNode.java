package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class ZeroInputMathCommandTreeNode extends CommandTreeNode {


    public ZeroInputMathCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    public void doAction() {
        this.calculateAndSetReturnValue();
    }

    abstract void calculateAndSetReturnValue();
}
