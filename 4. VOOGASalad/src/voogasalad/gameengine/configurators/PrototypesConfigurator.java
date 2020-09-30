package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.*;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;
import voogasalad.gameengine.executors.sprites.strategies.cost.CostStrategy;
import voogasalad.gameengine.executors.sprites.strategies.effect.EffectStrategy;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
/**
 * Class: PrototypesConfigurator
 * Purpose: Configure prototypes for the game
 * Assumptions: Assumes correct resource bundle and path for resource bundles
 * Dependencies: Dependent on Strategy and StrategyBuilder classes; dependent on resource bundle static constant from GameConfigurator
 * Example of how to use: Can be used by GameConfigurator to configure prototypes for level
 * Other details: N/A
 */
public class PrototypesConfigurator {

    public static final String PROTOTYPE_CONFIG_METHOD_CALLS_PATH = "resources/engine/PrototypeConfiguratorMethodCalls";
    public static final String STRATEGY_CONFIG_METHOD_CALLS_PATH = "resources/engine/StrategyConfiguratorMethodCalls";
    public static final String STRATEGY_BUILDERS_PATH = "resources/engine/StrategyBuilders";
    public static final String STRATEGY_BUILDER_CLASS_PATH = "voogasalad.gameengine.executors.objectcreators.";
    public static final ResourceBundle PROTOTYPE_CONFIG_BUNDLE = ResourceBundle.getBundle(PROTOTYPE_CONFIG_METHOD_CALLS_PATH);
    public static final ResourceBundle STRATEGY_CONFIG_BUNDLE = ResourceBundle.getBundle(STRATEGY_CONFIG_METHOD_CALLS_PATH);
    public static final ResourceBundle STRATEGY_BUILDERS_BUNDLE = ResourceBundle.getBundle(STRATEGY_BUILDERS_PATH);
    public static final String SPRITE_PROPERTIES_NODE_KEY = "SpritePropertiesNodeTag";
    public static final String SPRITE_STRATEGIES_NODE_KEY = "SpriteStrategiesNodeTag";
    public static final String SPRITE_STRATEGIES_TYPE_NODE_KEY = "SpriteStrategiesTypeTag";
    public static final String SPRITE_STRATEGIES_PARAMETERS_NODE_KEY = "SpriteStrategiesParametersTag";

    //TODO: Refactor all the different strategy builders into one builder.

    private NodeList myPrototypesNodesList;

    /**
     * Purpose: Builds list of prototype for a game from XML nodes list
     * Assumptions: N/A
     * @param prototypesNodeList the list of prototype nodes from the XML
     * @return the list of prototypes converted from the XML node list
     * @throws GameEngineException when sprites fail to configure
     */
    public List<Sprite> buildPrototypesList(NodeList prototypesNodeList) throws GameEngineException {
        myPrototypesNodesList = prototypesNodeList; // this is all the defined prototypes in a level in the xml
        List<Sprite> prototypesForLevel = new ArrayList<>();
        for (int i = 0; i< myPrototypesNodesList.getLength(); i++) {
            SpriteBuilder spriteBuilder = new SpriteBuilder();
            Element definedPrototype = ConfigurationTool.convertNodeToElement(myPrototypesNodesList.item(i));

            Element propertiesRoot = ConfigurationTool.convertNodeToElement(definedPrototype.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(SPRITE_PROPERTIES_NODE_KEY)).item(0));
            Element strategiesRoot = ConfigurationTool.convertNodeToElement(definedPrototype.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(SPRITE_STRATEGIES_NODE_KEY)).item(0));

