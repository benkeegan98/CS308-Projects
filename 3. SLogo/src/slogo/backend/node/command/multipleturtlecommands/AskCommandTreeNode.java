package slogo.backend.node.command.multipleturtlecommands;


import slogo.backend.Controller;
import slogo.backend.Turtle;
import slogo.backend.node.ListTreeNode;
import slogo.backend.node.TreeNode;
import slogo.backend.node.command.controlstructures.ControlTreeNode;

import java.util.ArrayList;

public class AskCommandTreeNode extends ControlTreeNode {
    public AskCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }


    @Override
    protected double doTurtleCommand() {
//        System.out.println("past active: "+pastActive);
        if (this.getMyController().getActiveId()==-1){
            ArrayList<Integer> ids = new ArrayList<>();
            ListTreeNode t = getList(0);
            for (TreeNode n : t.getMyTrees()) {
                int id = (int) n.getReturnValue();
                ids.add(id);
                if(!this.getMyController().getTurtles().keySet().contains(id)){
                    this.getMyController().getTurtles().put(id, new Turtle(0, 0));
                }
            }
            this.getMyController().setAskedIdList(ids);
            System.out.println("set ask list" + ids);
        }
        this.getChildren().get(1).doAction();
        return this.getChildren().get(1).getReturnValue();

    }

}
