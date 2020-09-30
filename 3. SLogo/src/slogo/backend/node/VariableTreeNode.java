package slogo.backend.node;

import slogo.backend.Controller;

/**
 * Tree Node that corresponds to any variable
 */
public class VariableTreeNode extends TreeNode {
    private String variableName;
    private Controller myController;

    /**
     *
     * @param controller Controller to send error messages to
     * @param word word that defines the name of the variable
     * @param index index of this input in the word list
     */
    public VariableTreeNode(Controller controller, String word, int index) {
        super(word, index);
        this.variableName = word;
        this.myController = controller;
        this.setReturnValue(controller.getVariable(word));
    }

    @Override
    public void doAction() {
        this.setReturnValue(myController.getVariable(variableName));
    }

    public String getVariableName() {
        return variableName;
    }
}
