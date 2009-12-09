package org.ponzao;

public class AStar {
    private final Grid grid;
    private final PriorityQueue<Node> open;
    private Grid gridWithRoute;
    private int totalAmountOfNodes = 0;

    public AStar(final Grid grid, final PriorityQueue<Node> open) {
        this.grid = grid;
        this.open = open;
    }

    // TODO Refactor
    public void findRoute() {
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

        // TODO refactor
        gridWithRoute = new Grid(grid.toString());
        Node curr = goal;
        while (curr != null) {
            ++totalAmountOfNodes;
            if (!curr.equals(start) && !curr.equals(goal)) {
                gridWithRoute.setNode(new Node('*', curr.getRow(), curr
                        .getColumn()), curr.getRow(), curr.getColumn());
            }
            curr = curr.getParent();
        }

        System.out
                .println("Nodes on route (start and goal calculated into this): "
                        + totalAmountOfNodes
                        + ", total cost: "
                        + goal.getCost());
        System.out.println();
        System.out
                .println("The route is highlighted below with '*' characters.");
        System.out.println(gridWithRoute);
        System.out.println();
    }

}
