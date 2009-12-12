package org.ponzao;

public class Route {
    private final double totalCost;
    private final int nodes;
    private final Grid route;

    public Route(final double totalCost, final int nodes, final Grid route) {
        this.totalCost = totalCost;
        this.nodes = nodes;
        this.route = route;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getNodes() {
        return nodes;
    }

    public Grid getRoute() {
        return route;
    }
}
