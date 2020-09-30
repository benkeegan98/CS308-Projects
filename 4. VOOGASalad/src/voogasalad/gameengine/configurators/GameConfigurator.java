package voogasalad.gameengine.configurators;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.control.gamecontrol.controllers.GameLevelsController;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.condition.game.GameCondition;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.*;

/**
 * Class: GameConfigurator
 * Purpose: Configures a game
 * Assumptions: Assumes correct resource bundle and resource path
 * Dependencies: Depends on LevelsConfigurator to configure levels and PrototypesConfigurator to configure prototypes
 * Example of how to use: Can be used by Game to set up its initial state
 * Other details: N/A
 */
public class GameConfigurator {

    public static final String GAME_CONFIGURATION_RESOURCE_PATH = "resources/engine/EngineXMLTags";
    public static final ResourceBundle GAME_CONFIGURATION_RESOURCE_BUNDLE = ResourceBundle.getBundle(GAME_CONFIGURATION_RESOURCE_PATH);
    public static final String LEVELS_NODES_KEY = "LevelNodeTag";
    public static final String LEVELS_SEQUENCE_NODE_KEY = "LevelsSequenceNodeTag";
    public static final String PROTOTYPES_NODES_KEY = "PrototypesNodeTag";
    public static final String GAME_CONDITIONS_NODES_KEY = "GameConditionsNodeTag";
    public static final String GAME_TITLE_NODE_KEY = "GameTitleNodeTag";

    private Element myRoot;
    private Document myDocument;
    private List<Sprite> myGamePrototypes;

    /**
     * Purpose: Constructor for GameConfigurator to set up game
     * Assumptions: N/A
     * @param doc the XML document to configure the game
     * @throws GameEngineException if the game fails to configure
     */
    public GameConfigurator(Document doc) throws GameEngineException {
        myDocument = doc;
        myRoot = myDocument.getDocumentElement();
        myGamePrototypes = configurePrototypes();
    }

    /**
     * Purpose: Gets the prototypes for the game
     * Assumptions: N/A
     * @return the list of prototypes configured for the game
     */
    public List<Sprite> getGamePrototypesCollection() {
        return myGamePrototypes;
    }

    /**
     * Purpose: Configures the levels for the game from the XML for the LevelsController
     * Assumptions: Assumes GameLevelsController class existence
     * @return a GameLevelsController for the game with the configured list of levels and level sequence
     * @throws GameEngineException if levels fail to configure
     */
    public GameLevelsController loadLevelsFromXML() throws GameEngineException {
        List<Level> levels = loadLevels();
        List<Integer> levelsSequence = loadLevelsSequence();
        return new GameLevelsController(levels, levelsSequence);
    }

    private List<Level> loadLevels() throws GameEngineException {
        try {
            LevelConfigurator levelConfigurator = new LevelConfigurator();
            return levelConfigurator.configureLevels(myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(LEVELS_NODES_KEY)), myGamePrototypes);
        } catch (NullPointerException e) {
            throw new GameEngineException(e, "NoLevelsSpecified");
        }
    }

    private List<Integer> loadLevelsSequence() {
        List<Integer> levelsSequence = new ArrayList<>();
        try {
            List<String> levelsSequenceAsStringList = Arrays.asList(myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(LEVELS_SEQUENCE_NODE_KEY)).item(0).getTextContent().split(" "));
            for (String levelId : levelsSequenceAsStringList) {
                levelsSequence.add(Integer.parseInt(levelId));
            }
        } catch (NullPointerException e) {
            // do nothing; empty levels sequence returned
        }
        return levelsSequence;
    }

    private List<Sprite> configurePrototypes() throws GameEngineException {
        PrototypesConfigurator prototypesConfigurator = new PrototypesConfigurator();
        NodeList prototypeNodes = myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(PROTOTYPES_NODES_KEY));
        return prototypesConfigurator.buildPrototypesList(prototypeNodes);
    }

    /**
     * Purpose: Configures the conditions of the game from the XML
     * Assumptions: Assumes dependency on ConditionsConfigurator
     * @return a collection of conditions for the game
     * @throws GameEngineException if the conditions fail to configure
     */
    public Collection<GameCondition> configureGameConditions() throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        NodeList conditionNodes = myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(GAME_CONDITIONS_NODES_KEY));
        return conditionsConfigurator.buildGameConditionsCollection(conditionNodes);
    }

    /**
     * Purpose: Configures the title of the game from the XML
     * Assumptions: N/A
     * @return the String that represents the title of the game
     */
    public String configureGameTitle() {
        try {
            return myRoot.getElementsByTagName(GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(GAME_TITLE_NODE_KEY)).item(0).getTextContent();
        } catch (NullPointerException e) {
            return "";
        }
    }
}
