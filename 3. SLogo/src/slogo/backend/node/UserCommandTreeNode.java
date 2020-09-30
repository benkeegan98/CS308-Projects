package slogo.backend.node;

import slogo.backend.Controller;
import slogo.backend.UserCommand;
import slogo.backend.node.command.CommandTreeNode;

/**
 * UserCommandTreeNode, which represents any nodes that are defined by previous user commands.
 */
public class UserCommandTreeNode extends CommandTreeNode {
    private Controller myController;
    private int inputSize;
    private UserCommand myUserCommand;

    /**
     *
     * @param controller Controller, used to send error messages
     * @param word word that defines the name of the user command
     * @param myCommand previously defined user command
     * @param index index of this input in the word list
     */
    public UserCommandTreeNode(Controller controller, String word, UserCommand myCommand, Integer index) {
        super(controller, word, word, index);
        this.myController = controller;
        this.myUserCommand = myCommand;
        this.inputSize = myCommand.getVariables().size();
        System.out.println("\n\n\n Numeber of inputs: " +this.inputSize+"\n\n\n\n\n");
    }

    @Override
    public void doAction() {
        this.setReturnValue(executeUserCommand());
    }

    /**
     * @return number of expected inputs to the user function
     */
    public int getInputSize() {
        return inputSize;
    }

    private double executeUserCommand() {
        System.out.println("Size of variable list: " + myUserCommand.getVariables().size());
        System.out.println("Number of Children: " + getChildren().size());
        for (int i = 0; i < myUserCommand.getVariables().size(); i++) {
            myController.setVariable(myUserCommand.getVariables().get(i), getChildren().get(i).getReturnValue());
        }
        myUserCommand.executeCommandTree();
        return 0;
    }
}
