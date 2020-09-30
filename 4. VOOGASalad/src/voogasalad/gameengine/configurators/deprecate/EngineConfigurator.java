//package voogasalad.gameengine.configurators;
//
//import org.w3c.dom.Document;
//import voogasalad.gameengine.executors.exceptions.GameEngineException;
//import voogasalad.gameengine.executors.gamecontrol.condition.ConditionClassification;
//import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
//import voogasalad.gameengine.executors.gamecontrol.level.Level;
//import voogasalad.gameengine.executors.gamecontrol.Wave;
//import voogasalad.gameengine.executors.gamecontrol.action.level.LevelAction;
//import voogasalad.gameengine.executors.gamecontrol.condition.level.LevelCondition;
//import voogasalad.gameengine.executors.gamecontrol.condition.level.TemporalCondition;
//import voogasalad.gameengine.executors.objectcreators.SpriteBuilder;
//
//import java.awt.*;
//import java.util.*;
//
//public class EngineConfigurator {
//
//    public static final String TYPE = "GameConfig";
//    public static final String ACTION_PATH = "voogasalad.gameengine.executors.gamecontrol.action.";
//    public static final int WINDOW_SIZE_FOR_HARD_CODED_PATH = 500;
//
//    private Document myDocument;
//    private XMLParser myXMLParser;
//    private StrategiesFactory myStrategiesFactory;
//    private EngineDriverManager myEngineDriverManager;
//
//
//    public void loadXML(Document document) {
//        myDocument = document;
//        myXMLParser = new XMLParser(TYPE, myDocument);
//    }
//
//    public void initializeGame() {
//        myStrategiesFactory = new StrategiesFactory();
//        myEngineDriverManager = new EngineDriverManager();
//    }
//
//    public Level initializeEngineForGame() throws GameEngineException {
//        ArrayList<Map<String, String>> componentList = myXMLParser.getListOfAttributeMaps();
//        if(componentList != null && componentList.size() > 0) {
//            for (int i = 0; i < componentList.size(); i++) {
//                instantiateEngineObject(componentList.get(i));
//            }
//        }
//        Queue<Integer> spritesWave0Queue = new LinkedList<>() {{ add(0); add(1); add(2);}};
//        myEngineDriverManager.addWave(createWave(new Point(0, 0), spritesWave0Queue, 1));
//        return myEngineDriverManager.getNewLevel();
//    }
//
//    private Wave createWave(Point waveSpawnPoint, Queue<Integer> spritesWaveQueue, double spriteInterval) throws GameEngineException {
//        LevelAction levelAction = addAction("SpawnWaveAction");
//        Set<LevelAction> levelActions = new HashSet<>();
//        levelActions.add(levelAction);
//        LevelCondition condition = new TemporalCondition(0, ConditionClassification.ONETIME, levelActions);
//        myEngineDriverManager.addLevelCondition(condition);
//        Wave wave = new Wave(spritesWaveQueue, spriteInterval, waveSpawnPoint);
//        return wave;
//    }
//
//    private LevelAction addAction(String action) {
//        try {
//            LevelAction levelAction = (LevelAction) Class.forName(ACTION_PATH + action).getConstructor().newInstance();
//            myEngineDriverManager.addLevelAction(levelAction);
//            return levelAction;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //Instantiates sprite and adds it to Sprite manager
//    private void instantiateEngineObject(Map<String, String> componentAttributeMap ) throws GameEngineException {
//        //Initialise default parameter values when none set
//        int id = 0, health = 0;
//        double xpos = 0, ypos = 0, width = 0, height = 0;
//        String healthstrategy = "NoHealth", imagePath = "";
//
//        //Update values for parameters that have been specified
//        for(String att : componentAttributeMap.keySet()){
//
//            if(att.equalsIgnoreCase("id")){
//                id = Integer.parseInt(componentAttributeMap.get("ID"));
//            }
//            if(att.equalsIgnoreCase("Health")){
//                healthstrategy = "Health";
//                health = Integer.parseInt(componentAttributeMap.get("Health"));
//            }
//            if(att.equalsIgnoreCase("xpos")){
//                xpos = Double.parseDouble(componentAttributeMap.get("xpos"));
//            }
//            if(att.equalsIgnoreCase("ypos")){
//                ypos = Double.parseDouble(componentAttributeMap.get("ypos"));
//            }
//            if(att.equalsIgnoreCase("Width")){
//                width = Double.parseDouble(componentAttributeMap.get("width"));
//            }
//            if(att.equalsIgnoreCase("Height")){
//                height = Double.parseDouble(componentAttributeMap.get("height"));
//            }
//            if(att.equalsIgnoreCase("Image")){
//                imagePath = componentAttributeMap.get("Image");
//            }
//        }
//        int finalHealth = health;
//        Map<String, Object> prototypeHealthParameter = new HashMap<>() {{ put("health", finalHealth); }};
//        LinkedList<Point> path = new LinkedList<>();
//        path.add(new Point(0, WINDOW_SIZE_FOR_HARD_CODED_PATH/2));
//        path.add(new Point(WINDOW_SIZE_FOR_HARD_CODED_PATH/2, WINDOW_SIZE_FOR_HARD_CODED_PATH/2));
//        path.add(new Point(WINDOW_SIZE_FOR_HARD_CODED_PATH, WINDOW_SIZE_FOR_HARD_CODED_PATH/2));
//        Map<String, Object> prototypeMovementParameter = new HashMap<>();
//        prototypeMovementParameter.put("path", path);
//        prototypeMovementParameter.put("speed", 50.0);
//        myEngineDriverManager.addSpritePrototype(new SpriteBuilder().setX(xpos).setY(ypos).setWidth(width).setHeight(height)
//                .setImagePath(imagePath).setPrototypeId(id).setHealthStrategy(myStrategiesFactory.makeHealth(healthstrategy, prototypeHealthParameter))
//                .setMovementStrategy(myStrategiesFactory.makeMovement("PathMovement", prototypeMovementParameter)).build());
//    }
//}
