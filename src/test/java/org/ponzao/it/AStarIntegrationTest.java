package org.ponzao.it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.ponzao.AStar;
import org.ponzao.BinaryHeap;
import org.ponzao.Grid;
import org.ponzao.Node;
import org.ponzao.Route;

// TODO Sensible names for tests?
public class AStarIntegrationTest {
    @Test
    public void test1() {
        final AStar as = new AStar(new Grid("SG\n"), new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(1.0, route.getTotalCost(), 0);
        assertEquals(2, route.getNodes());
        assertEquals("SG\n", route.getRoute());
    }

    @Test
    public void test2() {
        final AStar as = new AStar(new Grid("S.G\n"), new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(2.0, route.getTotalCost(), 0);
        assertEquals(3, route.getNodes());
        assertEquals("S*G\n", route.getRoute());
    }

    @Test
    public void test3() {
        final AStar as = new AStar(new Grid("S.\n.G\n"), new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(Math.sqrt(2), route.getTotalCost(), 0);
        assertEquals(2, route.getNodes());
        assertEquals("S.\n.G\n", route.getRoute());
    }

    @Test
    public void test4() {
        final AStar as = new AStar(new Grid("S..\n...\n..G\n"),
                new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(Math.sqrt(2) * 2, route.getTotalCost(), 0);
        assertEquals(3, route.getNodes());
        assertEquals("S..\n.*.\n..G\n", route.getRoute());
    }

    @Test
    public void test5() {
        final AStar as = new AStar(new Grid("S..\n##.\nG..\n"),
                new BinaryHeap<Node>());
        final Route route = as.findRoute();
        assertEquals(6.0, route.getTotalCost(), 0);
        assertEquals(7, route.getNodes());
        assertEquals("S**\n##*\nG**\n", route.getRoute());
    }

}
