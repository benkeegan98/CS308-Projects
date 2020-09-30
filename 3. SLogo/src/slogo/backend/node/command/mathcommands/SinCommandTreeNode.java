package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class SinCommandTreeNode extends OneInputMathCommandTreeNode {


    public SinCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(double input) {
        this.setReturnValue(Math.sin((Math.toRadians(input))));
    }


}
