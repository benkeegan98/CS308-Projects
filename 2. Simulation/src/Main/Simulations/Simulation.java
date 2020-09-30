package Main.Simulations;

import Main.Components.Cell.Cell;
import Main.Grid;

public abstract class Simulation {

    protected Grid theGrid;

    public Simulation(int xNumCells, int yNumCells, String simulationType) {
        theGrid = new Grid(xNumCells, yNumCells, simulationType);
    }

    /**
     *
     * @return Grid object that tracks the cells in the simulation
     */
    public Grid getGrid() { return theGrid; }

    /**
     *
     * @param cell - abstract method, takes in cell and sets its next state
     */
    public abstract void updateNextState(Cell cell);
}
