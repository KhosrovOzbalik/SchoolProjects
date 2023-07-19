import java.io.Serializable;
import java.util.*;

public class RoutingTable implements Serializable {

    static final long serialVersionUID = 99L;
    private final Router router;
    private final Network network;
    private final List<RoutingTableEntry> entryList;

    public RoutingTable(Router router) {
        this.router = router;
        this.network = router.getNetwork();
        this.entryList = new ArrayList<>();
    }

    /**
     * updateTable() should calculate routing information and then instantiate RoutingTableEntry objects, and finally add
     * them to RoutingTable object’s entryList.
     */
    public void updateTable() {
        // TODO: YOUR CODE HERE
    }

    /**
     * pathTo(Router destination) should return a Stack<Link> object which contains a stack of Link objects,
     * which represents a valid path from the owner Router to the destination Router.
     *
     * @param destination Destination router
     * @return Stack of links on the path to the destination router
     */
    public Stack<Link> pathTo(Router destination) {
        // TODO: YOUR CODE
        List<Router> unvisited = new ArrayList<>();
        List<Router> visited = new ArrayList<>();

        for (Router router : network.getRouters()) {
            if (!router.isDown()) unvisited.add(router);
        }

        return recursive(router, unvisited, visited);
    }
    public Stack<Link> recursive(Router router, List<Router> unvisited, List<Router> visited) {
        /*
        visited.add(Link);
        unvisited.remove(Link);

        for (Router r : neighbors) {
            if (!visited.contains(Link)) {
                recursive(r, unvisited, visited);
            }
        }
        */
        return new Stack<Link>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutingTable that = (RoutingTable) o;
        return router.equals(that.router) && entryList.equals(that.entryList);
    }

    public List<RoutingTableEntry> getEntryList() {
        return entryList;
    }
}
