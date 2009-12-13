package org.ponzao;

public class Route {
    private final double totalCost;
    private final int nodes;
    private final String route;

    public Route(final double totalCost, final int nodes, final String route) {
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

    public String getRoute() {
        return route;
    }
}
