package slogo.backend;

import javafx.scene.paint.Color;
import slogo.view.ErrorPane;
import slogo.view.TurtleTurnup;

import java.util.*;


public class Controller {
    private final ResourceBundle defaultColors = ResourceBundle.getBundle("resources.defaultcolors/Colors");

    private Parser parser;
    private Map<Integer, Turtle> turtles = new TreeMap<>();
    private int activeId = 0;
    private List<Integer> toldIdList = new ArrayList<>();
    private List<Integer> askedIdList = new ArrayList<>();
    private TurtleTurnup myTurnup;
    private Variables myVariables;
    private ErrorPane myErrorPane;
    private Map<String, UserCommand> myUserCommands;
    //TODO: (Frontend) When a color is chosen on the wheel for pen color, add that color to a new index in myColors
    // and update currentColor
    private Map<Integer, Color> myColors;
    private Integer currentColor;

    public Controller(TurtleTurnup turtle, ErrorPane errorPane) {
        this.parser = new Parser(this);
        this.turtles.put(-1, new Turtle(0, 0)); // fake turtle
        this.turtles.put(0, new Turtle(0, 0));
        this.myTurnup = turtle;
        this.myVariables = new Variables();
        this.myUserCommands = new HashMap<>();
        this.myErrorPane = errorPane;
        this.myColors = getDefaultColors();
        this.currentColor = 1;
        toldIdList.add(0);
    }

    /// external

    /**
     * parse and do the according action of the user input
     *
     * @param input user input
     */
    public void parseAndPerform(String input) {
        this.parser.parseInput(input);
    }

    /**
     * change the language that the commands are using
     *
     * @param lang new language to change to
     */
    public void changeLanguage(String lang) {
        this.parser.changeLanguage(lang);
    }

    //// internal

    /**
     * get the map of user defined commands
     *
     * @return Map from command name to list of user commands
     */
    public Map getCommands() {
        return myUserCommands;
    }

    /**
     * Set the error msg for display
     *
     * @param err error msg
     */
    public void setErrorText(String err) {
        myErrorPane.errorMessage(err);

    }

    /**
     * @return the id of the current active turtle
     */
    public int getActiveId() {
        return activeId;
    }

    /**
     * set the id of the current active turtle
     * @param activeId the id of the current active turtle to set to
     */
    public void setActiveId(int activeId) {
        this.activeId = activeId;
        myTurnup.setActiveTurtle(activeId);
    }

    public List<Integer> getToldIdList() {
        return toldIdList;
    }

    /** Sets the activeIDList to be the input. This list will override the targetIdList
     * for a specified command. Used for the ASK command.
     *
     * @param toldIdList
     */
    public void setToldIdList(List<Integer> toldIdList) {
        this.toldIdList = toldIdList;
    }

    /** gets the list of turtles that have been created
     *
     * @return
     */
    public Map<Integer, Turtle> getTurtles() {
        return turtles;
    }

    /** Gets the list of turtles that should execute input commands
     *
     * @return List of integers that correspond to turtle IDs
     */
    public List<Integer> getAskedIdList() {
        return askedIdList;
    }

    /** Sets the targetIDList of the controller. This list specifies the turtles that
     * should execute input commands
     *
     * @param askedIdList
     */
    public void setAskedIdList(List<Integer> askedIdList) {
        this.askedIdList = askedIdList;
    }

    /**
     * Sets a new color to map to a specified index. If the input array does not contain three integers,
     * the color is not set.
     *
     * @param myIndex color index
     * @param myRGB rgb value of a specified index
     */
    public void setColor(Integer myIndex, int[] myRGB) {
        if (myRGB.length == 3){
            this.myColors.put(myIndex, Color.rgb(myRGB[0], myRGB[1], myRGB[2]));
        }
        else{
            setErrorText("Color not set. Please indicate valid RGB value!");
        }
    }

    /** Gets a color from an index
     *
     * @param myIndex index of the color to obtain RGB value from
     * @return color that maps to the specified index
     */
    public Color getColor(Integer myIndex){
        if (myColors.containsKey(myIndex)) {
            return myColors.get(myIndex);
        } else {
            setErrorText("Color Index Not Previously Set!");
            return Color.rgb(0, 0, 0);
        }
    }

    public int getCurrentColor() {
        return this.currentColor;
    }

    /**
     * @param myIndex index color specified by command
     */
    public void setCurrentColor(int myIndex){
        this.currentColor = myIndex;
    }

    /**
     * Returns a map of the default colors specified in the defaultColors resource file
     */
    private Map<Integer, Color> getDefaultColors() {
        Map<Integer, Color> defaultColorMap = new HashMap<>();
        for (Iterator<String> myColors = defaultColors.getKeys().asIterator(); myColors.hasNext(); ) {
            String myKey = myColors.next();
            Color myColor = Color.valueOf(defaultColors.getString(myKey));
            defaultColorMap.put(Integer.parseInt(myKey), myColor);
        }
        return defaultColorMap;
    }

    public int getTurtleX() {
        return turtles.get(activeId).getX();
    }

    public int getTurtleY() {
        return turtles.get(activeId).getY();
    }

    public void setTurtleLocation(double newX, double newY) {
        turtles.get(activeId).setLocation(newX, newY);
    }

    public double getTurtleDirection() {
        return turtles.get(activeId).getDirection();
    }

    public void setTurtleDirection(double newDirection) {
        turtles.get(activeId).setDirection(newDirection);
    }

    public void revertTurtleStep() {
        this.turtles.get(activeId).revertStep();
    }

    public boolean isPenDown() {
        return turtles.get(activeId).isPenDown();
    }

    public void setPen(boolean penDown) {
        turtles.get(activeId).setPenDown(penDown);
    }

    public boolean isTurtleShow() {
        return turtles.get(activeId).isShow();
    }

    public TurtleTurnup getTurtleTurnup() {
        return this.myTurnup;
    }

    public boolean updateTurtleTurnup(double newX, double newY, boolean pen, double direction){
        if(activeId != -1){
            return myTurnup.moveTurtle(newX, newY, pen, direction);
        }
        return false;
    }

    public void setVariable(String variableName, double variableVal) {
        if(activeId != -1){
            myVariables.addVariable(variableName, variableVal);
        }
    }

    public double getVariable(String variableName) {
        if (!myVariables.variableExists(variableName)) {
            //setErrorText("VARIABLE " + variableName + " DOES NOT EXIST");
            return 0.0;
        }
        return myVariables.getVariableValue(variableName);
    }

    public Map getVariables() {
        return myVariables.getVariables();
    }

    public boolean commandDefined(String commandName) {
        return myUserCommands.containsKey(commandName);
    }

    public void addCommand(String commandName, UserCommand command) {
        myUserCommands.put(commandName, command);
    }

    public UserCommand getCommand(String commandName) {
        if (myUserCommands.containsKey(commandName)) {
            return myUserCommands.get(commandName);
        } else {
            //TODO: Command not specified; throw error to front end
            setErrorText("NO USER DEFINED COMMAND FOUND");
            System.out.println("No user defined command found!!");
            return null;
        }
    }

}
