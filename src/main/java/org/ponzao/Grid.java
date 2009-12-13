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

    public boolean isInGrid(final int row, final int column) {
        return 0 <= row && 0 <= column && column < columns && row < rows;
    }

    // TODO use this is getAcces.... TODO bad name, isMovable? is it really a
    // bad name? TODO separate the methods
    public boolean isAccessible(final int fromRow, final int fromColumn,
            final int toRow, final int toColumn) {
        // Bottom right case
        if (fromRow + 1 == toRow
                && fromColumn + 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked())) {
            return false;
        }
        // Bottom left case
        if (fromRow + 1 == toRow
                && fromColumn - 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked())) {
            return false;
        }
        // Top left case
        if (fromRow - 1 == toRow
                && fromColumn - 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked())) {
            return false;
        }
        // Top right case
        if (fromRow - 1 == toRow
                && fromColumn + 1 == toColumn
                && (grid[fromRow][toColumn].isBlocked() || grid[toRow][fromColumn]
                        .isBlocked())) {
            return false;
        }
        return true;
    }

    private int findEmptyIndex(final Node[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    // TODO UNIT TESTS
    // FIXME I am a "bit" ugly
    public Node[] getAccessibleNeighborsWithValuesUpdatedOf(final Node node) {
        final Node[] result = new Node[8];

        final int centerRow = node.getRow();
        final int centerColumn = node.getColumn();

        for (int row = centerRow - 1; row <= centerRow + 1; ++row) {
            for (int column = centerColumn - 1; column <= centerColumn + 1; ++column) {
                if ((row == centerRow && column == centerColumn)
                        || !isInGrid(row, column)
                        || grid[row][column].isBlocked()
                        || !isAccessible(centerRow, centerColumn, row, column)) {
                    continue;
                }
                final Node current = grid[row][column];
                final double cost = (row != centerRow && column != centerColumn) ? DIAGONAL_COST
                        : NORMAL_COST;
                if (current.getCost() == null) {
                    // unvisited node
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
