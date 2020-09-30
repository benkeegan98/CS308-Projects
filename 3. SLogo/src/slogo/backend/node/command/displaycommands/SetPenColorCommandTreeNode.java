package slogo.backend.node.command.displaycommands;

import javafx.scene.paint.Color;
import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

public class SetPenColorCommandTreeNode extends CommandTreeNode {
    public SetPenColorCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }

    @Override
    public void doAction() {
        int myIndex = (int) Math.floor(getInput(0));
        Color myColor = getMyController().getColor(myIndex);
        getMyController().getTurtleTurnup().setPenColor(myColor);
        getMyController().setCurrentColor(myIndex);
        this.setReturnValue(myIndex);
    }
}
