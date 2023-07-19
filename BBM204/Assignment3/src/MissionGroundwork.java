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
import java.util.*;

public class MissionGroundwork {

    /**
     * Given a list of Project objects, prints the schedule of each of them.
     * Uses getEarliestSchedule() and printSchedule() methods of the current project to print its schedule.
     * @param projectList a list of Project objects
     */
    public void printSchedule(List<Project> projectList) {
        // TODO: YOUR CODE HERE

        for (Project project : projectList) {
            int[] schedule = project.getEarliestSchedule();
            project.printSchedule(schedule);
        }
    }

    /**
     * TODO: Parse the input XML file and return a list of Project objects
     *
     * @param filename the input XML file
     * @return a list of Project objects
     */
    public List<Project> readXML(String filename) {
        List<Project> projectList = new ArrayList<>();
        // TODO: YOUR CODE HERE

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(filename));

            document.getDocumentElement().normalize();

            NodeList projects = document.getElementsByTagName("Project");

            for (int i = 0; i < projects.getLength(); i++) {
                Node project = projects.item(i);

                if (project.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) project;

                    List<Task> tasks = new ArrayList<>();
                    NodeList taskNode = element.getElementsByTagName("Task");
                    for (int j = 0; j < taskNode.getLength(); j++) {
                        Element task = (Element) taskNode.item(j);

                        int id = Integer.parseInt(task.getElementsByTagName("TaskID").item(0).getTextContent());
                        String description = task.getElementsByTagName("Description").item(0).getTextContent();
                        int duration = Integer.parseInt(task.getElementsByTagName("Duration").item(0).getTextContent());
                        List<Integer> dependencyList = new ArrayList<>();

                        NodeList dependencies = task.getElementsByTagName("DependsOnTaskID");
                        for (int k = 0; k < dependencies.getLength(); k++) {
                            dependencyList.add(Integer.parseInt(dependencies.item(k).getTextContent()));
                        }

                        Task tempTask = new Task(id, description, duration, dependencyList);
                        tasks.add(tempTask);
                    }

                    Project tempProject = new Project(element.getElementsByTagName("Name").item(0).getTextContent(), tasks);
                    projectList.add(tempProject);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


        return projectList;
    }


}
