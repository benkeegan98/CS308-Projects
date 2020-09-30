package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;

public class IfCommandTreeNode extends ControlTreeNode {
    public IfCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    protected double doTurtleCommand() {
        double truthValue = getInput(0);
        double result = 0;
        //Get the number of times to repeat
        if (truthValue > 0) {
            result = getInput(1);
        }
        return result;
    }
}
