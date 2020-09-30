package Main.Simulations;

import Main.Components.Cell.Cell;
import Main.Main;

public class Percolation extends Simulation {

    private static final String IDENTIFIER = Main.simTypes.getString("percolation");
    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int PERCOLATED = 2;

    public Percolation(int xCells, int yCells) {
        super(xCells, yCells, IDENTIFIER);
    }

    /**
     *
     * @param cell - takes in cell and sets its next state
     */
    @Override
    public void updateNextState(Cell cell) {
        cell.setNextState(cell.getState());
        if (cell.getState() == OPEN) {
            for (Cell neighbor : cell.getNeighbors()) {
                if (neighbor.getState() == PERCOLATED) {
                    cell.setNextState(PERCOLATED);
                }
            }
        }
    }
}
