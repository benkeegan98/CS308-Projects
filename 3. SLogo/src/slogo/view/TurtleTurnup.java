package slogo.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleTurnup extends Pane {

    private static final int SCREEN_HEIGHT = 500;
    private static final int SCREEN_WIDTH = 700;
    private static final int TURTLE_SIZE = 40;
    public static final int CENTER_X = SCREEN_WIDTH / 2 - TURTLE_SIZE / 2;
    public static final int CENTER_Y = SCREEN_HEIGHT / 2 - TURTLE_SIZE / 2;

    private Map<Integer, TurtleView> myTurtles;
    private int myActiveTurtle;
    private Color penColor = Color.BLACK;
    private List<Line> lineList;
    private double myPenWidth = 1;

    TurtleTurnup(){
        myTurtles = new HashMap<>();
        this.setPrefHeight(SCREEN_HEIGHT);
        this.setPrefWidth(SCREEN_WIDTH);
        setBackgroundFill(Color.WHITE);
        createTurtle(0);
        lineList = new ArrayList<>();
    }

    public void setBackgroundFill(Color color) {
        BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(fill);
        setBackground(background);
    }

    void changeTurtleImage(String name){
        myTurtles.get(myActiveTurtle).changeTurtleImage(name);
    }

    public boolean moveTurtle(double newX, double newY, boolean pen, double direction){
        double oldX = myTurtles.get(myActiveTurtle).getX() + (TURTLE_SIZE / 2);
        double oldY = myTurtles.get(myActiveTurtle).getY() + (TURTLE_SIZE / 2);
        if((newX+CENTER_X > 0 && newX+CENTER_X < SCREEN_WIDTH) && (CENTER_Y-newY > 0 && CENTER_Y-newY < SCREEN_HEIGHT)) {
            myTurtles.get(myActiveTurtle).moveTurtle(newX, newY, pen, direction);
            if(pen) {
                Line newLine = new Line(oldX, oldY, newX + (SCREEN_WIDTH/2), (SCREEN_HEIGHT/2) - newY);
                newLine.setStroke(penColor);
                newLine.setStrokeWidth(myPenWidth);
                getChildren().add(newLine);
                lineList.add(newLine);
            }
            return true;
        }
        return false;
    }

    public void hideTurtle(boolean invisible) {
        myTurtles.get(myActiveTurtle).setVisible(!invisible);
    }

    public void setPenColor(Color color) {
        penColor = color;
    }

    public void clearLines() {
        for (Line line : lineList) {
            getChildren().remove(line);
            System.out.println(line);
        }
        lineList.clear();
    }

    public void setPenWidth(double penWidth){
        myPenWidth = penWidth;
    }

    private void createTurtle(int index){
        TurtleView turtle = new TurtleView();
        myTurtles.put(index, turtle);
        turtle.setX(CENTER_X);
        turtle.setY(CENTER_Y);
        turtle.setFitHeight(TURTLE_SIZE);
        turtle.setFitWidth(TURTLE_SIZE);
        turtle.setPreserveRatio(true);
        getChildren().add(turtle);
    }

    public void setActiveTurtle(int activeUpdate) {
        if(activeUpdate != -1){
            if(!myTurtles.containsKey(activeUpdate)){
                createTurtle(activeUpdate);
            }
            this.myActiveTurtle = activeUpdate;
        }
    }

    /**
     * This method checks or unchecks the pen checkbox in the settings pane.
     * @param bool: true if pen down, false if pen up
     */
    public void updatePenDownSwitch(boolean bool) {
        SettingsPane.updatePenDownSwitch(bool);
    }
}
