package slogo.backend.node.command.multipleturtlecommands;


import slogo.backend.Controller;
import slogo.backend.node.ListTreeNode;
import slogo.backend.node.TreeNode;
import slogo.backend.node.command.controlstructures.ControlTreeNode;

import java.util.ArrayList;

public class AskWithCommandTreeNode extends ControlTreeNode {
    public AskWithCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);
    }


    @Override
    protected double doTurtleCommand() {
        if (this.getMyController().getActiveId()==-1){
            ArrayList<Integer> ids = new ArrayList<>();
            ListTreeNode t = getList(0);
            TreeNode cond  = t.getMyTrees().get(0);
            for (int i : this.getMyController().getTurtles().keySet()){
                this.getMyController().setActiveId(i);
                cond.doAction();
                if ((int) cond.getReturnValue()==1){
                    ids.add(i);
                }
            }
            this.getMyController().setAskedIdList(ids);
            System.out.println("set ask with list" + ids);
        }
        this.getChildren().get(1).doAction();
        return this.getChildren().get(1).getReturnValue();
    }

}
