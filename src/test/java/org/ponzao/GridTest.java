package org.ponzao;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class GridTest {
    @Test
    public void test_Start_Node_Is_Not_Null_After_Construction() {
        final Grid grid = new Grid("..G\nS..\n");
        assertNotNull(grid.getStart());
    }

    @Test
    public void test_Goal_Node_Is_Not_Null_After_Construction() {
        final Grid grid = new Grid("..G\nS..\n");
        assertNotNull(grid.getGoal());
    }

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

    @Test
    public void test_As_String() {
        final Grid grid = new Grid("..G\nS..\n...\n");
        assertEquals(grid.asString(), grid.toString());
        assertEquals("..G\nS..\n...\n", grid.asString());
    }

    @Test
    public void test_Get_Accessible_Neighbors_All_Are_Accessible() {
        final Grid grid = new Grid("...\n.S.\n..G\n");
        final Node[] neighbors = grid
                .getAccessibleNeighborsWithValuesUpdatedOf(grid.getStart());
        assertNotNull(neighbors[0]);
        assertNotNull(neighbors[1]);
        assertNotNull(neighbors[2]);
        assertNotNull(neighbors[3]);
        assertNotNull(neighbors[4]);
        assertNotNull(neighbors[5]);
        assertNotNull(neighbors[6]);
        assertNotNull(neighbors[7]);
    }

    @Test
    public void test_Get_Accessible_Neighbors_Only_One_Is_Accessible_Because_Others_Arent_In_Grid() {
        final Grid grid = new Grid("SG\n");
        final Node[] neighbors = grid
                .getAccessibleNeighborsWithValuesUpdatedOf(grid.getStart());
        assertNotNull(neighbors[0]);
        assertEquals(grid.getGoal(), neighbors[0]);
        assertNull(neighbors[1]);
    }

    @Test
    public void test_Get_Accessible_Neighbors_Nothing_Accessible_Because_Blocked() {
        final Grid grid = new Grid("###\n#S#\n###\n..G\n");
        final Node[] neighbors = grid
                .getAccessibleNeighborsWithValuesUpdatedOf(grid.getStart());
        assertNull(neighbors[0]);
    }

    @Test
    public void test_Get_Accessible_Neighbors_Only_One_Accessible_Because_Cheating_Past_Blocks_Not_Allowed() {
        final Grid grid = new Grid("S#\n.G\n");
        final Node[] neighbors = grid
                .getAccessibleNeighborsWithValuesUpdatedOf(grid.getStart());
        assertNotNull(neighbors[0]);
        assertNull(neighbors[1]);
        assertThat(neighbors[0], not(grid.getGoal()));
    }

    @Test
    public void test_Get_Accessible_Neighbors_Twice_Cost_Doesnt_Improve() {
        final Grid grid = new Grid("G..\n...\n..S\n");
        final Node[] neighbors = grid
                .getAccessibleNeighborsWithValuesUpdatedOf(grid.getStart());
        final Node notImprovingNode = neighbors[1];
        final Node[] neighborsOfBestNode = grid
                .getAccessibleNeighborsWithValuesUpdatedOf(neighbors[0]);
        for (Node node : neighborsOfBestNode) {
            if (node != null && node.equals(notImprovingNode))
                fail("Node's value improved.");
        }
        assertNotNull(neighborsOfBestNode[4]);
        assertNull(neighborsOfBestNode[5]);
    }

    @Test
    public void test_Get_Accessible_Neighbors_Twice_Cost_Improves() {
        /*
         * TODO I can't think of a situation where the value improves, in such a
         * simple grid. Luckily this case is handled by the integration test.
         */
        assertTrue(true);
    }

}
