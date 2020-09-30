package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class PiCommandTreeNode extends ZeroInputMathCommandTreeNode {


    public PiCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue() {
        this.setReturnValue(Math.PI);
    }


}
