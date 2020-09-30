package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class TanCommandTreeNode extends OneInputMathCommandTreeNode {


    public TanCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(double input) {
        this.setReturnValue(Math.tan((Math.toRadians(input))));
    }


}
