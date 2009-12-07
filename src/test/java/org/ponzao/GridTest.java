package org.ponzao;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridTest {
    @Test
    public void test_Constructor() {
        final Grid grid = new Grid("..G\nS..\n");
        assertEquals("..G\nS..\n", grid.toString());
    }
}
