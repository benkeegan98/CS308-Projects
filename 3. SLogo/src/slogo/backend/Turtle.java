package slogo.backend;

/**
 * Backend representation of the turtle, managers the location and turtle's pen.
 */
public class Turtle {
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    private double oldDirection;
    private double direction;
    private boolean show = true;
    private boolean penDown = true;

    /**
     * @param x initial x location
     * @param y initial y location
     */
    public Turtle(int x, int y) {
        this.x = x;
        this.y = y;
        this.oldDirection = 0;
        this.oldY = 0;
        this.oldX = 0;
    }

    /**
     * @return direction of the turtle, facing up is 0 degrees, and goes clockwise
     */
    public double getDirection() {
        return direction;
    }

    /**
     * @param newDirection new direction of turtle
     */
    public void setDirection(double newDirection) {
        this.oldDirection = direction;
        this.direction = newDirection;
    }

    public int getX() {
        return x;
    }

    /**
     * @param newX new x location of turtle
     * @param newY new y location of turtle
     */
    public void setLocation(double newX, double newY) {
        System.out.println("New X and Y: " + newX + " " + newY);
        this.oldX = x;
        this.oldY = y;
        this.x = (int) Math.round(newX);
        this.y = (int) Math.round(newY);
    }

    public int getY() {
        return y;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void revertStep() {
        this.x = this.oldX;
        this.y = this.oldY;
        this.direction = this.oldDirection;
    }

}
