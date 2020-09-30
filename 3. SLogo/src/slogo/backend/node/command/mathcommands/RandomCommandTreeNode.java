package slogo.backend.node.command.mathcommands;

import slogo.backend.Controller;

import java.util.Random;

public class RandomCommandTreeNode extends OneInputMathCommandTreeNode {
    private Random r = new Random();

    public RandomCommandTreeNode(Controller controller, String commandType, String word, Integer index) {
        super(controller, commandType, word, index);
    }

    @Override
    void calculateAndSetReturnValue(double input) {
        this.setReturnValue(r.nextDouble() * input);
    }


}
