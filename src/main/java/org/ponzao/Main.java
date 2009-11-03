package org.ponzao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    private static class Node {
        private final char c;
        private final int x;
        private final int y;
        private Node parent;

        public Node(final char c, final int x, final int y) {
            this.c = c;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "" + this.c;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public boolean isBlocked() {
            return c == '#';
        }

        public void setParent(Node parent) {
            this.parent = parent;
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
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    final Node node = new Node(charGrid[i][j], i, j);
                    if (charGrid[i][j] == 'S')
                        this.start = node;
                    else if (charGrid[i][j] == 'G')
                        this.goal = node;
                    this.grid[i][j] = node;
                }
            }
        }

        public Node getStart() {
            return start;
        }

        public Node getGoal() {
            return goal;
        }

        public boolean isInGrid(final int row, final int column) {
            return 0 <= row && 0 <= column && column < columns && row < rows;
        }

        public Collection<Node> accessibleNeighbors(final Node node) {
            final Collection<Node> result = new ArrayList<Node>();
            final int x = node.getX();
            final int y = node.getY();
            for (int row = x - 1; row <= x + 1; ++row) {
                for (int column = y - 1; column <= y + 1; ++column) {
                    if (!(row == x && column == y) && isInGrid(row, column)
                            && !grid[row][column].isBlocked())
                        grid[row][column].setParent(node);
                    result.add(grid[row][column]);
                }
            }
            return result;
        }

        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    s = s + this.grid[i][j];
                }
                s = s + "\n";
            }
            return s;
        }
    }

    public static void main(String args[]) {
        final Grid grid = new Grid(new char[][] {
                { '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '#', '.', '.', '.' },
                { '.', 'S', '.', '#', '.', 'G', '.' },
                { '.', '.', '.', '#', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.' }, });
        final List<Node> open = new ArrayList<Node>();
        final Node start = grid.getStart();
        open.add(start);
        final List<Node> closed = new ArrayList<Node>();
        open.addAll(grid.accessibleNeighbors(start));
        open.remove(start);
        closed.add(start);
        System.out.println(open.size());
    }
}
