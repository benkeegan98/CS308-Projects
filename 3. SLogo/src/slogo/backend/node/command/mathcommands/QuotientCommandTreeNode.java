package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class QuotientCommandTreeNode extends TwoInputMathCommandTreeNode {


    public QuotientCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(Double[] inputs) {
        this.setReturnValue(inputs[0] / inputs[1]);
    }
}
