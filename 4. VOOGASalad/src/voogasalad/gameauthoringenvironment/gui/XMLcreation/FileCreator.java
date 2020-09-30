package voogasalad.gameauthoringenvironment.gui.XMLcreation;

import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

public class FileCreator {
    public static final String SAVE_PATH = "/Users/sumervardhan/Desktop/Backup October 2019/DesktopBackup/Fall 2019/CS308/testing.xml";
    public static final String LEVEL_PROPERTIES = "resources.gae.XMLCreatorProperties.LevelAttributes";
    public static final String SPRITE_PROTOTYPE_PROPERTIES = "resources.gae.XMLCreatorProperties.SpritePrototypeAttributes";
    public static final String GAME_CONDITION_PROPERTIES = "resources.gae.XMLCreatorProperties.GameConditionAttributes";
    public static final String STRATEGIES_PROPERTIES = "resources.gae.XMLCreatorProperties.StrategiesAttributes";
    public static final String ACTION_PROPERTIES = "resources.gae.XMLCreatorProperties.ActionAttributes";
    public static final String LEVEL_TAG = "Level";
    private static final String SPRITE_PROTOTYPE_TAG = "SpritePrototype";
    public static final String GAME_CONDITION_TAG = "GameCondition";
    private DocumentBuilder myBuilder;
    private Document myDocument;
    private String myFileSavePath;

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        FileCreator f = new FileCreator(SAVE_PATH);
        //f.createSpritePrototypeElement();
        HashMap<String, String> map = new HashMap<>();
        map.put("Attribute0", "Value0");
        map.put("Attribute1", "Value1");
        f.addStrategyToSpritePrototype(f.createSpritePrototypeElement(), "MovementStrategy", "NoMovement", map);
        f.saveFile();
    }

    public FileCreator(String savePath) throws ParserConfigurationException {
        this.myFileSavePath = savePath;
        myBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        myDocument = myBuilder.newDocument();
        myDocument.appendChild(myDocument.createElement("GameConfiguration"));
    }

    public Element createLevelElement() {
        return createElementByTagType(LEVEL_TAG, LEVEL_PROPERTIES);
    }

    public Element createSpritePrototypeElement(){
        return createElementByTagType(SPRITE_PROTOTYPE_TAG, SPRITE_PROTOTYPE_PROPERTIES);
    }

    public void createGameConditionElement(String type, int conditionID, Map<String, String> paramMap){
        Element element = createElementByTagType(GAME_CONDITION_TAG, GAME_CONDITION_PROPERTIES);
    }

    public void addConditionToLevel(Element levelRoot, String conditionName, Map<String, String> paramMap){
        Element conditionRoot = myDocument.createElement("Condition");
        Element typeRoot = myDocument.createElement("Type");
        typeRoot.setTextContent(conditionName);
        conditionRoot.appendChild(typeRoot);
        Element parameterRoot = myDocument.createElement("Parameters");
        conditionRoot.appendChild(parameterRoot);
        for(String param : paramMap.keySet()){
            Element element = myDocument.createElement(param);
            element.setTextContent(paramMap.get(param));
            parameterRoot.appendChild(element);
        }
        levelRoot.appendChild(conditionRoot);
    }

    public void addActionToCondition(Element conditionRoot, String actionType){
        Element actionRoot = myDocument.createElement("Action");
        Element typeRoot = myDocument.createElement("Type");
        typeRoot.setTextContent(actionType);
        actionRoot.appendChild(typeRoot);
        conditionRoot.appendChild(actionRoot);
    }

    public void addWaveToLevel(Element levelRoot, String queue, String interval, String spawnPointX, String spawnPointY, String path){
        Element waveRoot = myDocument.createElement("Wave");
        Element queueRoot = myDocument.createElement("Queue");
        queueRoot.setTextContent(queue);
        Element intervalRoot = myDocument.createElement("Interval");
        intervalRoot.setTextContent(interval);
        Element spawnPointXRoot = myDocument.createElement("SpawnPointX");
        spawnPointXRoot.setTextContent(spawnPointX);
        Element spawnPointYRoot = myDocument.createElement("SpawnPointY");
        spawnPointYRoot.setTextContent(spawnPointY);
        Element pathRoot = myDocument.createElement("Path");
        pathRoot.setTextContent(path);
        waveRoot.appendChild(queueRoot);
        waveRoot.appendChild(intervalRoot);
        waveRoot.appendChild(spawnPointXRoot);
        waveRoot.appendChild(spawnPointYRoot);
        waveRoot.appendChild(pathRoot);
        levelRoot.appendChild(waveRoot);
    }

    public void addStrategyToSpritePrototype(Element spriteRoot, String strategyName, String strategyType, Map<String, String> paramMap){
        Element strategyRoot = myDocument.createElement(strategyName);
        Element typeRoot = myDocument.createElement("Type");
        typeRoot.setTextContent(strategyType);
        strategyRoot.appendChild(typeRoot);
        Element parameterRoot = myDocument.createElement("Parameters");
        strategyRoot.appendChild(parameterRoot);
        for(String param : paramMap.keySet()){
            Element element = myDocument.createElement(param);
            element.setTextContent(paramMap.get(param));
            parameterRoot.appendChild(element);
        }
        spriteRoot.getElementsByTagName("Strategies").item(0).appendChild(strategyRoot);
    }

    public void addActionToGameCondition(Element gameConditionRoot, String type){
        Element actionRoot = myDocument.createElement("Action");
        Element typeRoot = myDocument.createElement("Type");
        typeRoot.setTextContent(type);
        actionRoot.appendChild(typeRoot);
        gameConditionRoot.getElementsByTagName("Actions").item(0).appendChild(actionRoot);
    }

    private Element createElementByTagType(String tagName, String propertiesPath){
        ResourceBundle levelResources = ResourceBundle.getBundle(propertiesPath);
        HashMap<String, String> outerMap = new HashMap<>();
        HashMap<String, String> innerMap = new HashMap<>();
        List<Element> nestedElements = new ArrayList<>();
        for (String att : levelResources.keySet()) {
            String[] nestedAttributes = levelResources.getString(att).split(":");
            if (nestedAttributes.length == 0) {
                outerMap.put(att, "");
            }
            else {
                for (String nestedAtt : nestedAttributes) {
                    innerMap.put(nestedAtt, "");
                }
                nestedElements.add(createNewElement(att, innerMap));
                innerMap.clear();
            }
        }
        Element element = createNewElement(tagName, outerMap);
        for (Element e : nestedElements) {
            element.appendChild(e);
        }
        return element;
    }


    private Element createNewElement(String name, Map<String, String> attributeMap){
        Element element = myDocument.createElement(name);
        myDocument.getDocumentElement().appendChild(element);
        for(String att : attributeMap.keySet()){
            Element toAdd = myDocument.createElement(att);
            element.appendChild(toAdd);
        }
        return element;
    }

    public void saveFile() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(myDocument);
        StreamResult streamResult = new StreamResult(new File(myFileSavePath));
        transformer.transform(domSource, streamResult);
    }
}
