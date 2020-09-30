package slogo.backend.node.command.booleancommands;

import slogo.backend.Controller;

public class NotEqualCommandTreeNode extends TwoInputBooleanCommandTreeNode {
    public NotEqualCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    boolean evaluate(Double[] inputs) {
        return inputs[0] != inputs[1];
    }

}
