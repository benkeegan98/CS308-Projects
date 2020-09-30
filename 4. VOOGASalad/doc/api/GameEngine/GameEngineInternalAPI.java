package GameEngine;

public interface GameEngineInternalAPI {
    public void chunkSpriteHealth();

    public boolean spriteIsDead();

    public void checkForDeadSprites();

    public void handleDeath();

    public void checkCollision();
}
