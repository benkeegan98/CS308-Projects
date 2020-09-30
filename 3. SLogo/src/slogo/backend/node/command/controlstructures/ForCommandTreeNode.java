package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;
import slogo.backend.node.ListTreeNode;

public class ForCommandTreeNode extends ControlTreeNode {
    public ForCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    protected double doTurtleCommand() {
        ListTreeNode myList = getList(0);
        String myVariable = myList.getVariableNameAt(0);
        double myStart = myList.getReturnValueAt(1);
        double myEnd = myList.getReturnValueAt(2);
        double myIncrement = myList.getReturnValueAt(3);
        double result = 0;
        double value = 0;
        System.out.println(myEnd);
        //Get the number of times to repeat
        for (int i = 0; value <= myEnd; i++) {
            value = myStart + myIncrement * i;
            System.out.println(value);
            this.getMyController().setVariable(myVariable, value);
            result = getInput(1);
        }
        return result;
    }
}
