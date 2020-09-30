package slogo.backend.node;

import slogo.backend.Controller;
import slogo.backend.TreeBuilder;
import slogo.backend.node.command.CommandTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Node that corresponds to any Group input
 */
public class GroupTreeNode extends TreeNode{
    private List<TreeNode> myTrees;

    /**
     *
     * @param controller Controller to send errors to
     * @param Command Previously created commandTreeNode (first index of a group)
     * @param commandInputs Inputs to the command
     * @param index index of this input in the word list
     */
    public GroupTreeNode(Controller controller, CommandTreeNode Command, List<String> commandInputs, int index) {
        super("Group", index);
        TreeBuilder tb = new TreeBuilder(controller);
        int numInputs = Command.getInputSize();
        String myCommandType = commandInputs.get(0);

        List<String> newInput = new ArrayList<>();
        int inputIndex = 1;
        while(inputIndex < commandInputs.size()){
            newInput.add(myCommandType);
            newInput.addAll(commandInputs.subList(inputIndex, inputIndex+numInputs));
            inputIndex = inputIndex+numInputs;
        }
        this.myTrees = tb.createTree(newInput);
    }

    @Override
    public void doAction() {
        for (TreeNode tree : myTrees) {
            tree.doAction();
        }
        this.setReturnValue(myTrees.get(0).getReturnValue());
    }
}
