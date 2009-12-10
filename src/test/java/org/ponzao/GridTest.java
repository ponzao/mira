package org.ponzao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GridTest {

    @Test
    public void test_Start_Cost_Is_Set_To_Zero_In_Constructor() {
        final Grid grid = new Grid("..G\nS..\n");
        final Double expected = 0.0;
        assertEquals(expected, grid.getStart().getCost());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_Will_Throw_Illegal_Argument_Exception_If_Parameter_Is_Empty() {
        new Grid("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_Will_Throw_Some_Exception_If_Start_Node_Cannot_Be_Set() {
        new Grid("..G\n...\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_Will_Throw_Some_Exception_If_Goal_Node_Cannot_Be_Set() {
        new Grid("...\nS..\n");
    }

    @Test
    public void test_Node_In_Grid_Area() {
        final Grid grid = new Grid("SG\n");
        assertTrue(grid.isInGrid(0, 0));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_Left() {
        final Grid grid = new Grid("SG\n");
        assertFalse(grid.isInGrid(0, -1));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_Right() {
        final Grid grid = new Grid("SG\n");
        assertFalse(grid.isInGrid(0, 2));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_High() {
        final Grid grid = new Grid("SG\n");
        assertFalse(grid.isInGrid(-1, 0));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Too_Low() {
        final Grid grid = new Grid("SG\n");
        assertFalse(grid.isInGrid(1, 0));
    }

    @Test
    public void test_Node_Not_In_Grid_Area_Diagonally_Misplaced() {
        final Grid grid = new Grid("SG\n");
        assertFalse(grid.isInGrid(1, 1));
    }
    
    @Test
    public void test_Node_In_Bottom_Right_Corner_Not_Accessible_Due_To_Blocking_On_Right() {
        final Grid grid = new Grid("S#\n.G\n");
        assertFalse(grid.isAccessible(0, 0, 1, 1));
    }
    
    @Test
    public void test_Node_In_Bottom_Right_Corner_Not_Accessible_Due_To_Blocking_On_Bottom() {
        final Grid grid = new Grid("S.\n#G\n");
        assertFalse(grid.isAccessible(0, 0, 1, 1));
    }
    
    @Test
    public void test_Node_In_Bottom_Left_Corner_Not_Accessible_Due_To_Blocking_On_Left() {
        final Grid grid = new Grid("#S\nG.\n");
        assertFalse(grid.isAccessible(0, 1, 1, 0));
    }
    
    @Test
    public void test_Node_In_Bottom_Left_Corner_Not_Accessible_Due_To_Blocking_On_Bottom() {
        final Grid grid = new Grid(".S\nG#\n");
        assertFalse(grid.isAccessible(0, 1, 1, 0));
    }
    
    @Test
    public void test_Node_In_Top_Left_Corner_Not_Accessible_Due_To_Blocking_On_Left() {
        final Grid grid = new Grid("G.\n#S\n");
        assertFalse(grid.isAccessible(1, 1, 0, 0));
    }
    
    @Test
    public void test_Node_In_Top_Left_Corner_Not_Accessible_Due_To_Blocking_On_Top() {
        final Grid grid = new Grid("G#\n.S\n");
        assertFalse(grid.isAccessible(1, 1, 0, 0));
    }

    @Test
    public void test_Node_In_Top_Right_Corner_Not_Accessible_Due_To_Blocking_On_Right() {
        final Grid grid = new Grid(".G\nS#\n");
        assertFalse(grid.isAccessible(1, 0, 0, 1));
    }
    
    @Test
    public void test_Node_In_Top_Right_Corner_Not_Accessible_Due_To_Blocking_On_Top() {
        final Grid grid = new Grid("#G\nS.\n");
        assertFalse(grid.isAccessible(1, 0, 0, 1));
    }
    
    @Test
    public void test_Node_Is_Accessible_On_Right() {
        final Grid grid = new Grid("SG\n");
        assertTrue(grid.isAccessible(0, 1, 0, 0));
    }
    
    @Test
    public void test_Node_Is_Accessible_On_Left() {
        final Grid grid = new Grid("SG\n");
        assertTrue(grid.isAccessible(0, 0, 0, 1));
    }
    
    @Test
    public void test_Node_Is_Accessible_On_Bottom() {
        final Grid grid = new Grid("S\nG\n");
        assertTrue(grid.isAccessible(0, 0, 1, 0));
    }
    
    @Test
    public void test_Node_Is_Accessible_On_Top() {
        final Grid grid = new Grid("S\nG\n");
        assertTrue(grid.isAccessible(1, 0, 0, 0));
    }
    
    @Test
    public void test_To_String() {
        final Grid grid = new Grid("..G\nS..\n...\n");
        assertEquals("..G\nS..\n...\n", grid.toString());
    }
}
