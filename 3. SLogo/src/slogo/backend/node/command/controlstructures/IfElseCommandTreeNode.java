package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;

public class IfElseCommandTreeNode extends ControlTreeNode {
    public IfElseCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(3);
    }

    @Override
    protected double doTurtleCommand() {
        double truthValue = getInput(0);
        double result = 0;
        //Get the number of times to repeat
        if (truthValue > 0) {
            result = getInput(1);
        } else {
            result = getInput(2);
        }
        return result;
    }
}
