package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;
import slogo.backend.node.ListTreeNode;

public class DoTimesCommandTreeNode extends ControlTreeNode {
    public DoTimesCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    protected double doTurtleCommand() {
        ListTreeNode myList = getList(0);
        String myVariable = myList.getVariableNameAt(0);
        int myLimit = (int) myList.getReturnValueAt(1);
        double result = 0;
        //Get the number of times to repeat
        for (int i = 1; i <= myLimit; i++) {
            this.getMyController().setVariable(myVariable, (double) i);
            result = getInput(1);
        }
        return result;
    }
}
