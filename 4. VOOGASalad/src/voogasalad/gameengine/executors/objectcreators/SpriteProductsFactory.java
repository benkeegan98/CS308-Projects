package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.SpriteManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class SpriteProductsFactory {
    public static final String SPRITE_FRONTEND_RESOURCE_PATH = "resources.engine.SpriteFrontendSelection";
    private final static String CLASS_PATH = "voogasalad.gameengine.executors.sprites.";
    private final ResourceBundle SpriteFrontendSelection = ResourceBundle.getBundle(SPRITE_FRONTEND_RESOURCE_PATH);

    public SpriteManager makeSpriteManager(LevelActionsRequester levelActionsRequester) throws GameEngineException {
        String spriteManagerClassSelection = SpriteFrontendSelection.getString("SpriteManager");
        try {
            return (SpriteManager) Class.forName(CLASS_PATH + spriteManagerClassSelection).getConstructor(LevelActionsRequester.class).newInstance(levelActionsRequester);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteManagerProductionFailed");
        }
    }

    public Sprite makeSprite(SpriteBuilder builder) throws GameEngineException {
        String spriteClassSelection = SpriteFrontendSelection.getString("Sprite");
        try {
            return (Sprite) Class.forName(CLASS_PATH + spriteClassSelection)
                    .getConstructor(SpriteBuilder.class)
                    .newInstance(builder);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteProductionFailed");
        }
    }
}
