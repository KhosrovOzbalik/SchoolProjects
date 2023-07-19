import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) {
        // TODO: YOUR CODE HERE
        Map<String, Malware> dict = new HashMap<String, Malware>();


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(filename));

            document.getDocumentElement().normalize();

            NodeList rows = document.getElementsByTagName("row");

            for (int i = 0; i < rows.getLength(); i++) {
                Node row = rows.item(i);

                if (row.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) row;

                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String hash  = element.getElementsByTagName("hash").item(0).getTextContent();
                    String level = element.getElementsByTagName("level").item(0).getTextContent();

                    dict.put(hash, new Malware(title,Integer.parseInt(level),hash));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return dict;
    }
}
