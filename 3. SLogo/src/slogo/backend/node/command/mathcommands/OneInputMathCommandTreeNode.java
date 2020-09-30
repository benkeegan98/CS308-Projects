package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class OneInputMathCommandTreeNode extends CommandTreeNode {


    public OneInputMathCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    public void doAction() {
        this.setReturnValue(0);
        this.getChildren().get(0).doAction();
        double input = this.getChildren().get(0).getReturnValue();
        this.calculateAndSetReturnValue(input);
    }

    abstract void calculateAndSetReturnValue(double input);
}
