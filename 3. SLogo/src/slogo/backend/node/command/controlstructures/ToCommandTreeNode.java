package slogo.backend.node.command.controlstructures;

import slogo.backend.Controller;
import slogo.backend.UserCommand;
import slogo.backend.node.ListTreeNode;

import java.util.ArrayList;
import java.util.List;

public class ToCommandTreeNode extends ControlTreeNode {
    public ToCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        setInputSize(3);
    }

    @Override
    protected double doTurtleCommand() {
        String myCommandName = getCommandName(0);
        ListTreeNode myVariables = getList(1);
        ListTreeNode myCommandTree = getList(2);
        String userCommandLiteral = "to " + myCommandName + " " + myVariables.getListLiteral() + " " + myCommandTree.getListLiteral();
        this.getMyController().addCommand(myCommandName,
                new UserCommand(userCommandLiteral, getVariableList(myVariables), myCommandTree));
        System.out.println("COMMAND TREE:" + myCommandTree);
        return 1;
    }

    private String getCommandName(int inputIndex) {
        return this.getChildren().get(inputIndex).getWord();
    }

    private List<String> getVariableList(ListTreeNode myVariables) {
        List<String> variableList = new ArrayList<>();
        for (int i = 0; i < myVariables.getListLength(); i++) {
            variableList.add(myVariables.getVariableNameAt(i));
        }
        return variableList;
    }

    @Override
    protected ListTreeNode getList(int inputIndex) {
        if (this.getChildren().get(inputIndex).getClass().equals(ListTreeNode.class)) {
            System.out.println("This is a list\n");
            return (ListTreeNode) this.getChildren().get(inputIndex);
        } else {
            getMyController().setErrorText("Incorrect input format. List Expected!");
        }
        return null;
    }
}
