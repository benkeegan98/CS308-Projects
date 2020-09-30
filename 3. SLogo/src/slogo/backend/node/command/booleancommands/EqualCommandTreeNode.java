package slogo.backend.node.command.booleancommands;

import slogo.backend.Controller;

public class EqualCommandTreeNode extends TwoInputBooleanCommandTreeNode {
    public EqualCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    boolean evaluate(Double[] inputs) {
        return inputs[0].equals(inputs[1]);
    }

}
