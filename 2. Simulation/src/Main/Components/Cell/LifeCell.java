package Main.Components.Cell;

import javafx.scene.paint.Color;

import java.util.Random;

public class LifeCell extends Cell {

    private static final int DEAD = 0;
    private static final int ALIVE = 1;

    public LifeCell(int xPos, int yPos) {
        super(xPos, yPos);
        setState();
    }

    /**
     * sets initial cell state - would read in XML initial layout here if we had this working
     * here it randomizes initial layout
     */
    @Override
    public void setState() {
        myState = DEAD;
        if(Math.random() > 0.5) myState = ALIVE;
    }

    /**
     * sets color associated with each state
     * @return - Color value of this cell
     */
    @Override
    public Color getColor() {
        switch (myState) {
            case DEAD:
                myColor = Color.WHITE;
                break;
            case ALIVE:
                myColor = Color.BLACK;
                break;
        }
        return myColor;
    }

    @Override
    public Polygon getShape() {
        return null;
    }

}
