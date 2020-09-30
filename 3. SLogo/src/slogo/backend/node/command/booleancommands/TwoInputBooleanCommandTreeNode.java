package slogo.backend.node.command.booleancommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class TwoInputBooleanCommandTreeNode extends CommandTreeNode {


    public TwoInputBooleanCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    public void doAction() {
        this.setReturnValue(1);
        Double[] inputs = new Double[2];
        for (int i = 0; i < this.getChildren().size(); i++) {
            this.getChildren().get(i).doAction();
            inputs[i] = this.getChildren().get(i).getReturnValue();
        }
        if (! this.evaluate(inputs)) {
            this.setReturnValue(0);
        }
    }

    abstract boolean evaluate(Double[] inputs);
}
