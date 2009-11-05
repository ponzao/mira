package org.ponzao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static class Node implements Comparable<Node> {
        private final char c;
        private final int column;
        private final int row;
        private Integer g;
        private Integer h;
        private Integer f;
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

        public void setG(Integer g) {
            this.g = g;
        }

        public Integer getG() {
            return g;
        }

        public void setH(Integer h) {
            this.h = h;
        }

        public Integer getH() {
            return h;
        }

        public int calculateDistance(final Node other) {
            int result = 0;
            result = result + Math.abs(this.column - other.column);
            result = result + Math.abs(this.row - other.row);
            return 10 * result;
        }

        public void setF(Integer f) {
            this.f = f;
        }

        public Integer getF() {
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
                    if (!(row == origRow && column == origColumn) && isInGrid(row, column)
                            && !grid[row][column].isBlocked()) {
                        final Node current = grid[row][column];
                        if (current == goal) {
                            System.out.println("KICKASS");
                            System.out.println(1 + origColumn + " " + (1 + origRow));
                        }
                        if (row != origRow && column != origColumn) {
                            if (current.getG() == null) {
                                current.setG(14 + node.getG());
                            } else if (current.getG() != null
                                    && (14 + node.getG()) < current.getG()) {
                                current.setG(14 + node.getG());
                            } else {
                                continue;
                            }
                        } else {
                            if (current.getG() == null) {
                                current.setG(10 + node.getG());
                            } else if ((10 + node.getG()) < current.getG()) {
                                current.setG(10 + node.getG());
                            } else {
                                continue;
                            }
                        }
                        current.setH(current.calculateDistance(goal));
                        current.setF(current.getH() + current.getG());
                        current.setParent(node);
                        result.add(current);
                    }
                }
            }
            return result;
        }

        public Node get(final int column, final int row) {
            return grid[row][column];
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
                { '.', '.', '.', '#', '.', '.', '.' },
                { '.', 'S', '.', '#', '.', '.', 'G' },
                { '.', '.', '.', '#', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.' }, });
        final List<Node> open = new ArrayList<Node>();
        final Node start = grid.getStart();
        start.setG(0);
        open.add(start);
        final List<Node> closed = new ArrayList<Node>();
        open.addAll(grid.accessibleNeighbors(start));
        open.remove(start);
        Collections.sort(open);
        closed.add(start);

        System.out.println();
        Node bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        System.out.println();
        bestNode = open.get(0);
        open.addAll(grid.accessibleNeighbors(bestNode));
        open.remove(bestNode);
        Collections.sort(open);
        closed.add(bestNode);
        
        


    }

}