            setPropertiesForSpriteBuilder(spriteBuilder, propertiesRoot);
            setStrategiesForSpriteBuilder(spriteBuilder, strategiesRoot);
            prototypesForLevel.add(spriteBuilder.build());
        }
        return prototypesForLevel;
    }

    private void setPropertiesForSpriteBuilder(SpriteBuilder spriteBuilder, Element root) throws GameEngineException {
        NodeList listOfSpriteProperties = root.getChildNodes();
        for (int j=0; j<listOfSpriteProperties.getLength(); j++) {
            Element property = ConfigurationTool.convertNodeToElement(listOfSpriteProperties.item(j));
            if (property != null) {
                setSinglePropertyInBuilder(spriteBuilder, property);
            }
        }
    }

    private void setSinglePropertyInBuilder(SpriteBuilder builder, Node property) throws GameEngineException {
        String value = property.getTextContent();
        try {
            String methodName = PROTOTYPE_CONFIG_BUNDLE.getString(property.getNodeName());
            builder.getClass().getMethod(methodName, String.class).invoke(builder, value);
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteProductionFailedDueToPropertiesTag");
        }
    }

    private void setStrategiesForSpriteBuilder(SpriteBuilder builder, Element root) throws GameEngineException {
        NodeList listOfSpriteStrategies = root.getChildNodes();
        for (int i=0; i<listOfSpriteStrategies.getLength(); i++) {
            Element strategy = ConfigurationTool.convertNodeToElement(listOfSpriteStrategies.item(i));
            if (strategy != null) {
                try {
                    Object builtStrategy = buildStrategy(strategy);
                    String methodName = STRATEGY_CONFIG_BUNDLE.getString(strategy.getNodeName());
                    this.getClass().getDeclaredMethod(methodName, SpriteBuilder.class, Object.class).invoke(this, builder, builtStrategy);
                } catch (Exception e) {
                    throw new GameEngineException(e, "SpriteProductionFailedDueToStrategyTags");
                }
            }
        }
    }

    private Object buildStrategy(Element strategy) throws GameEngineException {
        try {
            var builder = Class.forName(STRATEGY_BUILDER_CLASS_PATH + STRATEGY_BUILDERS_BUNDLE.getString(strategy.getNodeName())).getConstructor().newInstance();
            String type = strategy.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(SPRITE_STRATEGIES_TYPE_NODE_KEY)).item(0).getTextContent();
            builder.getClass().getMethod(STRATEGY_CONFIG_BUNDLE.getString(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(SPRITE_STRATEGIES_TYPE_NODE_KEY)), String.class).invoke(builder, type);
            NodeList parametersNodeList = strategy.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(SPRITE_STRATEGIES_PARAMETERS_NODE_KEY)).item(0).getChildNodes();
            for (int i = 0; i < parametersNodeList.getLength(); i++) {
                Element parameter = ConfigurationTool.convertNodeToElement(parametersNodeList.item(i));
                if (parameter!= null) {
                    builder.getClass().getMethod(STRATEGY_CONFIG_BUNDLE.getString(parameter.getNodeName()), String.class).invoke(builder, parameter.getTextContent());
                }
            }
            StrategyBuilder strategyBuilder = (StrategyBuilder) builder;
            return strategyBuilder.build();
        } catch (Exception e) {
            throw new GameEngineException(e, "SpriteProductionFailedDueToStrategyParameters");
        }
    }

    private void setRotationStrategy(SpriteBuilder builder, Object strategy) {
        RotationStrategy toSet = (RotationStrategy) strategy;
        builder.setRotationStrategy(toSet);
    }

    private void setHealthStrategy(SpriteBuilder builder, Object strategy) {
        HealthStrategy toSet = (HealthStrategy) strategy;
        builder.setHealthStrategy(toSet);
    }

    private void setMovementStrategy(SpriteBuilder builder, Object strategy) {
        MovementStrategy toSet = (MovementStrategy) strategy;
        builder.setMovementStrategy(toSet);
    }

    private void setAttackStrategy(SpriteBuilder builder, Object strategy) {
        AttackStrategy toSet = (AttackStrategy) strategy;
        builder.setAttackStrategy(toSet);
    }

    private void setCostStrategy(SpriteBuilder builder, Object strategy) {
        CostStrategy toSet = (CostStrategy) strategy;
        builder.setCostStrategy(toSet);
    }

    private void setEffectStrategy(SpriteBuilder builder, Object strategy) {
        EffectStrategy toSet = (EffectStrategy) strategy;
        builder.setEffectStrategy(toSet);
    }
}
