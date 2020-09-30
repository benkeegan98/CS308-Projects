package testing.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.HealthBuilder;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;



public class HealthStrategyTests {

    @Test
    public void testChunkHealth() throws GameEngineException {
        int expected = 3;
        HealthStrategy healthStrategy = new HealthBuilder().setType("Health").setHealthValue("5").build();
        healthStrategy.chunkHealth(2);
        Assertions.assertEquals(expected, healthStrategy.getHealth());
    }
}
