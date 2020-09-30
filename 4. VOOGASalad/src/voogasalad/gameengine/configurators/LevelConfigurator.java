package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.control.levelcontrol.Wave;
import voogasalad.gameengine.executors.control.condition.level.LevelCondition;
import voogasalad.gameengine.executors.objectcreators.LevelBuilder;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.ConfigurationTool;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class: LevelConfigurator
 * Purpose: configures the list of levels for the game
 * Assumptions: Assumes existence of default background and sound in the correct path
 * Dependencies: Depends on WavesConfigurator and ConditionsConfigurator; dependent on resource bundle static constant from GameConfigurator
 * Example of how to use: GameConfigurator uses LevelConfigurator's configure method to configure levels
 * Other details: N/A
 */
public class LevelConfigurator {

    public static final String WAVE_NODES_KEY = "WavesNodeTag";
    public static final String RESOURCES_NODE_KEY = "ResourcesNodeTag";
    public static final String LIVES_NODE_KEY = "LivesNodeTag";
    public static final String CONDITION_NODES_KEY = "ConditionsNodeTag";
    public static final String LEVEL_ID_NODE_KEY = "LevelIdNodeTag";
    public static final String BACKGROUND_PATH_KEY = "BackgroundImageNodeTag";
    public static final String SOUND_PATH_KEY = "SoundFileNodeTag";
    public static final String PROTOTYPE_SPECIFIED_FOR_LEVEL_NODE_KEY = "PrototypesSpecifiedNodeTag";
    public static final String DEFAULT_BACKGROUND_PATH = "images/whitebackground.jpg";
    public static final String DEFAULT_SOUND_PATH = "BlankNoise.mp3";

    private Element myCurrentLevelRoot;
    private List<Sprite> myAvailablePrototypesList;

    /**
     * Purpose: Configures the list of levels for the game
     * Assumptions: Assumes the existence of the bundles required
     * @param levelNodes list of nodes that represent the levels
     * @param gamePrototypes list of sprite prototypes available for the level
     * @return the list of levels for the game
     * @throws GameEngineException if levels fail to configure
     */
    public List<Level> configureLevels(NodeList levelNodes, List<Sprite> gamePrototypes) throws GameEngineException {
        List<Level> levels = new ArrayList<>();
        for (int i=0; i<levelNodes.getLength(); i++) {
            Element levelRoot = ConfigurationTool.convertNodeToElement(levelNodes.item(i));
            myCurrentLevelRoot = levelRoot;
            myAvailablePrototypesList = configureLevelPrototypes(gamePrototypes);
            Collection<Wave> wavesCollection = configureWaves();
            int resources = configureIntProperties(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(RESOURCES_NODE_KEY));
            int lives = configureIntProperties(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(LIVES_NODE_KEY));
            int levelId = configureIntProperties(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(LEVEL_ID_NODE_KEY));
            Collection<LevelCondition> levelConditions = configureLevelConditions();
            String backgroundPath = configureBackgroundPath();
            String soundPath = configureSoundPath();
            levels.add(new LevelBuilder(levelId).setConditions(levelConditions)
                    .setLives(lives).setResources(resources).setSpritePrototypes(myAvailablePrototypesList)
                    .setWaves(wavesCollection).setBackgroundPath(backgroundPath).setSoundPath(soundPath).build());
        }
        return levels;
    }

    private Collection<Wave> configureWaves() throws GameEngineException {
        WavesConfigurator wavesConfigurator = new WavesConfigurator();
        NodeList waveNodes = myCurrentLevelRoot.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(WAVE_NODES_KEY));
        return wavesConfigurator.buildWavesCollection(waveNodes, myAvailablePrototypesList);
    }

    private int configureIntProperties(String propertyNodeTagName) {
        try {
            return Integer.parseInt(myCurrentLevelRoot.getElementsByTagName(propertyNodeTagName).item(0).getTextContent());
        } catch (NullPointerException | NumberFormatException e) {
            return 0;
        }
    }

    private Collection<LevelCondition> configureLevelConditions() throws GameEngineException {
        ConditionsConfigurator conditionsConfigurator = new ConditionsConfigurator();
        NodeList conditionNodes = myCurrentLevelRoot.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(CONDITION_NODES_KEY));
        return conditionsConfigurator.buildLevelConditionsCollection(conditionNodes);
    }

    private String configureBackgroundPath() {
        try {
            return myCurrentLevelRoot.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(BACKGROUND_PATH_KEY)).item(0).getTextContent();
        } catch (NullPointerException e) {
            return DEFAULT_BACKGROUND_PATH;
        }
    }

    private String configureSoundPath() {
        try {
            return myCurrentLevelRoot.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(SOUND_PATH_KEY)).item(0).getTextContent();
        } catch (NullPointerException e) {
            return DEFAULT_SOUND_PATH;
        }
    }

    private List<Sprite> configureLevelPrototypes(List<Sprite> gamePrototypes) {
        List<Sprite> prototypesSpecifiedForLevel = new ArrayList<>();
        try {
            configureLevelPrototypesHelper(gamePrototypes, prototypesSpecifiedForLevel);
        } catch (NullPointerException | NumberFormatException e) {
            return gamePrototypes;
        }
        return prototypesSpecifiedForLevel;
    }

    private void configureLevelPrototypesHelper(List<Sprite> gamePrototypes, List<Sprite> prototypesSpecifiedForLevel) {
        String[] prototypesSpecifiedForLevelAsStrings = myCurrentLevelRoot.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(PROTOTYPE_SPECIFIED_FOR_LEVEL_NODE_KEY)).item(0).getTextContent().split(" ");
        for (String prototypeString : prototypesSpecifiedForLevelAsStrings) {
            for (Sprite prototype : gamePrototypes) {
                if (prototype.getPrototypeId() == Integer.parseInt(prototypeString) || prototype.getSpriteArchetype()== SpriteArchetype.PROJECTILE) {
                    prototypesSpecifiedForLevel.add(prototype);
                }
            }
        }
    }

}
