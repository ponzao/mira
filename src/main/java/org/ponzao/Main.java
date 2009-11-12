package org.ponzao;

public class Main {
    private static class Node implements Comparable<Node> {
        private final char c;
        private final int column;
        private final int row;
        private Double cost;
        private Double estimatedDistance;
        private Node parent;
        private boolean closed;

        public Node(final Node other) {
            this.c = other.c;
            this.column = other.column;
            this.row = other.row;
            this.cost = other.cost;
            this.estimatedDistance = other.estimatedDistance;
            this.parent = other.parent;
        }

        public Node(final char c, final int row, final int column) {
            this.c = c;
            this.column = column;
            this.row = row;
        }

        @Override
        public String toString() {
            return "" + this.c + " column: " + column + " row: " + row;
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

        public void setCost(Double cost) {
            this.cost = cost;
        }

        public Double getCost() {
            return cost;
        }

        public void setEstimatedDistance(Double estimatedDistance) {
            this.estimatedDistance = estimatedDistance;
        }

        public Double calculateDistance(final Node other) {
            return Math.sqrt(Math.pow(other.column - this.column, 2)
                    + Math.pow(other.row - this.row, 2));
        }

        @Override
        public int compareTo(Node o) {
            final Double thisSum = estimatedDistance + cost;
            final Double thatSum = o.estimatedDistance + o.cost;
            return thisSum < thatSum ? -1 : thisSum > thatSum ? 1 : 0;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + c;
            result = prime * result + column;
            result = prime * result + row;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (c != other.c)
                return false;
            if (column != other.column)
                return false;
            if (row != other.row)
                return false;
            return true;
        }

        public void setClosed(boolean closed) {
            this.closed = closed;
        }

        public boolean isClosed() {
            return closed;
        }

    }

    private static class Grid {
        private final Node grid[][];
        private Node start;
        private Node goal;
        private final int rows;
        private final int columns;
        private static final Double DIAGONAL_COST = Math.sqrt(2);
        private static final Double NORMAL_COST = 1.0;

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

        public Node getNode(final int column, final int row) {
            return grid[row][column];
        }

        private boolean isInGrid(final int row, final int column) {
            return 0 <= row && 0 <= column && column < columns && row < rows;
        }

        private int findEmptyIndex(final Node[] array) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == null) {
                    return i;
                }
            }
            throw new RuntimeException();
        }

        public Node[] neighbors(final Node node) {
            final Node[] result = new Node[8];

            final int centerRow = node.getRow();
            final int centerColumn = node.getColumn();

            for (int row = centerRow - 1; row <= centerRow + 1; ++row) {
                for (int column = centerColumn - 1; column <= centerColumn + 1; ++column) {
                    if ((row == centerRow && column == centerColumn)
                            || !isInGrid(row, column)
                            || grid[row][column].isBlocked() || grid[row][column].isClosed()) {
                        continue;
                    }
                    final Node current = new Node(grid[row][column]);
                    final double cost = (row != centerRow && column != centerColumn) ? DIAGONAL_COST
                            : NORMAL_COST;
                    if (current.getCost() == null) {
                        // new node
                        current.setCost(cost + node.getCost());
                    } else if (cost + node.getCost() < current.getCost()) {
                        // improved cost
                        current.setCost(cost + node.getCost());
                    } else {
                        // already visited and cost not improved
                        continue;
                    }
                    current.setEstimatedDistance(current
                            .calculateDistance(goal));
                    current.setParent(node);
                    result[findEmptyIndex(result)] = current;
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
                { '#', '#', '.', '.', '#', '.', '.', '.' },
                { 'S', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '.', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '.', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '.', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '.', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '.', '#', '#', '.', '.', '.' },
                { '.', '#', '.', '#', '.', '.', '.', '.' },
                { '.', '.', '.', '#', '.', '.', '.', '.' },
                { '.', '#', '.', '#', '#', '.', '.', '.' },
                { '.', '#', '.', '.', '#', '.', '.', '.' },
                { '.', '#', '#', '.', '#', '.', '.', '.' },
                { '.', '.', '#', '#', '#', '.', 'G', '.' } });

        showRoute(findRouteToGoal(grid));
    }

    private static void showRoute(final Node goal) {
        System.out.println("Route from goal to start, indexed from 1.");
        Node current = goal;
        while (current != null) {
            System.out.println("Column: " + (current.getColumn() + 1)
                    + ", Row: " + (1 + current.getRow()));
            current = current.getParent();
        }
    }

    private static Node findRouteToGoal(final Grid grid) {
        final PriorityQueue<Node> open = new BinaryHeap<Node>();
        final Node start = grid.getStart();
        final Node goal = grid.getGoal();
        start.setCost(0.0);
        for (Node node : grid.neighbors(start)) {
            if (node != null) {
                open.add(node);
            }
        }
        while (true) {
            if (open.peek().equals(goal)) {
                return open.peek();
            }
            final Node bestNode = open.remove();
            grid.getNode(bestNode.getColumn(), bestNode.getRow()).setClosed(
                    true);
            for (Node node : grid.neighbors(bestNode)) {
                if (node != null) {
                    open.add(node);
                }
            }
        }
    }
}
