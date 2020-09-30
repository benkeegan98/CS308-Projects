package voogasalad.gameauthoringenvironment.gui.tabconfig;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;

import voogasalad.gameauthoringenvironment.gui.mapconfig.GameInfoConfig;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ObjectPreviewAndActive;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

public class TabPaneCreator {
    private static final String SPRITE_OPTIONS_RESOURCE = "resources.gae.tabcreation.SpriteOptions";
    private static final String PARAM_FIELD_TYPE_RESOURCE = "resources.gae.tabcreation.ParamToInputType";

    private TabPane myTabPane;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private LevelConfigPane levelConfigPane;
    private Map<String, Map<String, String>> allActiveObjects;
    private List<ObjectPreviewAndActive> allActiveObjectObjects;

    /**
     * @author Marc Jabbour
     * This class defines what Tabs are going to be added to the TabPane
     * It reads properties files and creates Tabs accordingly, associating the parameters each Tab should contain to associated Nodes using reflection
     * @param sendToXMLParam
     * @param createdXMLParam
     * @param busInstanceParam
     */
    public TabPaneCreator(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam) {
        allActiveObjectObjects = new ArrayList<>();
        allActiveObjects = new HashMap<>();
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS_RESOURCE);
        paramFieldType = ResourceBundle.getBundle(PARAM_FIELD_TYPE_RESOURCE);
        myTabPane = createTabPane();
        myTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    /**
     * A getter method to return local variable myTabPane
     * @return a TabPane with tabs already added
     */
    public TabPane getTabPane() {

        return myTabPane;
    }

    private TabPane createTabPane() {
        String[] objectsFromResource = Arrays.copyOf(typeToParams.keySet().toArray(), typeToParams.keySet().toArray().length, String[].class);
        levelConfigPane = new LevelConfigPane(sendToXML, createdXML, busInstance, allActiveObjects, allActiveObjectObjects, objectsFromResource);
        TabPane tabPane = new TabPane();
        Tab gameTab = new Tab("Game");
        gameTab.setContent(new GameInfoConfig());
        tabPane.getTabs().add(gameTab);
        createPane(tabPane, levelConfigPane);
        Tab levelTab = new Tab("Level");
        levelTab.setContent(levelConfigPane);
        tabPane.getTabs().add(levelTab);

        return tabPane;
    }

    private void createPane(TabPane tabPane, LevelConfigPane levelConfigPane) {
        typeToParams.getKeys().asIterator().forEachRemaining(key -> {
            try {
                Tab objectTab = new Tab(key, new ParameterCreator(key, typeToParams.getString(key).split(","), paramFieldType, levelConfigPane, allActiveObjects, allActiveObjectObjects));
                tabPane.getTabs().add(objectTab);
            } catch (ParserConfigurationException e) {
                throw new Error(e);
            }
        });
    }
}
