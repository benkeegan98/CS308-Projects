package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class CosCommandTreeNode extends OneInputMathCommandTreeNode {


    public CosCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(double input) {
        this.setReturnValue(Math.cos((Math.toRadians(input))));
    }


}
