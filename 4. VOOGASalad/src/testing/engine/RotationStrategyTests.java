package testing.engine;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.RotationBuilder;
import voogasalad.gameengine.executors.sprites.strategies.rotation.NoRotation;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RotationStrategyTests {

    private RotationBuilder builder = new RotationBuilder();
    RotationStrategy FullRotation;
    RotationStrategy LimitedRotation;
    RotationStrategy NoRotation;

    @Test
    public void testBuilderParsingSpeed() throws GameEngineException {
        builder.setSpeed("40");
        NoRotation = new NoRotation(builder);
        assertEquals(builder.getSpeed(), 40);
    }

    @Test
    public void testBuilderParsingRangePair() throws GameEngineException{
        builder.setValidRotationRange("40,70");
        assertEquals(builder.getRotationRange(), new Pair(40.0,70.0));
    }

    @Test
    public void testBuilderParseType() throws GameEngineException{
        builder.setType("NoRotation");
        assertEquals(builder.getType(), "NoRotation");
    }
}
