package org.ponzao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GridTest {
    @Test
    public void test_Constructor() {
        final Grid grid = new Grid("..G\nS..\n");
        assertEquals("..G\nS..\n", grid.toString());
    }

    @Test
    public void test_Get_Start_Not_Null_After_Construction() {
        final Grid grid = new Grid("..G\nS..\n");
        assertTrue(grid.getStart() != null);
    }

    @Test
    public void test_Goal_Not_Null_After_Construction() {
        final Grid grid = new Grid("..G\nS..\n");
        assertTrue(grid.getGoal() != null);
    }

    @Test
    public void test_Node_In_Grid_Area() {
        final Grid grid = new Grid(".");
        assertTrue(grid.isInGrid(0, 0));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_Left() {
        final Grid grid = new Grid(".");
        assertFalse(grid.isInGrid(0, -1));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_Right() {
        final Grid grid = new Grid(".");
        assertFalse(grid.isInGrid(0, 1));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_High() {
        final Grid grid = new Grid(".");
        assertFalse(grid.isInGrid(-1, 0));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_Low() {
        final Grid grid = new Grid(".");
        assertFalse(grid.isInGrid(1, 0));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Diagonally_Misplaced() {
        final Grid grid = new Grid(".");
        assertFalse(grid.isInGrid(1, 1));
    }
    
    @Test
    public void test_To_String() {
        final Grid grid = new Grid("..G\nS..\n...\n");
        assertEquals("..G\nS..\n...\n", grid.toString());
    }
}
