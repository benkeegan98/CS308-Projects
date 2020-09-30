package Main.Components.Cell;

import javafx.scene.paint.Color;

public class PercolationCell extends Cell {

    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int PERCOLATED = 2;

    public PercolationCell(int xPos, int yPos) {
        super(xPos, yPos);
        setState();
    }

    /**
     * sets initial cell state - would read in XML initial layout here if we had this working
     * here it sets initial layout based on chosen proportions
     */
    @Override
    public void setState() {
        double rand = Math.random();
        if (rand < 0.3) myState = BLOCKED;
        else if (rand > 0.95) myState = PERCOLATED;
        else myState = OPEN;
    }

    /**
     * sets color associated with each state
     * @return - Color value of this cell
     */
    @Override
    public Color getColor() {
        switch (myState) {
            case BLOCKED -> myColor = Color.BLACK;
            case OPEN -> myColor = Color.WHITE;
            case PERCOLATED -> myColor = Color.DODGERBLUE;
        }
        return myColor;
    }

    @Override
    public Polygon getShape() {
        return null;
    }
}
