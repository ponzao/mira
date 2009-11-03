package org.ponzao;

public class Grid {
    private final Node[][] grid;

    /**
     * Creates an empty grid based on the given values.
     * 
     * @param rows
     * @param columns
     */
    public Grid(final int rows, final int columns) {
        this.grid = new Node[rows][columns];
        for (int row = 0; row < grid.length; ++row) {
            for (int column = 0; column < grid[row].length; ++column) {
                grid[row][column] = Node.EMPTY;
            }
        }
    }

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
                grid[row][column] = new Node(stringRows[row].charAt(column));
            }
        }
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

    private static class Node {
        private final char c;
        private static final Node EMPTY = new Node('.');
        private static final Node BLOCKADE = new Node('#');
        private static final Node START = new Node('S');
        private static final Node GOAL = new Node('G');

        public Node(final char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "" + this.c;
        }
    }
}
