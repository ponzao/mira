package org.ponzao;

/**
 * A*-shortest route algorithm.
 * 
 * @author vesa
 * 
 */
public class AStar {
    private final Grid grid;
    private final PriorityQueue<Node> open;

    /**
     * Grid is the map that is used for searching. The queue is for the open
     * nodes.
     * 
     */
    public AStar(final Grid grid, final PriorityQueue<Node> open) {
        this.grid = grid;
        this.open = open;
    }

    /**
     * Finds the route from start to goal, given in the original grid.
     */
    public Route findRoute() {
        final Node start = grid.getStart();
        final Node goal = grid.getGoal();

        Node current = start;
        while (true) {
            if (current.equals(goal)) {
                // We've reached the goal.
                break;
            }
            for (Node node : grid
                    .getAccessibleNeighborsWithValuesUpdatedOf(current)) {
                // The method returns an array of length 8 (max possible
                // neighbors). It might be a good idea in the future iterations
                // to implement this as a stack.
                if (node != null) {
                    open.add(node);
                }
            }
            // No nodes left to inspect.
            if (open.size() == 0) {
                throw new RuntimeException(
                        "There is no route from start to goal in the given map!");
            }
            // Let's take the best possible node for inspection next.
            current = open.remove();
        }
        return createResultRoute(start, goal);
    }

    /**
     * Creates a Route result object in which the route's data is stored (route
     * cost, route visualized and amount of nodes on route).
     */
    private Route createResultRoute(final Node start, final Node goal) {
        final Grid gridWithRoute = new Grid(grid.asString());
        Node curr = goal;
        int totalAmountOfNodes = 0;
        while (curr != null) {
            ++totalAmountOfNodes;
            if (!curr.equals(start) && !curr.equals(goal)) {
                gridWithRoute.setNode(new Node('*', curr.getRow(), curr
                        .getColumn()), curr.getRow(), curr.getColumn());
            }
            curr = curr.getParent();
        }

        return new Route(goal.getCost(), totalAmountOfNodes, gridWithRoute
                .asString());
    }

}
