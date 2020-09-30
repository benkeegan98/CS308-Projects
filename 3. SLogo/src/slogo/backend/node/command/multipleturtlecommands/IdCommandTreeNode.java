package slogo.backend.node.command.multipleturtlecommands;

import slogo.backend.Controller;
import slogo.backend.node.command.CommandTreeNode;

import java.sql.SQLOutput;

public class IdCommandTreeNode extends CommandTreeNode {
    public IdCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(0);
    }

    @Override
    public void doAction() {
        System.out.println("MY ACTIVE ID: "+getMyController().getActiveId());
        this.setReturnValue(getMyController().getActiveId());
    }
}
