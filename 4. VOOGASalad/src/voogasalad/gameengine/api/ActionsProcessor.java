package voogasalad.gameengine.api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import voogasalad.gameengine.executors.control.action.game.GameAction;
import voogasalad.gameengine.executors.control.action.level.SellTowerAction;
import voogasalad.gameengine.executors.control.gamecontrol.GameActionsRequester;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.control.action.level.AddSpriteAction;
import voogasalad.gameengine.executors.control.action.level.LevelAction;
import voogasalad.gameengine.executors.control.action.level.RemoveSpriteAction;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * Class: Actions Processor
 * Purpose: Provide a united API for non-engine parts/modules access into requesting actions from the engine.
 * Assumptions: Assumes the existence of the properties files and appropriate routes listed; otherwise an error will be thrown.
 * Dependencies: Must have a LevelActionsRequester to request actions on the current level and GameActionsRequester to request actions on the game.
 * Example of how to use: Player can request an add sprite action at the creation of a tower according to UI interaction.
 * Other details: This class processes both UI actions and Live Game Editing requests. Currently, the engine is built only
 * around one game, so there is only 1 ActionsProcessor; however, in any future case in which we want the engine to handle
 * multiple games at once, when refactoring the engine to run the appropriate game, we can assign different ActionsProcessor
 * to different indexed games inside the engine.
 */
public class ActionsProcessor {
    public static final String GAME_ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.game.";
    public static final String LEVEL_ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.level.";
    public static final String ACTIONS_DIRECTORY_ROOT = "voogasalad.gameengine.executors.control.action.";
    public static final String LIVE_GAME_EDITING_CLASS_PATH = "resources/engine/LiveGameEditing";
    public static final ResourceBundle LIVE_GAME_EDITING_BUNDLE = ResourceBundle.getBundle(LIVE_GAME_EDITING_CLASS_PATH);
    public static final String LIVE_GAME_EDITING_TYPE_ROOT_KEY = "ActionTypeDocumentChildRoot";
    public static final String LIVE_GAME_EDITING_EDITABLE_ROOT_KEY = "EditableDocumentChildRoot";
    public static final String PROCESS_LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL = "LiveGameEditingRequestUnsuccessfullyProcessed";
    public static final String LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_GAME = "UnsuccessfulLiveGameEditingOnGameLevel";
    public static final String LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_LEVEL = "UnsuccessfulLiveGameEditingOnLevelLevel";
    public static final String LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_PROTOTYPE = "UnsuccessfulLiveGameEditingOnPrototypeLevel";

    private LevelActionsRequester myLevelActionsRequester;
    private GameActionsRequester myGameActionsRequester;

    /**
     * Purpose: Constructor for the actions processor
     * Assumptions: non-null levelActionsRequester and gameActionsRequester
     * TODO: Create null check for levelActionsRequester and GameActionsRequester for next steps
     * @param levelActionsRequester
     * @param gameActionsRequester
     */
    public ActionsProcessor(LevelActionsRequester levelActionsRequester, GameActionsRequester gameActionsRequester) {
        myGameActionsRequester = gameActionsRequester;
        myLevelActionsRequester = levelActionsRequester;
    }

    /**
     * Purpose: Processes the request to add a sprite to the collection of on-screen sprites.
     * Assumptions: The existence of the AddSpriteAction LevelAction and a valid LevelActionsRequester that processes this action.
     * @param prototypeId the id reference to the prototype of which the new sprite will be
     * @param xPos the x position for the new sprite
     * @param yPos the y position for the new sprite
     */
    public void processAddSpriteAction(int prototypeId, double xPos, double yPos) {
        LevelAction action = new AddSpriteAction(prototypeId, xPos, yPos);
        myLevelActionsRequester.requestAction(action);
    }

