package org.ponzao;

public class Node implements Comparable<Node> {
    private final char c;
    private final int column;
    private final int row;
    private Double cost;
    private Double estimatedDistance;
    private Node parent;

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

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCost() {
        return cost;
    }

    public void setEstimatedDistance(final Double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public Double calculateDistance(final Node other) {
        return Math.sqrt(Math.pow(other.column - this.column, 2)
                + Math.pow(other.row - this.row, 2));
    }

    @Override
    public int compareTo(final Node o) {
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

}
