package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class MinusCommandTreeNode extends OneInputMathCommandTreeNode {


    public MinusCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(double input) {
        this.setReturnValue(-1 * input);
    }


}
