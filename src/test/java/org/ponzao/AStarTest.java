package org.ponzao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AStarTest {
    private AStar aStar;
    
    @Test
    public void test_findShortestRoute() {
        aStar = new AStar(new Grid("S#\n..\n#G\n"));
        final Grid grid = aStar.findShortestRoute();
        assertEquals("S#\n**\n#G\n", grid.toString());
    }
}
