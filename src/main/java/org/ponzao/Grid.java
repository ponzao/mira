package org.ponzao;

public class Grid {
    private final Node grid[][];
    private Node start;
    private Node goal;
    private final int rows;
    private final int columns;
    private static final Double DIAGONAL_COST = Math.sqrt(2);
    private static final Double NORMAL_COST = 1.0;

    public Grid(final String stringGrid) {
        final String[] stringRows = stringGrid.split("\n");
        this.rows = stringRows.length;
        this.columns = stringRows[0].length();
        this.grid = new Node[rows][columns];
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                final Node node = new Node(stringRows[row].charAt(column), row,
                        column);
                if (stringRows[row].charAt(column) == 'S')
                    this.start = node;
                else if (stringRows[row].charAt(column) == 'G')
                    this.goal = node;
                setNode(node, row, column);
            }
        }
    }

    public void setNode(final Node node, final int row, final int column) {
        this.grid[row][column] = node;
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

    private int findEmptyIndex(final Node[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    public Node[] getNeighborsOf(final Node node) {
        final Node[] result = new Node[8];

        final int centerRow = node.getRow();
        final int centerColumn = node.getColumn();

        for (int row = centerRow - 1; row <= centerRow + 1; ++row) {
            for (int column = centerColumn - 1; column <= centerColumn + 1; ++column) {
                if ((row == centerRow && column == centerColumn)
                        || !isInGrid(row, column)
                        || grid[row][column].isBlocked()) {
                    continue;
                }
                final Node current = grid[row][column];
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
                current.setEstimatedDistance(current.calculateDistance(goal));
                current.setParent(node);
                result[findEmptyIndex(result)] = new Node(current);
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
