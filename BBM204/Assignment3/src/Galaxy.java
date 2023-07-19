import java.util.*;

public class Galaxy {

    private final List<Planet> planets;
    private List<SolarSystem> solarSystems;

    public Galaxy(List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Using the galaxy's list of Planet objects, explores all the solar systems in the galaxy.
     * Saves the result to the solarSystems instance variable and returns a shallow copy of it.
     *
     * @return List of SolarSystem objects.
     */
    class planetNode {
        Planet planet;
        boolean visited = false;

        List<planetNode> neigbors = new ArrayList<>();
        public void setPlanet(Planet planet) {
            this.planet = planet;
        }
    }
    public List<SolarSystem> exploreSolarSystems() {
        solarSystems = new ArrayList<>();
        // TODO: YOUR CODE HERE
        HashMap<String, planetNode> dict = new HashMap<>();
        for (int i = 0; i < planets.size(); i++) {
            String id = planets.get(i).getId();
            if (!dict.containsKey(id)) dict.put(id, new planetNode());
            dict.get(id).setPlanet(planets.get(i));
            for (String n : dict.get(id).planet.getNeighbors()) {
                if (!dict.containsKey(n)) dict.put(n, new planetNode());
                dict.get(id).neigbors.add(dict.get(n));
                if (!dict.get(n).neigbors.contains(dict.get(id))) dict.get(n).neigbors.add(dict.get(id));
            }
        }

        for (Planet planet : planets) {
            if (!dict.get(planet.getId()).visited) {
                SolarSystem tempSolar = new SolarSystem();
                bfs(dict.get(planet.getId()), tempSolar);
                solarSystems.add(tempSolar);
            }
        }
        return new ArrayList<>(solarSystems);
    }

    public void bfs(planetNode head, SolarSystem solarSystem) {
        solarSystem.addPlanet(head.planet);
        head.visited = true;
        for (planetNode neigbor : head.neigbors) {
            if (!neigbor.visited) bfs(neigbor,solarSystem);
        }
    }

    public List<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    // FOR TESTING
    public List<Planet> getPlanets() {
        return planets;
    }

    // FOR TESTING
    public int getSolarSystemIndexByPlanetID(String planetId) {
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            if (solarSystem.hasPlanet(planetId)) {
                return i;
            }
        }
        return -1;
    }

    public void printSolarSystems(List<SolarSystem> solarSystems) {
        System.out.printf("%d solar systems have been discovered.%n", solarSystems.size());
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            List<Planet> planets = new ArrayList<>(solarSystem.getPlanets());
            Collections.sort(planets);
            System.out.printf("Planets in Solar System %d: %s", i + 1, planets);
            System.out.println();
        }
    }
}
