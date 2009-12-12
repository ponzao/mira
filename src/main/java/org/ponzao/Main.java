package org.ponzao;

public class Main {
    public static void main(String args[]) {
        final AStar as = new AStar(new Grid(new InputParser().readInput()),
                new BinaryHeap<Node>());
        final Route route = as.findRoute();

        System.out
                .println("Nodes on route (start and goal calculated into this): "
                        + route.getNodes()
                        + ", total cost: "
                        + route.getTotalCost());
        System.out.println();
        System.out
                .println("The route is highlighted below with '*' characters.");
        System.out.println(route.getRoute());
    }
}
