package slogo.backend.node;

/**
 * Corresponds to any constant input
 */
public class ConstantTreeNode extends TreeNode {

    private double myvalue;

    /**
     *
     * @param value value of the constant
     * @param word word specifying the type of constant(or name of placeholder constant)
     * @param index index of this input in the word list
     */
    public ConstantTreeNode(double value, String word, int index) {
        super(word, index);
        this.myvalue = value;
        this.setReturnValue((int) value);
    }

    @Override
    public void doAction() {
        this.setReturnValue((int) myvalue);
    }

    public double getMyvalue() {
        return myvalue;
    }
}
