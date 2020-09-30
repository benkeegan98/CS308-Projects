package slogo.backend.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract TreeNode to represent each element in the user input
 */
public abstract class TreeNode {
    private List<TreeNode> children = new ArrayList<>();
    private String word;
    private int index;
    private double returnValue;

    /**
     * @param word word in the user input
     * @param index index of this input in the word list
     */
    public TreeNode(String word, Integer index) {
        this.index = index;
        this.word = word;
    }

    /**
     * do the according action of this element, set return value of the element
     */
    public abstract void doAction();

    /**
     * Add child node to this tree node
     * @param child child node
     */
    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    protected int getIndex() {
        return index;
    }

    /**
     * @return get the word of this tree node
     */
    public String getWord() {
        return word;
    }

    /**
     * @return get the child tree nodes of this tree node
     */
    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * @return the return value of this tree node
     */
    public double getReturnValue() {
        return returnValue;
    }


    protected void setReturnValue(double returnValue) {
        this.returnValue = returnValue;
    }
}
