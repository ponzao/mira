package org.ponzao;

public class Main {
    public static void main(String args[]) {
        final AStar as = new AStar(new Grid(new InputParser().readInput()),
                new BinaryHeap<Node>());
        as.findRoute();
    }
}
