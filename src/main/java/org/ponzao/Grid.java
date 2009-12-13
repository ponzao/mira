package org.ponzao;

public class Grid {
    private final Node grid[][];
    private Node start;
    private Node goal;
    private final int rows;
    private final int columns;
    private static final Double DIAGONAL_COST = Math.sqrt(2);
    private static final Double NORMAL_COST = 1.0;

    /**
     * Creates a Grid object from the given string parameter. If there is no
     * start or goal node in the parameter it will thrown an exception, or if
     * the string is empty.
     */
    public Grid(final String stringGrid) {
        if (stringGrid.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot construct a grid from an empty string!");
        }
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
        if (start == null || goal == null) {
            throw new IllegalArgumentException(
                    "String parameter didn't contain a start and/or goal node!");
        }
        // Start node's cost from itself is of course 0.
        start.setCost(0.0);
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

    /**
     * Checks that a point is actually in the grid and not out of bounds.
     */
    public boolean isInGrid(final int row, final int column) {
        return 0 <= row && 0 <= column && column < columns && row < rows;
    }

    /**
     * This and the following methods are helper methods used in isAccessible to
     * check that no cheating is allowed aka. skipping corners.
     */
    private boolean isBottomRightBlocked(final int fromRow,
            final int fromColumn, final int toRow, final int toColumn) {
        return fromRow + 1 == toRow
                && fromColumn + 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked());
    }

    private boolean isBottomLeftBlocked(final int fromRow,
            final int fromColumn, final int toRow, final int toColumn) {
        return fromRow + 1 == toRow
                && fromColumn - 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked());
    }

    private boolean isTopLeftBlocked(final int fromRow, final int fromColumn,
            final int toRow, final int toColumn) {
        return fromRow - 1 == toRow
                && fromColumn - 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked());
    }

    private boolean isTopRightBlocked(final int fromRow, final int fromColumn,
            final int toRow, final int toColumn) {
        return fromRow - 1 == toRow
                && fromColumn + 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked());
    }

    /**
     * Verifies that a point is accessible from the current position. If it has
     * a block on either side of it then it is not.
     */
    public boolean isAccessible(final int fromRow, final int fromColumn,
            final int toRow, final int toColumn) {
        if (isBottomRightBlocked(fromRow, fromColumn, toRow, toColumn)
                || isBottomLeftBlocked(fromRow, fromColumn, toRow, toColumn)
                || isTopLeftBlocked(fromRow, fromColumn, toRow, toColumn)
                || isTopRightBlocked(fromRow, fromColumn, toRow, toColumn)) {
            return false;
        }
        return true;
    }

    /**
     * Helper method in finding an empty index in the array. TODO Honestly
     * implement this as a stack.
     */
    private int findEmptyIndex(final Node[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    /**
     * Returns the accessible neighbors of a node. Also it updates their value
     * if there is reason to do that.
     */
    public Node[] getAccessibleNeighborsWithValuesUpdatedOf(final Node node) {
        // TODO This as a stack.
        final Node[] result = new Node[8];

        final int centerRow = node.getRow();
        final int centerColumn = node.getColumn();

        // Iterates through the node's neighboring rows.
        for (int row = centerRow - 1; row <= centerRow + 1; ++row) {
            for (int column = centerColumn - 1; column <= centerColumn + 1; ++column) {
                // If the node is not a neighbor for some reason the we iterate
                // again.
                if ((row == centerRow && column == centerColumn)
                        || !isInGrid(row, column)
                        || grid[row][column].isBlocked()
                        || !isAccessible(centerRow, centerColumn, row, column)) {
                    continue;
                }
                final Node current = grid[row][column];
                // If the current node is diagonally aligned from our center
                // node then the cost is diagonal (sqrt(2)), otherwise normal
                // (1).
                final double cost = (row != centerRow && column != centerColumn) ? DIAGONAL_COST
                        : NORMAL_COST;
                if (current.getCost() == null) {
                    // Unvisited node.
                    current.setCost(cost + node.getCost());
                } else if (cost + node.getCost() < current.getCost()) {
                    // Improved cost.
                    current.setCost(cost + node.getCost());
                } else {
                    // Already visited and cost not improved.
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
        final StringBuilder sb = new StringBuilder();
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                sb.append(this.grid[row][column]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Just calls toString, this is done due to problems with PowerMock
     * overriding the toString presentation thus not allowing me to mock that
     * call.
     * 
     */
    public String asString() {
        return this.toString();
    }
}
