package Main.Components.Cell;

import javafx.scene.paint.Color;

import java.util.List;


/**
 * @author Ben Keegan, Samantha Whitt
 * Cell class is an abstract class for setting up the cell and its neighbors with their states
 * Each subclass is a specific simulation cell holding specific game rules like its color and state types
 * example: create a new simulation that uses specific cell types/rules
 */
public abstract class Cell {
    protected int myState;
    protected int nextState;
    protected Color myColor;
    protected List<Cell> myNeighbors;
    protected int xIndex;
    protected int yIndex;

    public Cell(int xPos, int yPos) {
        myState = 0; // change this to be based on proportions
        xIndex = xPos;
        yIndex = yPos;
    }

    /**
     * abstract setter method for initializing current state - different in each game
     */
    public abstract void setState();

    /**
     * @return current myState of Cell
     */
    public int getState() {
        return myState;
    }

    /**
     * @return current nextState of Cell
     */
    public int getNextState() { return nextState; }

    /**
     * @param newState - new state that Cell will change to next step
     */
    public void setNextState(int newState) {
        nextState = newState;
    }

    /**
     * simulation works by setting the next state of each cell, then setting myState to be nextState
     * this method executes this process for an individual cell
     */
    public void goToNextState() {
        myState = nextState;
    }

    /**
     * @return x position of Cell in grid
     */
    public int getX() {
        return xIndex;
    }

    /**
     * @return y position of Cell in grid
     */
    public int getY() {
        return yIndex;
    }

    /**
     * @return List of neighbors for current cell
     */
    public List<Cell> getNeighbors() { return myNeighbors; };

    /**
     * sets cell neighbors list to be method argument
     * @param neighbors - new neighbors list to be set for cell
     */
    public void setMyNeighbors(List<Cell> neighbors) {
        myNeighbors = neighbors;
    }

    public abstract Color getColor();

    public abstract Polygon getShape();

}
