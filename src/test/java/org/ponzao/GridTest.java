package org.ponzao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GridTest {
    private Grid grid;

    @Test
    public void test_Grid_With_Predefined_Values() {
        grid = new Grid("S#\n..\n#G\n");
        assertEquals("S#\n..\n#G\n", grid.toString());
        assertEquals(0, grid.getStart().getRow());
        assertEquals(0, grid.getStart().getColumn());
        assertEquals(2, grid.getGoal().getRow());
        assertEquals(1, grid.getGoal().getColumn());
    }
}
