package slogo.backend.node.command.booleancommands;

import slogo.backend.Controller;

public class NotCommandTreeNode extends OneInputBooleanCommandTreeNode {


    public NotCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    boolean evaluate(double input) {
        return input == 0;
    }
}
