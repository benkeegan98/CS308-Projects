package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public abstract class ControlTreeNode extends CommandTreeNode {

    public ControlTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    public void doAction() {
        this.setReturnValue(this.doTurtleCommand());
    }

    protected abstract double doTurtleCommand();

    protected String getVariableName(int inputIndex) {
        this.getChildren().get(inputIndex).doAction();
        return this.getChildren().get(inputIndex).getWord();
    }
}
