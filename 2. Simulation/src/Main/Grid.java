package Main;

import Main.Components.Cell.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grid {

    protected Cell[][] myGrid;
    protected int gridSizeX;
    protected int gridSizeY;
    private Queue<Cell> emptyCellQueue = new LinkedList<>();

    public Grid(int xCells, int yCells, String gridType) {
        myGrid = new Cell[xCells][yCells];
        gridSizeX = xCells;
        gridSizeY = yCells;
        populateGrid(xCells, yCells, gridType); // this populates myGrid with correct cell, and sets each cells neighbors list myNeighbors
    }

    /**
     *
     * @return 2D array of Cells tracked by the Grid object - these are the active simulation cells
     */
    public Cell[][] getGridArray() {
        return myGrid;
    }

    /**
     *
     * @return emptyCellQueue which tracks empty cells - used in Segregation
     */
    public Queue<Cell> getEmptyCellQueue() {
        return emptyCellQueue;
    }

    /**
     *
     * @param cell - takes in a cell to add it to the emptyCellQueue that exists in Grid
     */
    public void addEmptyCell(Cell cell) {
        emptyCellQueue.add(cell);
    }

    /**
     * populates grid with correct cells based on gridType parameter
     * sets the neighborhoods for each cell in the grid
     * adds any empty cells to the emptyCellQueue
     *
     * @param xCells - number of cells in grid horizontally
     * @param yCells - number of cells in grid vertically
     * @param gridType - String identifier for active grid/simulation
     *
     */
    private void populateGrid(int xCells, int yCells, String gridType) {
        if (myGrid != null) {
            for (int row = 0; row < xCells; row++) {
                for (int col = 0; col < yCells; col++) {
                    myGrid[row][col] = insertCell(gridType, row, col);
                }
            }
        }
        for (int row = 0; row < xCells; row++) {
            for (int col = 0; col < yCells; col++) {
                assert myGrid != null;
                assert myGrid[row][col] != null;
                setCellNeighbors(myGrid[row][col]);
                if (myGrid[row][col].getState() == 0) emptyCellQueue.add(myGrid[row][col]);
            }
        }
    }

    /**
     *
     * @param cellType - String cellType identifies which type of cell to insert
     * @param xPos - x position in grid
     * @param yPos - y position in grid
     * @return - returns a new Cell of type to match simulation type
     */
    private Cell insertCell(String cellType, int xPos, int yPos) {
        if (cellType.equals(Main.simTypes.getString("life"))) return new LifeCell(xPos, yPos);
        else if (cellType.equals(Main.simTypes.getString("fire"))) return new FireCell(xPos, yPos);
        else if (cellType.equals(Main.simTypes.getString("percolation"))) return new PercolationCell(xPos, yPos);
        else if (cellType.equals(Main.simTypes.getString("segregation"))) return new SegregationCell(xPos, yPos);
        else return null;
    }

    /**
     *
     * @param cell - takes in cell to calculate neighbors
     *             sets Neighbors List for this cell
     */
    private void setCellNeighbors(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();

        List<Cell> neighborsList = new ArrayList<>();

        for(int neighborX = x-1; neighborX <= x+1; neighborX++) {
            for(int neighborY = y-1; neighborY <= y+1; neighborY++) {
                if(cellExists(neighborX, neighborY) && !(neighborX == x && neighborY == y)) neighborsList.add(myGrid[neighborX][neighborY]); // iterate through indexes of surrounding cells, and if they are in grid, add them as a cell neighbor
            }
        }
        cell.setMyNeighbors(neighborsList); // set the cell's myNeighbors instance variable
    }

    /**
     *
     * @param x - x position in grid
     * @param y - y position in grid
     * @return - true or false value of whether cell exists in grid
     */
    private boolean cellExists(int x, int y) {
        return x >= 0 && y >= 0 && x < gridSizeX && y < gridSizeY;
    }
}
