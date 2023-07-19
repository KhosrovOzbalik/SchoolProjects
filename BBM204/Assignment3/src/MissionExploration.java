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
import java.util.ArrayList;
import java.util.List;

public class MissionExploration {

    /**
     * Given a Galaxy object, prints the solar systems within that galaxy.
     * Uses exploreSolarSystems() and printSolarSystems() methods of the Galaxy object.
     *
     * @param galaxy a Galaxy object
     */
    public void printSolarSystems(Galaxy galaxy) {
        // TODO: YOUR CODE HERE
        List<SolarSystem> systems =  galaxy.exploreSolarSystems();
        galaxy.printSolarSystems(systems);
    }

    /**
     * TODO: Parse the input XML file and return a list of Planet objects.
     *
     * @param filename the input XML file
     * @return a list of Planet objects
     */
    public Galaxy readXML(String filename) {
        List<Planet> planetList = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(filename));

            document.getDocumentElement().normalize();

            NodeList planets = document.getElementsByTagName("Planet");

            for (int i = 0; i < planets.getLength(); i++) {
                Node planet = planets.item(i);

                if (planet.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) planet;

                    String id = element.getElementsByTagName("ID").item(0).getTextContent();
                    int techLevel = Integer.parseInt(element.getElementsByTagName("TechnologyLevel").item(0).getTextContent());

                    List<String> neighbors = new ArrayList<>();
                    NodeList neighborNodes = element.getElementsByTagName("PlanetID");
                    for (int j = 0; j < neighborNodes.getLength(); j++) {
                        Element neigbor = (Element) neighborNodes.item(j);
                        neighbors.add(neigbor.getTextContent());
                    }

                    Planet tempPlanet = new Planet(id, techLevel, neighbors);
                    planetList.add(tempPlanet);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return new Galaxy(planetList);
    }
}
