package slogo.backend.node.command.booleancommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class OneInputBooleanCommandTreeNode extends CommandTreeNode {


    public OneInputBooleanCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    public void doAction() {
        this.setReturnValue(0);
        this.getChildren().get(0).doAction();
        double input = this.getChildren().get(0).getReturnValue();
        if (this.evaluate(input)) this.setReturnValue(1);
    }

    abstract boolean evaluate(double input);
}
