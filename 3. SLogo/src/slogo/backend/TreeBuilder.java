package slogo.backend;

import slogo.backend.node.*;
import slogo.backend.node.command.CommandTreeNode;
import slogo.backend.node.command.controlstructures.ToCommandTreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Builder for the command tree
 */
public class TreeBuilder {
    private final String COMMANDCLASS_PATH = "slogo.backend.node.command.";
    private final ResourceBundle rb = ResourceBundle.getBundle("resources.languages/CommandClassNames");
    private Pattern commandPattern = Pattern.compile(NodeType.COMMAND.getSyntax());
    private Pattern constantPattern = Pattern.compile(NodeType.CONSTANT.getSyntax());
    private Pattern variablePattern = Pattern.compile(NodeType.VARIABLE.getSyntax());
    private Pattern listStartPattern = Pattern.compile(NodeType.LISTSTART.getSyntax());
    private Pattern listEndPattern = Pattern.compile(NodeType.LISTEND.getSyntax());
    private Pattern groupStartPattern = Pattern.compile(NodeType.GROUPSTART.getSyntax());
    private Pattern groupEndPattern = Pattern.compile(NodeType.GROUPEND.getSyntax());
    private CommandDictionary commandDictionary = new CommandDictionary();
    private Controller myController;

    private int currentIndex;

    /**
     * @param controller controller in the backend
     */
    public TreeBuilder(Controller controller) {
        this.myController = controller;
    }

    /**
     * Accepts a list of words (starting with a command) and creates a tree structure of commands
     * and their associated parameters. Throws an error if there are parameters without an associated command,
     * since entries that do not start with a command are meaningless.
     *
     * @param wordList List of Words to create the tree with
     * @return List of tree nodes constructed from the wordList
     */
    public List<TreeNode> createTree(List<String> wordList) {
        currentIndex = -1;
        System.out.println("Builder");
        List<TreeNode> rootList = new ArrayList<>();
        while (currentIndex < wordList.size() - 1) {
            currentIndex++;
            if (wordList.get(currentIndex).equals("SAVE")) {
                String filename = wordList.get(currentIndex + 1);
                FileSaver fileSaver = new FileSaver(new UserDefinedCommandsAndVars(this.myController.getCommands(), this.myController.getVariables()));
                fileSaver.writeFile(myController, filename);
                currentIndex += 2;
            } else if (wordList.get(currentIndex).equals("READ")) {
                String filename = wordList.get(currentIndex + 1);
                String input = FileSaver.readFile(myController, filename);
                this.myController.parseAndPerform(input);
                currentIndex += 2;
            } else if (match(wordList.get(currentIndex), commandPattern)) {
                TreeNode root = (TreeNode) createNewCommandNode(wordList.get(currentIndex), currentIndex);
                populateChildren(wordList, root);
                if (root.getClass().equals(ToCommandTreeNode.class)) { root.doAction(); }
                rootList.add(root);
            } else if (match(wordList.get(currentIndex), groupStartPattern)) {
                int myIndex = getEndIndex(wordList);
                rootList.add(new GroupTreeNode(myController, (CommandTreeNode)createNewCommandNode(wordList.get(currentIndex+1), currentIndex+1),
                        wordList.subList(currentIndex + 1, myIndex), currentIndex));
                currentIndex = myIndex;
            } else if (match(wordList.get(currentIndex), listStartPattern)) {
                myController.setErrorText("ONLY ALLOW ROOTS TO BE COMMANDS, VARIABLES, OR CONSTANTS");
            } else {
                TreeNode root = createTreeHelper(wordList, currentIndex, null);
                rootList.add(root);
            }
        }
        return rootList;
    }

    /**
     * Determines number of leaves to attach to root node and creates these leaves.
     */
    private void populateChildren(List<String> wordList, TreeNode root) {
        for (int i = 0; i < ((CommandTreeNode) root).getInputSize(); i++) {
            if (currentIndex >= wordList.size() - 1){
                myController.setErrorText("NOT ENOUGH INPUTS TO COMMAND!");
                break;
            }
            try {
                root.addChild(createTreeHelper(wordList, currentIndex + 1, root));
            } catch (RuntimeException e) {
                //TODO: error handling
            }
        }
    }

    /**
     * Returns the index of the end of a list. If the startIndex specified does not specify a list,
     * the program will return 0.
     */
    private int getEndIndex(List<String> wordList) {
        int endIndex = 0;
        if (match(wordList.get(currentIndex), listStartPattern)) {
            endIndex = currentIndex + 1;
            int nestedLists = 0;
            while (!match(wordList.get(endIndex), listEndPattern) || nestedLists != 0) {
                if (match(wordList.get(endIndex), listStartPattern)) { nestedLists++; }
                if (match(wordList.get(endIndex), listEndPattern)) { nestedLists--; }
                endIndex++;
            }
        }
        if(match(wordList.get(currentIndex), groupStartPattern)){
            endIndex = currentIndex + 1;
            while(!match(wordList.get(endIndex), groupEndPattern)){
                endIndex++;
            }
        }
        return endIndex;
    }

    /**
     * Creates a new command node. If the command is unrecognized, throws error.
     */
    private Object createNewCommandNode(String type, Integer index) {
        if (myController.commandDefined(type)) {
            return new UserCommandTreeNode(myController, type, myController.getCommand(type), currentIndex);
        }
        System.out.println(type);
        try {
            type = commandDictionary.getTranslationInEnglish(type);
        } catch (RuntimeException e) {
            myController.setErrorText("WORD NOT FOUND IN LANGUAGE, word: " + type);
            return null;
        }
        Class<?> clazz;
        try {
            clazz = Class.forName(COMMANDCLASS_PATH + rb.getString(type));
            Object ret =  clazz.getDeclaredConstructor(Controller.class, String.class, String.class, Integer.class).newInstance(this.myController, type, type, index);
            return ret;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(rb.getString(type));
            myController.setErrorText("CANNOT FIND INPUT COMMAND! Command: " + type);
            return null;
        }
    }

    /**
     * Creates nodes based on the type of regex read in
     */
    private TreeNode createTreeHelper(List<String> wordList, int index, TreeNode parent) {
        currentIndex = Math.max(currentIndex, index);
        String currentWord = wordList.get(index);
        TreeNode currentNode;
        int endIndex = getEndIndex(wordList);
        if (endIndex != 0) {
            currentNode = new ListTreeNode(this.myController, wordList.get(index), wordList.subList(index + 1, endIndex), endIndex);
            currentIndex = endIndex;
        } else if (match(currentWord, commandPattern)) {
            if (parent.getClass().equals(ToCommandTreeNode.class)) {
                currentNode = new ConstantTreeNode(0, currentWord, index);
            } else {
                currentNode = (CommandTreeNode) createNewCommandNode(currentWord, index);
                populateChildren(wordList, currentNode);
            }
        } else if (match(currentWord, constantPattern)) {
            currentNode = new ConstantTreeNode(Double.parseDouble(currentWord), currentWord, index);
        } else if (match(currentWord, variablePattern)) {
            currentNode = new VariableTreeNode(this.myController, currentWord, index);
        } else {
            myController.setErrorText("Entry format not found: " + currentWord);
            currentNode = new ConstantTreeNode(0, currentWord, index);
        }
        return currentNode;
    }

    /**
     * Checks to see if the input text matches the regex
     */
    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    /**
     * Changes the current language of the program
     */
    public void changeLanguage(String language) {
        this.commandDictionary.changeLanguage(language);
    }
}
