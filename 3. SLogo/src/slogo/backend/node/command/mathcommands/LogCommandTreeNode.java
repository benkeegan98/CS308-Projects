package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

public class LogCommandTreeNode extends OneInputMathCommandTreeNode {


    public LogCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(double input) {
        this.setReturnValue((Math.log(input)));
    }


}
