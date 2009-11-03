package org.ponzao;

public class Grid {
    private final int nodes;
    private final Node[][] grid;
    private Node start;
    private Node goal;

    /**
     * Creates a grid based on the given String.
     * 
     * @param string
     */
    public Grid(final String s) {
        final String[] stringRows = s.split("\n");
        final int rows = stringRows.length;
        final int columns = stringRows[0].length();
        this.grid = new Node[rows][columns];
        for (int row = 0; row < grid.length; ++row) {
            for (int column = 0; column < grid[row].length; ++column) {
                final Node node = new Node(stringRows[row].charAt(column), row,
                        column);
                if (node.equals(Node.START))
                    this.start = node;
                else if (node.equals(Node.GOAL))
                    this.goal = node;
                grid[row][column] = node;
            }
        }
        this.nodes = rows + columns;
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }

    public int getNodeCount() {
        return nodes;
    }

    @Override
    public String toString() {
        String s = "";
        for (Node[] row : grid) {
            for (Node node : row) {
                s = s + node;
            }
            s = s + '\n';
        }
        return s;
    }

    public static class Node {
        private final char c;
        private int row;
        private int column;
        private boolean visited;
        private Node parent;
        private Node[] children;

        private static final Node EMPTY = new Node('.');
        private static final Node BLOCKADE = new Node('#');
        private static final Node START = new Node('S');
        private static final Node GOAL = new Node('G');

        public Node(final char c) {
            this.c = c;
            this.visited = false;
        }

        public Node(final char c, final int row, final int column) {
            this(c);
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + c;
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
            return true;
        }

        @Override
        public String toString() {
            return "" + this.c;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }

        public void setChildren(Node[] children) {
            this.children = children;
        }

        public Node[] getChildren() {
            return children;
        }
    }
}
