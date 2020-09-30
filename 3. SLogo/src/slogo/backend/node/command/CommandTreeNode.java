package slogo.backend.node.command;

import slogo.backend.Controller;
import slogo.backend.node.ListTreeNode;
import slogo.backend.node.TreeNode;

/**
 * Abstract Command TreeNode class that defines the general behavior of Command Nodes.
 */
public abstract class CommandTreeNode extends TreeNode {
    private Controller myController;
    private int inputSize;
    private String commandType;

    /**
     *
     * @param controller Controller to send the
     * @param commandType Type of command
     */
    public CommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(word, index);
        this.myController = controller;
        this.commandType = commandType;
        this.inputSize = 2;
    }

    /**
     * @return number of expected inputs to the command
     */
    public int getInputSize() {
        return inputSize;
    }

    /** Sets the inputSize
     *
     * @param inputSize number of expected inputs to the command
     */
    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }

    /**
     * @return Controller that the command is associated with
     */
    public Controller getMyController() {
        return myController;
    }

    /**
     * @param inputIndex index of the child to get the input from
     * @return return value of the corresponding child
     */
    protected double getInput(int inputIndex) {
        this.getChildren().get(inputIndex).doAction();
        return this.getChildren().get(inputIndex).getReturnValue();
    }

    /** If applicable, returns the list in the specified child
     *
     * @param inputIndex index of the child to get the list from
     * @return ListTreeNode in the corresponding child
     */
    protected ListTreeNode getList(int inputIndex) {
        System.out.println(this.getChildren().get(inputIndex).getClass());
        if (this.getChildren().get(inputIndex).getClass().equals(ListTreeNode.class)) {
            System.out.println("This is a list\n");
            this.getChildren().get(inputIndex).doAction();
            return (ListTreeNode) this.getChildren().get(inputIndex);
        } else {
            getMyController().setErrorText("List Expected but Not Found!");
            return null;
        }
    }
}
