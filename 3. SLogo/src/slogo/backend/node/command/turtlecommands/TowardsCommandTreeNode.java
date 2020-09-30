package slogo.backend.node.command.turtlecommands;

import slogo.backend.Controller;

public class TowardsCommandTreeNode extends TurtleCommandTreeNode {
    public TowardsCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
        this.setInputSize(2);

    }

    @Override
    double doTurtleCommand() {
        double x = getInput(0) - this.getMyController().getTurtleX();
        double y = getInput(1) - this.getMyController().getTurtleY();
        double newDirection = Math.toDegrees(Math.atan(y / x));
        if (x < 0) newDirection += 180;
//        System.out.println(newDirection);
//        if (x<0 && y<0) newDirection += 180;
//        if (x==0 && y<0) newDirection = 270;
//        if (x==0 && y>0) newDirection = 90;
//        if (x<0 && y>0) newDirection = 180 - newDirection;
//        if (x>0 && y<0) newDirection = 360 - newDirection;
//        if (x<0 && y==0) newDirection = 180;
//        if (x>0 && y==0) newDirection = 0;
//        System.out.println(x + " " +y);
//        System.out.println(newDirection);

        System.out.println(newDirection);
        newDirection -= 90;
        newDirection *= -1;
        this.getMyController().setTurtleDirection(newDirection);
        return newDirection;
    }
}
