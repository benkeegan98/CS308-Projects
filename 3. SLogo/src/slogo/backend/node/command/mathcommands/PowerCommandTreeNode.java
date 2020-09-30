package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class PowerCommandTreeNode extends TwoInputMathCommandTreeNode {


    public PowerCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(Double[] inputs) {
        this.setReturnValue(Math.pow(inputs[0], inputs[1]));
    }
}
