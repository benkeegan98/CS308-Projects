package Main.Components.Cell;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class PredatorPreyCell extends Cell {

    private static final int KELP = 0;
    private static final int FISH = 1;
    private static final int SHARK = 2;

    public PredatorPreyCell(int xPos, int yPos) {
        super(xPos, yPos);
    }

    /**
     * sets initial cell state - would read in XML initial layout here if we had this working
     * here it sets initial layout based on chosen proportions
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
            case KELP -> myColor = Color.BLUE;
            case FISH -> myColor = Color.GREEN;
            case SHARK -> myColor = Color.YELLOW;
        }
        return myColor;
    }

    @Override
    public Polygon getShape() {
        return null;
    }
}
