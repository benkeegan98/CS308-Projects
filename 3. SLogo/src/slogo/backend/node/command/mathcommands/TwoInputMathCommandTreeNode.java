package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class TwoInputMathCommandTreeNode extends CommandTreeNode {


    public TwoInputMathCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    public void doAction() {
        this.setReturnValue(0);
        Double[] inputs = new Double[2];
        for (int i = 0; i < this.getChildren().size(); i++) {
            this.getChildren().get(i).doAction();
            inputs[i] = this.getChildren().get(i).getReturnValue();
        }
        this.calculateAndSetReturnValue(inputs);
    }

    abstract void calculateAndSetReturnValue(Double[] inputs);
}
