package Main.Simulations;

import Main.Components.Cell.Cell;
import Main.Main;

import java.util.List;

public class Life extends Simulation {

    private static final String IDENTIFIER = Main.simTypes.getString("life");
    private static final int DEAD = 0;
    private static final int ALIVE = 1;

    public Life(int xCells, int yCells) {
        super(xCells, yCells, IDENTIFIER);
    }

    /**
     *
     * @param cell - takes in cell and sets its next state
     */
    @Override
    public void updateNextState(Cell cell) {
        cell.setNextState(DEAD);
        if (numAliveNeighbors(cell) == 3 || (numAliveNeighbors(cell) == 2 && cell.getState() == ALIVE)) {
            cell.setNextState(ALIVE);
        }
    }

    /**
     *
     * @param cell - takes in cell to check its neighbors
     * @return int number of alive neighbors
     */
    private int numAliveNeighbors(Cell cell) {
        List<Cell> neighbors = cell.getNeighbors();

        int aliveNeighbors = 0;
        for (Cell neighborCell : neighbors) {
            aliveNeighbors += neighborCell.getState();
        }
        return aliveNeighbors;
    }


}