    /**
     * Purpose: Processes the request to a remove a sprite from the collection of on-scree sprites.
     * Assumptions: The existence of RemoveSpriteAction and a valid LevelActionsRequester that processes this action.
     * @param spriteId the id of the sprite to be removed
     */
    public void processRemoveSpriteAction(int spriteId) {
        LevelAction action = new RemoveSpriteAction(spriteId);
        myLevelActionsRequester.requestAction(action);
    }

    /**
     * Purpose: Processes the request to sell a tower.
     * Assumptions: The existence of SellTowerAction and a valid LevelActionsRequester that processes this action.
     * @param xpos the x-position of the sprite to be removed
     * @param ypos the y-position of the sprite to be removed
     */
    public void processSellTowerAction(double xpos, double ypos) {
        LevelAction action = new SellTowerAction(xpos, ypos);
        myLevelActionsRequester.requestAction(action);

    }

    /**
     * Purpose: Processes a Live Game Editing request via the Player by the GAE.
     * Assumptions: The Live Game Editing Bundle exists and is in the resources root.
     * @param doc The document with the new, edited specifications for the game.
     * @throws GameEngineException due to invalid XML that cannot be parsed and run by this game engine.
     */
    public void processGameEditingAction(Document doc) throws GameEngineException {
        Document document = ConfigurationTool.configureWithTestDocument("src/resources/player/EditedSpriteImageView.xml");
//        Document document = doc;
        Element documentRoot = document.getDocumentElement();
        String editGameActionType = documentRoot.getElementsByTagName(LIVE_GAME_EDITING_BUNDLE.getString(LIVE_GAME_EDITING_TYPE_ROOT_KEY)).item(0).getTextContent();
        Element editableObject = (Element) documentRoot.getElementsByTagName(LIVE_GAME_EDITING_BUNDLE.getString(LIVE_GAME_EDITING_EDITABLE_ROOT_KEY)).item(0);
        String methodName = LIVE_GAME_EDITING_BUNDLE.getString(editGameActionType);
        try {
            this.getClass().getDeclaredMethod(methodName, String.class, Element.class).invoke(this, editGameActionType, editableObject);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(e, PROCESS_LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL);
        }
    }

    private void processEditOnGameAction(String editGameActionType, Element editableObject) throws GameEngineException {
        try {
            GameAction action = (GameAction) Class.forName(GAME_ACTIONS_DIRECTORY_ROOT + editGameActionType).getConstructor(Element.class).newInstance(editableObject);
            myGameActionsRequester.requestAction(action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(e, LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_GAME);
        }
    }

    private void processEditOnLevelAction(String editLevelActionType, Element editableObject) throws GameEngineException {
        try {
            LevelAction action = (LevelAction) Class.forName(LEVEL_ACTIONS_DIRECTORY_ROOT + editLevelActionType).getConstructor(Element.class).newInstance(editableObject);
            myLevelActionsRequester.requestAction(action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_LEVEL);
        }
    }

    private void processEditPrototypeAction(String editPrototypeActionType, Element editableObject) throws GameEngineException {
        try {
            Object action = Class.forName(ACTIONS_DIRECTORY_ROOT + editPrototypeActionType).getConstructor(Element.class).newInstance(editableObject);
            myLevelActionsRequester.requestAction((LevelAction) action);
            myGameActionsRequester.requestAction((GameAction) action);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new GameEngineException(LIVE_GAME_EDITING_ACTION_UNSUCCESSFUL_PROTOTYPE);
        }
    }

    /**
     * Purpose: Allows the game engine to update the LevelActionsRequester to keep with the most up to date level
     * Assumptions: Assumes player will not change the LevelActionsRequester (Currently, a design guard against this is
     * provide Player with no mechanism to get a LevelActionsRequester object through the engine API). Assumes a non-null
     * LevelActionsRequester (in the future, a null-check can be implemented to ensure this).
     * @param levelActionsRequester the LevelActionsRequester for the most current level.
     */
    public void updateLevelActionsRequester(LevelActionsRequester levelActionsRequester) {
        myLevelActionsRequester = levelActionsRequester;
    }
}
