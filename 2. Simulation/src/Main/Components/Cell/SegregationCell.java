package Main.Components.Cell;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class SegregationCell extends Cell {

    private static final int EMPTY = 0;
    private static final int GROUP1 = 1;
    private static final int GROUP2 = 2;

    public SegregationCell(int xPos, int yPos) {
        super(xPos, yPos);
        setState();
    }

    /**
     * sets initial cell state - would read in XML initial layout here if we had this working
     * here it sets initial layout randomly
     */
    @Override
    public void setState() {
        myState = new Random().nextInt(3);
    }

    /**
     * sets color associated with each state
     * @return - Color value of this cell
     */
    @Override
    public Color getColor() {
        switch (myState) {
            case EMPTY -> myColor = Color.WHITE;
            case GROUP1 -> myColor = Color.RED;
            case GROUP2 -> myColor = Color.BLUE;
        }
        return myColor;
    }

    @Override
    public Polygon getShape() {
        return null;
    }
}
