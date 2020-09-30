package Main.Simulations;

import Main.Components.Cell.Cell;
import Main.Main;

import java.util.ArrayList;
import java.util.List;

public class PredatorPrey extends Simulation {

    private static final String IDENTIFIER = Main.simTypes.getString("percolation");
    private static final int KELP = 0;
    private static final int FISH = 1;
    private static final int SHARK = 2;

    private int turnsSurvived;

    public PredatorPrey(int xNumCells, int yNumCells) {
        super(xNumCells, yNumCells, IDENTIFIER);
    }

    /**
     *
     * @param cell - takes in cell and sets its next state
     */
    @Override
    public void updateNextState(Cell cell) {
        // RULES
        // FISH eat KELP, KELP never run out, FISH move over to a random adjacent cell each turn
        // after x amount of turns survived, a FISH will produce another FISH in an adjacent cell
        // SHARKs eat FISH. If FISH has SHARK neighbor, the SHARK eats it.
        // if SHARK has multiple adjacent FISH, it randomly eats one
        // if no adjacent FISH, SHARK moves onto random adjacent cell
        // after x amount of turns survived, a SHARK will produce another SHARK in an adjacent cell
        if (cell.getState() == FISH) {
            updateFishState(cell);
        } else if (cell.getState() == SHARK) {
            updateSharkState(cell);
        }
    }

    private void updateSharkState(Cell cell) {

    }

    private void updateFishState(Cell cell) {
        List<Cell> availableCells = getOpenCells(cell, KELP, FISH);

    }

    private List<Cell> getOpenCells(Cell cell, int openCell, int blockedCell) {
        List<Cell> neighbors = cell.getNeighbors();
        List<Cell> openCells = new ArrayList<>();

        for (Cell neighbor : neighbors) {
            if ((neighbor.getX() == cell.getX() || neighbor.getY() == cell.getY()) && neighbor.getState() == openCell && neighbor.getNextState() != blockedCell) {
                openCells.add(neighbor);
            }
        }
        return openCells;
    }
}
