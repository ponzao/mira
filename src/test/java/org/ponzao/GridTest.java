package org.ponzao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GridTest {
	private Grid grid;

	@Test
    public void test_Empty_Grid_With_Predefined_Dimensions() {
		grid = new Grid(3, 2);
		assertEquals("..\n..\n..\n", grid.toString());
	}
	
	@Test
	public void test_Grid_With_Predefined_Values() {
	    grid = new Grid("S#\n..\n#G\n");
	    assertEquals("S#\n..\n#G\n", grid.toString());
	}
}
