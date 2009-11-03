package org.ponzao;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static class Node {
        private final char c;
        private final int x;
        private final int y;
        private Node parent;
        private Node[] children;

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
    }

    private static class Grid {
        private final Node grid[][];
        private Node start;
        private Node goal;

        public Grid(final char[][] charGrid) {
            this.grid = new Node[charGrid.length][charGrid[0].length];
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
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
        List<Node> open = new ArrayList<Node>();
        open.add(grid.getStart());
    }
}
