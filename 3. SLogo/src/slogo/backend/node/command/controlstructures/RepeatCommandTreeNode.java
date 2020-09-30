package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;

public class RepeatCommandTreeNode extends ControlTreeNode {

    public RepeatCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    protected double doTurtleCommand() {
        double numRepeat = getInput(0);
        double result = 0;
        for (int i = 1; i <= numRepeat; i++) {
            this.getMyController().setVariable(":repcount", (double) i);
            result = getInput(1);
        }
        return result;
    }


}
