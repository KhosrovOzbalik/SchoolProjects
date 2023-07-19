import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubspaceCommunicationNetwork {

    private List<SolarSystem> solarSystems;

    /**
     * Perform initializations regarding your implementation if necessary
     * @param solarSystems a list of SolarSystem objects
     */
    public SubspaceCommunicationNetwork(List<SolarSystem> solarSystems) {
        // TODO: YOUR CODE HERE
        this.solarSystems = solarSystems;
    }

    /**
     * Using the solar systems of the network, generates a list of HyperChannel objects that constitute the minimum cost communication network.
     * @return A list HyperChannel objects that constitute the minimum cost communication network.
     */
    public List<HyperChannel> getMinimumCostCommunicationNetwork() {
        List<HyperChannel> minimumCostCommunicationNetwork = new ArrayList<>();
        // TODO: YOUR CODE HERE
        int biggest = 0;
        Planet bigPlanet = solarSystems.get(0).getPlanets().get(0);
        HashMap<Planet, Integer> planets = new HashMap<>();
        for (int i = 0; i < solarSystems.size(); i++) {
            int max = 0;
            Planet p = solarSystems.get(i).getPlanets().get(0);
            for (Planet planet : solarSystems.get(i).getPlanets()) {
                if (planet.getTechnologyLevel() > max) {
                    max = planet.getTechnologyLevel();
                    p = planet;
                }
            }
            planets.put(p, max);
            if (max > biggest) {
                biggest = max;
                bigPlanet = p;
            }
        }
        for (Planet planet : planets.keySet()) {
            if (planet != bigPlanet) {
                HyperChannel tempChannel = new HyperChannel(planet, bigPlanet, ((144.5)/((planet.getTechnologyLevel() + bigPlanet.getTechnologyLevel())/2d)));
                minimumCostCommunicationNetwork.add(tempChannel);
            }
        }

        return minimumCostCommunicationNetwork;
    }

    public void printMinimumCostCommunicationNetwork(List<HyperChannel> network) {
        double sum = 0;
        for (HyperChannel channel : network) {
            Planet[] planets = {channel.getFrom(), channel.getTo()};
            Arrays.sort(planets);
            System.out.printf("Hyperchannel between %s - %s with cost %f", planets[0], planets[1], channel.getWeight());
            System.out.println();
            sum += channel.getWeight();
        }
        System.out.printf("The total cost of the subspace communication network is %f.", sum);
        System.out.println();
    }
}
