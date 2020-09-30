//package voogasalad.gameengine.configurators;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.w3c.dom.Node;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//
///**
// * This class handles parsing XML files and returning a completed object.
// *
// * @author Rhondu Smithwick
// * @author Robert C. Duvall
// */
//public class XMLParser {
//    // Readable error message that can be displayed by the GUI
//    public static final String ERROR_MESSAGE = "XML file does not represent %s";
//    // name of root attribute that notes the type of file expecting to parse
//    private final String TYPE_ATTRIBUTE;
//    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
//    private final DocumentBuilder DOCUMENT_BUILDER;
//    private Document document;
//    private Element root;
//
//
//    /**
//     * Create parser for XML files of given type.
//     */
//    public XMLParser (String type, Document xmlDoc) {
//        DOCUMENT_BUILDER = getDocumentBuilder();
//        TYPE_ATTRIBUTE = type;
//        root = xmlDoc.getDocumentElement();
//    }
//
//
//    // get root element of an XML file
//    private Element getRootElement (File xmlFile) {
//        try {
//            DOCUMENT_BUILDER.reset();
//            document = DOCUMENT_BUILDER.parse(xmlFile);
//            return document.getDocumentElement();
//        }
//        catch (SAXException | IOException e) {
//            throw new XMLException(e);
//        }
//    }
//
//    // get value of Element's text
//    public ArrayList<Map<String, String>> getListOfAttributeMaps () {
//        ArrayList<Map<String,String>> componentMap = new ArrayList<Map<String, String>>();
//        NodeList nodeList = root.getChildNodes();
//        if(nodeList != null && nodeList.getLength() > 0) {
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node current_node = (Node) nodeList.item(i);
//                String current_component_name = current_node.getNodeName();
//                NodeList current_attributes = current_node.getChildNodes();
//                HashMap<String, String> attributeMap = new HashMap<String, String>();
//                if(current_attributes != null && current_attributes.getLength() > 0) {
//                    for (int j = 0; j < current_attributes.getLength(); j++) {
//                        attributeMap.put(current_attributes.item(j).getNodeName(), current_attributes.item(j).getTextContent());
//                    }
//                }
//                componentMap.add(attributeMap);
//            }
//        }
//        return componentMap;
//    }
//
//    // boilerplate code needed to make a documentBuilder
//    private DocumentBuilder getDocumentBuilder () {
//        try {
//            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        }
//        catch (ParserConfigurationException e) {
//            throw new XMLException(e);
//        }
//    }
//}
//
