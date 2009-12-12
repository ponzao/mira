package org.ponzao;

public class AStar {
    private final Grid grid;
    private final PriorityQueue<Node> open;

    public AStar(final Grid grid, final PriorityQueue<Node> open) {
        this.grid = grid;
        this.open = open;
    }

    // TODO Refactor
    public Route findRoute() {
        final Node start = grid.getStart();
        final Node goal = grid.getGoal();

        Node current = start;
        while (true) {
            if (current.equals(goal)) {
                break;
            }
            for (Node node : grid
                    .getAccessibleNeighborsWithValuesUpdatedOf(current)) {
                if (node != null) {
                    open.add(node);
                }
            }
            if (open.size() == 0) {
                // TODO Replace me with a custom Exception
                throw new RuntimeException(
                        "There is no route from start to goal in the given map!");
            }
            current = open.remove();
        }

        final Grid gridWithRoute = new Grid(grid.toString());
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

        return new Route(goal.getCost(), totalAmountOfNodes, gridWithRoute);
    }

}
