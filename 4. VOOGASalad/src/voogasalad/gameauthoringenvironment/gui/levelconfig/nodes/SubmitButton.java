package voogasalad.gameauthoringenvironment.gui.levelconfig.nodes;

import javafx.scene.control.Button;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import javax.xml.parsers.ParserConfigurationException;

public class SubmitButton extends Button {
    private AddToXML sendToXML;
    public Document createdXML;
    public Bus busInstance;

    /**
     * @author Marc Jabbour
     * This class is one that had added functionality in the branch BranchForXMLLinking. This is where, on click, information would be sent to the XML.
     * @param createdXMLParam
     * @param sendToXMLParam
     * @param busInstanceParam
     */
    public SubmitButton(Document createdXMLParam, AddToXML sendToXMLParam, Bus busInstanceParam){
        super("Submit");
        createdXML = createdXMLParam;
        sendToXML = sendToXMLParam;
        busInstance = busInstanceParam;
        this.setOnMouseClicked(event -> {
            try {
                createdXML = sendToXML.createXML();
                busInstance.goToPlayer(createdXML);

            } catch (ParserConfigurationException | GameEngineException e) {
                throw new Error(e);
            }
        });
    }
}
