package Main.Simulations;

import Main.Components.Cell.Cell;
import Main.Main;
import Main.Grid;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Segregation extends Simulation {

    private static final String IDENTIFIER = Main.simTypes.getString("segregation");
    private static final int EMPTY = 0;
    private static final int GROUP1 = 1;
    private static final int GROUP2 = 2;

    private double satisfactionLevel = 0.6;

    public Segregation(int xNumCells, int yNumCells) {
        super(xNumCells, yNumCells, IDENTIFIER);
    }

    /**
     *
     * @param cell - takes in cell and sets its next state
     */
    @Override
    public void updateNextState(Cell cell) {
        int totalNeighbors = 0;
        int similarNeighbors = 0;

        if (cell.getState() == EMPTY) return;

        for (Cell neighbor : cell.getNeighbors()) {
            if (neighbor.getState() == cell.getState()) {
                similarNeighbors++;
                totalNeighbors++;
            } else if (neighbor.getState() != EMPTY) {
                totalNeighbors++;
            }
        }

        if (totalNeighbors == 0) return;
        if ((double) similarNeighbors/totalNeighbors < satisfactionLevel) moveCellAway(cell);
    }

    /**
     * handles the migration of a cell
     * assigns nextState of Cell at front of emptyCellQueue to be the state of the current cell
     * changes nextState of current cell to be EMPTY
     * @param cell
     */
    private void moveCellAway(Cell cell) {
        if(theGrid.getEmptyCellQueue() != null) {
            Objects.requireNonNull(theGrid.getEmptyCellQueue().poll()).setNextState(cell.getState());
            cell.setNextState(EMPTY);
            theGrid.addEmptyCell(cell);
        }
    }
}
