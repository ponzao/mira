package org.ponzao;

// FIXME whole method really hacky
public class Main {
    public static void main(String args[]) {
        final Grid grid = new Grid(new InputParser().readInput());
        final Grid routeGrid = new Grid(grid.toString());
        showRoute(findRouteToGoal(grid), routeGrid);
    }

    private static void showRoute(final Node goal, final Grid grid) {
        System.out.println("Route from goal to start, indexed from 1:");
        Node current = goal;
        while (current != null) {
            System.out.println("Column: " + (current.getColumn() + 1)
                    + ", Row: " + (1 + current.getRow()));
            
            grid.setNode(new Node('*', current.getRow(), current.getColumn()),
                    current.getRow(), current.getColumn());
            current = current.getParent();
        }

        System.out.println("Route shown on map:");
        System.out.println(grid);
    }

    private static Node findRouteToGoal(final Grid grid) {
        final PriorityQueue<Node> open = new BinaryHeap<Node>();
        final Node start = grid.getStart();
        final Node goal = grid.getGoal();
        start.setCost(0.0);
        for (Node node : grid.getNeighborsOf(start)) {
            if (node != null) {
                open.add(node);
            }
        }
        while (true) {
            if (open.peek().equals(goal)) {
                return open.peek();
            }
            final Node bestNode = open.remove();
            // FIXME The following is stupid, it would make more sense to add
            // them in the collection straight away.
            open.add(grid.getNeighborsOf(bestNode));
        }
    }
}
