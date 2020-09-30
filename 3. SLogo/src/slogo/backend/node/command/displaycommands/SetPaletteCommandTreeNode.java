package slogo.backend.node.command.displaycommands;

import slogo.backend.Controller;
import slogo.backend.node.ListTreeNode;
import slogo.backend.node.command.CommandTreeNode;

public class SetPaletteCommandTreeNode extends CommandTreeNode {
    public SetPaletteCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }

    @Override
    public void doAction() {
        int myIndex = (int) Math.floor(getInput(0));
        ListTreeNode myRBG = getList(1);
        int[] myRBGArray = new int[]{(int)myRBG.getReturnValueAt(0), (int)myRBG.getReturnValueAt(1),
                (int)myRBG.getReturnValueAt(2)};
        getMyController().setColor(myIndex, myRBGArray);
        this.setReturnValue(myIndex);
    }
}
