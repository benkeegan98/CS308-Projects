package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;

public class MakeCommandTreeNode extends ControlTreeNode {
    public MakeCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    protected double doTurtleCommand() {
        String myVariable = getVariableName(0);
        double myValue = getInput(1);
        getMyController().setVariable(myVariable, myValue);
        return myValue;
    }
}
