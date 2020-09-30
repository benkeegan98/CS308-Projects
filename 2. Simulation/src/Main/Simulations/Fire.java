package Main.Simulations;

import Main.Components.Cell.Cell;
import Main.Main;

public class Fire extends Simulation {

    private static final String IDENTIFIER = Main.simTypes.getString("fire");
    private static final int EMPTY = 0;
    private static final int TREE = 1;
    private static final int BURNING = 2;

    private double probCatch = 0.5;

    public Fire(int xCells, int yCells) {
        super(xCells, yCells, IDENTIFIER);
    }

    /**
     *
     * @param cell - takes in cell and sets its next state
     */
    @Override
    public void updateNextState(Cell cell) {

        cell.setNextState(TREE);
        if (cell.getState() == TREE && Math.random() < probCatch) {

            for (Cell neighbor : cell.getNeighbors()) {
                if ((neighbor.getX() == cell.getX() || neighbor.getY() == cell.getY()) && neighbor.getState() == BURNING) {
                    cell.setNextState(BURNING);
                    break;
                }
            }
        } else if (cell.getState() == BURNING || cell.getState() == EMPTY) {
            cell.setNextState(EMPTY);
        }
    }
}
