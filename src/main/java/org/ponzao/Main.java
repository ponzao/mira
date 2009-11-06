package org.ponzao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static class Node implements Comparable<Node> {
        private final char c;
        private final int column;
        private final int row;
        private Integer cost;
        private Integer manhattanDistance;
        private Node parent;

        public Node(final char c, final int row, final int column) {
            this.c = c;
            this.column = column;
            this.row = row;
        }

        @Override
        public String toString() {
            return "" + this.c;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public boolean isBlocked() {
            return c == '#';
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }

        public void setCost(Integer cost) {
            this.cost = cost;
        }

        public Integer getCost() {
            return cost;
        }

        public void setManhattanDistance(Integer manhattanDistance) {
            this.manhattanDistance = manhattanDistance;
        }

        public Integer getManhattanDistance() {
            return manhattanDistance;
        }

        public int calculateDistance(final Node other) {
            int result = 0;
            result = result + Math.abs(this.column - other.column);
            result = result + Math.abs(this.row - other.row);
            return 10 * result;
        }

        @Override
        public int compareTo(Node o) {
            final int thisSum = manhattanDistance + cost;
            final int thatSum = o.getManhattanDistance() + o.getCost();
            return thisSum < thatSum ? -1 : thisSum > thatSum ? 1 : 0;
        }

    }

    private static class Grid {
        private final Node grid[][];
        private Node start;
        private Node goal;
        private final int rows;
        private final int columns;

        public Grid(final char[][] charGrid) {
            this.rows = charGrid.length;
            this.columns = charGrid[0].length;
            this.grid = new Node[rows][columns];
            for (int row = 0; row < rows; ++row) {
                for (int column = 0; column < columns; ++column) {
                    final Node node = new Node(charGrid[row][column], row,
                            column);
                    if (charGrid[row][column] == 'S')
                        this.start = node;
                    else if (charGrid[row][column] == 'G')
                        this.goal = node;
                    this.grid[row][column] = node;
                }
            }
        }

        public Node getStart() {
            return start;
        }

        public Node getGoal() {
            return goal;
        }

        private boolean isInGrid(final int row, final int column) {
            return 0 <= row && 0 <= column && column < columns && row < rows;
        }

        // TODO Honestly, wtf :D
        public List<Node> accessibleNeighbors(final Node node) {
            final List<Node> result = new ArrayList<Node>();
            final int origRow = node.getRow();
            final int origColumn = node.getColumn();
            for (int row = origRow - 1; row <= origRow + 1; ++row) {
                for (int column = origColumn - 1; column <= origColumn + 1; ++column) {
                    if (!(row == origRow && column == origColumn)
                            && isInGrid(row, column)
                            && !grid[row][column].isBlocked()) {
                        final Node current = grid[row][column];
                        if (row != origRow && column != origColumn) {
                            if (current.getCost() == null) {
                                current.setCost(14 + node.getCost());
                            } else if (current.getCost() != null
                                    && (14 + node.getCost()) < current
                                            .getCost()) {
                                current.setCost(14 + node.getCost());
                            } else {
                                continue;
                            }
                        } else {
                            if (current.getCost() == null) {
                                current.setCost(10 + node.getCost());
                            } else if ((10 + node.getCost()) < current
                                    .getCost()) {
                                current.setCost(10 + node.getCost());
                            } else {
                                continue;
                            }
                        }
                        current.setManhattanDistance(current
                                .calculateDistance(goal));
                        current.setParent(node);
                        result.add(current);
                    }
                }
            }
            return result;
        }

        @Override
        public String toString() {
            String s = "";
            for (int row = 0; row < rows; ++row) {
                for (int column = 0; column < columns; ++column) {
                    s = s + this.grid[row][column];
                }
                s = s + "\n";
            }
            return s;
        }
    }

    public static void main(String args[]) {
        final Grid grid = new Grid(new char[][] {
                { '.', '.', '.', '.', '.', '.', '.' },
                { '#', '#', '.', '#', '.', '.', '.' },
                { 'S', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '#', '.', '.', '.' },
                { '.', '.', '.', '#', '.', '.', 'G' }, });

        showRoute(findRouteToGoal(grid));
    }

    private static void showRoute(final Node goal) {
        System.out.println("Route from goal to start, indexed from 1.");
        Node current = goal;
        while (current != null) {
            System.out.println("Column: " + (current.getColumn() + 1) + ", Row: "
                    + (1 + current.getRow()));
            current = current.getParent();
        }
    }

    private static Node findRouteToGoal(final Grid grid) {
        final List<Node> open = new ArrayList<Node>();
        final Node start = grid.getStart();
        final Node goal = grid.getGoal();
        start.setCost(0);
        open.add(start);
        final List<Node> closed = new ArrayList<Node>();
        open.addAll(grid.accessibleNeighbors(start));
        open.remove(start);
        Collections.sort(open);
        closed.add(start);

        while (true) {
            final Node bestNode = open.get(0);
            open.addAll(grid.accessibleNeighbors(bestNode));
            if (open.contains(goal)) {
                return goal;
            }
            open.remove(bestNode);
            Collections.sort(open);
            closed.add(bestNode);
        }

    }

}
