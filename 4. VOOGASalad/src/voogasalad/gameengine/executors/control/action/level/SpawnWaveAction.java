package voogasalad.gameengine.executors.control.action.level;

import voogasalad.gameengine.executors.control.levelcontrol.managers.LevelWaveManager;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.Wave;
import voogasalad.gameengine.executors.sprites.SpriteManager;

public class SpawnWaveAction implements LevelAction {

    private Wave myWave;
    private boolean isFinished;

    /**
     * Purpose:
     * Assumptions:
     * @param level
     * @throws GameEngineException
     */
    @Override
    public void execute(Level level) throws GameEngineException {
        checkAndResetAction();
        SpriteManager spriteManager = level.getSpriteManager();
        LevelWaveManager levelWaveManager = level.getWaveManager();
        double elapsedTime = level.getStatusManager().getElapsedTimeSinceLastFrame();
        if (myWave == null) {
            setupWave(levelWaveManager);
        }
        if (! isFinished) {
            isFinished = myWave.spawnNextSprite(spriteManager, elapsedTime);
        }
    }

    private void checkAndResetAction() {
        if (isFinished==true) {
            myWave = null;
            isFinished = false;
        }
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return isFinished;
    }

    private void setupWave(LevelWaveManager levelWaveManager) {
        if (levelWaveManager.hasNextWave()) {
            isFinished = false;
            myWave = levelWaveManager.getNextWave();
        } else {
            isFinished = true;
        }
    }
}
