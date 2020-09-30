package slogo.backend.node.command.multipleturtlecommands;

import slogo.backend.Controller;
import slogo.backend.Turtle;
import slogo.backend.node.ListTreeNode;
import slogo.backend.node.TreeNode;
import slogo.backend.node.command.controlstructures.ControlTreeNode;

import java.util.ArrayList;

public class TellCommandTreeNode extends ControlTreeNode {
    public TellCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(1);
    }


    @Override
    protected double doTurtleCommand() {
        ArrayList<Integer> ids = new ArrayList<>();
        ListTreeNode t = getList(0);
        for (TreeNode n : t.getMyTrees()) {
            int id = (int) n.getReturnValue();
            for (int i = 0; i <= id; i++) {
                if (!getMyController().getTurtles().containsKey(i)) {
                    getMyController().getTurtles().put(i, new Turtle(0, 0));
                }
            }
            if (!ids.contains(id)) ids.add(id);
        }
        System.out.println(ids);
        this.getMyController().setToldIdList(ids);
        System.out.println("current active list: " + getMyController().getToldIdList());
        return 0.0;
    }
}
