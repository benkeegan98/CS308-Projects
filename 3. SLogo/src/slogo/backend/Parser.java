package slogo.backend;

import slogo.backend.node.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {

    private Controller myController;

    private List<String> inputLines = new ArrayList<>();
    private Pattern commentPattern = Pattern.compile("^#.*");
    private TreeBuilder treeBuilder;

    public Parser(Controller controller) {
        this.myController = controller;
        treeBuilder = new TreeBuilder(this.myController);
    }

    /**
     * Parses the input from the user. Relies on the input format specified by the SLOGO language.
     * Each command, constant, variable, or list must be separated by a space for the parser
     * to recognize it as input. Each comment should be on its own line.
     *
     * @param userInput String of user input
     */
    public void parseInput(String userInput) {
        inputLines = new ArrayList<>();
        inputLines.addAll(Arrays.asList(userInput.split("\n+")));
        removeComments();
        String concat = String.join(" ",inputLines);
        parseLine(concat);
//        for (String line : inputLines) {
//            parseLine(line); //parse and do action
//        }

    }

    /** Changes the parsing language
     *
     * @param language String of new language
     */
    public void changeLanguage(String language) {
        this.treeBuilder.changeLanguage(language);
    }

    /**
     * Parses a line by passing the line into the treeBuilder and operating on that line
     */
    private int parseLine(String line) {
        List<String> wordList = Arrays.asList(line.split("\\s+"));
        List<TreeNode> listOfNode = this.treeBuilder.createTree(wordList);
        //System.out.println("number of nodes: " + listOfNode.size());

        doActionForId(-1, listOfNode);
        //System.out.println("Current active list: " + myController.getActiveIdList());
        for (int i : myController.getTurtles().keySet()) {
            //System.out.println("\n\n\ncurrent turle: " + i);
            //System.out.println("actives: "+ this.myController.getActiveIdList());
            if (this.myController.getAskedIdList().size()!=0) {
                //System.out.println("targets: " + myController.getTargetIdList());
                if (this.myController.getAskedIdList().contains(i)) {
                    doActionForId(i, listOfNode);
                    System.out.println("current turtle did action in target: " + i);
                }
            }
            else if (this.myController.getToldIdList().contains(i)) {
                doActionForId(i, listOfNode);
            }
        }
        //doActionForId(-1, listOfNode);
        this.myController.getAskedIdList().clear();
        return 0;
    }

    /**
     * Performs the action specified by the Tree inputs to the turtle indicated.
     */
    private void doActionForId(int i, List<TreeNode> listOfNode) {
        this.myController.setActiveId(i);
        for (TreeNode node : listOfNode) {
            node.doAction();
            System.out.println("active turtle " + this.myController.getActiveId());
            System.out.println("root: " + node.getWord());
            System.out.println("root return value: " + node.getReturnValue());
            System.out.println("child size: " + node.getChildren().size());
        }
    }

    /**
     * Removes any lines of comments in the lines of input
     */
    private void removeComments() {
        List<String> newInputLines = new ArrayList<>();
        for (String line : inputLines) {
            if (!match(line, commentPattern)) {
                while (line.startsWith(" ")) line = line.substring(1);
                newInputLines.add(line);
            }
        }
        inputLines = newInputLines;
    }

    /**
     * Matches the regular expression to the input text. Returns true if regex matches.
     */
    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
}
