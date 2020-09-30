package slogo.backend;

import slogo.backend.node.TreeNode;

import java.util.List;

public class UserCommand {
    private String name;
    private List<String> variables;
    private TreeNode commandTree;
    private String userCommandLiteral;

    public UserCommand(String userCommandLiteral, List<String> variables, TreeNode commandTree) {
        this.variables = variables;
        this.commandTree = commandTree;
        this.userCommandLiteral = userCommandLiteral;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void executeCommandTree() {
        commandTree.doAction();
    }

    public String getName() {
        return name;
    }

    public String getUserCommandLiteral() {
        return userCommandLiteral;
    }
}