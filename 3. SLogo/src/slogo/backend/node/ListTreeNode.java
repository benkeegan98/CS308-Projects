package slogo.backend.node;

import slogo.backend.Controller;
import slogo.backend.TreeBuilder;

import java.util.List;

/**
 * Node corresponding to any list node
 */
public class ListTreeNode extends TreeNode {

    private Controller myController;
    private List<TreeNode> myTrees;
    private String listLiteral;

    /**
     *
     * @param controller Controller to send errors to
     * @param wordsInList words within the list input
     */
    public ListTreeNode(Controller controller, String word, List<String> wordsInList, int index) {
        super(word, index);
        this.myController = controller;
        System.out.println("list of words in the list node: " + wordsInList);
        TreeBuilder tb = new TreeBuilder(controller);
        this.myTrees = tb.createTree(wordsInList);
        this.listLiteral = "[ " + String.join(" ", wordsInList) + " ]";
    }

    @Override
    public void doAction() {
        for (TreeNode tree : myTrees) {
            System.out.println("Index of tree node within List: " + tree.getIndex());
            tree.doAction();
        }
        this.setReturnValue(myTrees.get(0).getReturnValue());
    }

    public double getReturnValueAt(int index) {
        if( index < myTrees.size()){
            return myTrees.get(index).getReturnValue();
        }
        else {
            myController.setErrorText("Not enough input arguments in list!");
            return 0;
        }
    }

    public String getVariableNameAt(int index) {
        if (myTrees.get(index).getClass().equals(VariableTreeNode.class)) {
            myTrees.get(index).doAction();
            return myTrees.get(index).getWord();
        } else {
            myController.setErrorText("Variable expected in list.");
            return null;
        }
    }

    public int getListLength() {
        return myTrees.size();
    }

    public String getListLiteral() {
        return listLiteral;
    }

    public List<TreeNode> getMyTrees() {
        return myTrees;
    }
}
