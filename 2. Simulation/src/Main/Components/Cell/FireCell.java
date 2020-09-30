package Main.Components.Cell;

import javafx.scene.paint.Color;

import java.util.List;
import java.util.Queue;

public class FireCell extends Cell {

    private static final int EMPTY = 0;
    private static final int TREE = 1;
    private static final int BURNING = 2;

    public FireCell(int xPos, int yPos) {
        super(xPos, yPos);
        setState();
    }

    /**
     * sets initial cell state - would read in XML initial layout here if we had this working
     * here it randomizes initial layout
     */
    @Override
    public void setState() {
        myState = 1;
        if (Math.random() > 0.9) myState = 2;
    }

    /**
     * sets color associated with each state
     * @return - Color value of this cell
     */
    @Override
    public Color getColor() {
        switch (myState) {
            case EMPTY -> myColor = Color.YELLOW;
            case TREE -> myColor = Color.GREEN;
            case BURNING -> myColor = Color.RED;
        }
        return myColor;
    }

    @Override
    public Polygon getShape() {
        return null;
    }

}
