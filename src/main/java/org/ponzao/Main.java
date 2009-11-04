package org.ponzao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static class Node implements Comparable<Node> {
        private final char c;
        private final int x;
        private final int y;
        private int g;
        private int h;
        private int f;
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

        public Node getParent() {
            return parent;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getG() {
            return g;
        }

        public void setH(int h) {
            this.h = h;
        }

        public int getH() {
            return h;
        }

        public int calculateDistance(final Node other) {
            int result = 0;
            result = result + Math.abs(this.x - other.x);
            result = result + Math.abs(this.y - other.y);
            return 10 * result;
        }

        public void setF(int f) {
            this.f = f;
        }

        public int getF() {
            return f;
        }

        @Override
        public int compareTo(Node o) {
            return this.f < o.f ? -1 : f > o.f ? 1 : 0;
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

        public List<Node> accessibleNeighbors(final Node node) {
            final List<Node> result = new ArrayList<Node>();
            final int x = node.getX();
            final int y = node.getY();
            for (int row = x - 1; row <= x + 1; ++row) {
                for (int column = y - 1; column <= y + 1; ++column) {
                    if (!(row == x && column == y) && isInGrid(row, column)
                            && !grid[row][column].isBlocked()) {
                        final Node current = grid[row][column];
                        current.setParent(node);
                        if (row != x && column != y) {
                            current.setG(14 + current.getParent().getG());
                        } else {
                            current.setG(10 + current.getParent().getG());
                        }
                        current.setH(current.calculateDistance(goal));
                        current.setF(current.getH() + current.getG());
                        System.out.println(current.getF());
                        current.setParent(node);
                        result.add(current);
                    }
                }
            }
            Collections.sort(result);
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
        start.setG(0);
        open.add(start);
        final List<Node> closed = new ArrayList<Node>();
        open.addAll(grid.accessibleNeighbors(start));
        open.remove(start);
        closed.add(start);
    }
}
