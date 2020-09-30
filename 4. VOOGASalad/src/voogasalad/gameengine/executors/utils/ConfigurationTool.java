package voogasalad.gameengine.executors.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Configures the test document
 */
public class ConfigurationTool {

    /**
     * Converts a node to an element
     * @param node to be converted to an element
     * @return the node as an element
     */
    public static Element convertNodeToElement(Node node) {
        Element element = null;
        if (node.getNodeType()==Node.ELEMENT_NODE) {
            element = (Element) node;
        }
        return element;
    }

    /**
     * Configures the game authoring environment with a test document by making the XML into a document through the
     * document factory and then parsing it
     * @param path the string representing the path to the test document
     * @return the newly parsed document
     * @throws GameEngineException
     */
    public static Document configureWithTestDocument(String path) throws GameEngineException {
        File testFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder ;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(testFile);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new GameEngineException(e, "ConfigurationFailedXML");
        }
    }

    /**
     * Parses a string that represents points on a path, and converts it to a List of Points to be used by the engine
     * @param pathString a string that represents an indefinite number of points on a path
     * @return a list of newly converted path points
     */
    public static List<Point2D.Double> parsePath(String pathString) {
        List<Point2D.Double> parsedPath = new ArrayList<>();
        String[] pointStrings = pathString.strip().split(";");
        for(String pointString : pointStrings) {
            Point2D.Double toAdd = new Point2D.Double();
            String[] coordinateStrings = pointString.split(",");
            toAdd.setLocation(Double.parseDouble(coordinateStrings[0]), Double.parseDouble(coordinateStrings[1]));
            parsedPath.add(toAdd);
        }
        return parsedPath;
    }
}
