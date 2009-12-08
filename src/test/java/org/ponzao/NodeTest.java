package org.ponzao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NodeTest {
    @Test
    public void test_Calculate_Distance_Between_The_Same_Node() {
        final Node a = new Node('.', 0, 0);
        final Double expected = 0.0;
        assertEquals(expected, a.calculateDistance(a));
    }

    @Test
    public void test_Calculate_Distance_Between_Two_Vertical_Nodes() {
        final Node a = new Node('.', 0, 0);
        final Node b = new Node('.', 0, 1);
        final Double expected = 1.0;
        assertEquals(expected, a.calculateDistance(b));
    }

    @Test
    public void test_Calculate_Distance_Between_Two_Horizontal_Nodes() {
        final Node a = new Node('.', 0, 0);
        final Node b = new Node('.', 1, 0);
        final Double expected = 1.0;
        assertEquals(expected, a.calculateDistance(b));
    }

    @Test
    public void test_Calculate_Distance_Between_Two_Diagonal_Nodes() {
        final Node a = new Node('.', 0, 0);
        final Node b = new Node('.', 1, 1);
        final Double expected = Math.pow(2, 0.5);
        assertEquals(expected, a.calculateDistance(b));
    }

    @Test
    public void test_Compare_To_Smaller() {
        final Node a = new Node('.', 0, 0);
        a.setEstimatedDistance(10.0);
        a.setCost(1.0);
        final Node b = new Node('.', 1, 1);
        b.setEstimatedDistance(10.0);
        b.setCost(Math.pow(2, 0.5));
        final int expected = -1;
        assertEquals(expected, a.compareTo(b));
    }

    @Test
    public void test_Compare_To_Equal() {
        final Node a = new Node('.', 0, 0);
        a.setEstimatedDistance(10.0);
        a.setCost(1.0);
        final Node b = new Node('.', 1, 1);
        b.setEstimatedDistance(10.0);
        b.setCost(1.0);
        final int expected = -0;
        assertEquals(expected, a.compareTo(b));
    }

    @Test
    public void test_Compare_To_Bigger() {
        final Node a = new Node('.', 0, 0);
        a.setEstimatedDistance(10.0);
        a.setCost(4.0);
        final Node b = new Node('.', 1, 1);
        b.setEstimatedDistance(10.0);
        b.setCost(Math.pow(2, 0.5));
        final int expected = 1;
        assertEquals(expected, a.compareTo(b));
    }

    @Test
    public void test_Hash_Code() {
        final Node a = new Node('.', 0, 0);
        final int expected = 73997;
        assertEquals(expected, a.hashCode());
    }

    @Test
    public void test_Equals_Object_Reference_Equal() {
        final Node a = new Node('.', 0, 0);
        assertTrue(a.equals(a));
    }

    @Test
    public void test_Equals_Other_Object_Null() {
        final Node a = new Node('.', 0, 0);
        assertFalse(a.equals(null));
    }

    @Test
    public void test_Equals_Other_Object_Wrong_Type() {
        final Node a = new Node('.', 0, 0);
        assertFalse(a.equals("foobar"));
    }

    @Test
    public void test_Equals_Other_Objects_C_Differs() {
        final Node a = new Node('.', 0, 0);
        final Node b = new Node('S', 0, 0);
        assertFalse(a.equals(b));
    }

    @Test
    public void test_Equals_Other_Objects_Row_Differs() {
        final Node a = new Node('.', 0, 0);
        final Node b = new Node('.', 1, 0);
        assertFalse(a.equals(b));
    }

    @Test
    public void test_Equals_Other_Objects_Column_Differs() {
        final Node a = new Node('.', 0, 0);
        final Node b = new Node('.', 0, 1);
        assertFalse(a.equals(b));
    }

    @Test
    public void test_Equals() {
        final Node a = new Node('.', 0, 0);
        final Node b = new Node('.', 0, 0);
        assertTrue(a.equals(b));
    }

    @Test
    public void test_Is_Blocked() {
        final Node a = new Node('#', 0, 0);
        assertTrue(a.isBlocked());
    }

    @Test
    public void test_Is_Not_Blocked() {
        final Node a = new Node('.', 0, 0);
        assertFalse(a.isBlocked());
    }

    @Test
    public void test_Copy_Constructor() {
        final Node a = new Node('#', 2, 2);
        a.setCost(1.0);
        a.setEstimatedDistance(14.0);
        a.setParent(new Node('S', 1, 1));
        final Node b = new Node(a);
        // There is not getter for c.
        assertEquals(a.isBlocked(), b.isBlocked());
        assertEquals(a.getRow(), b.getRow());
        assertEquals(a.getColumn(), b.getColumn());
        assertEquals(a.getParent(), b.getParent());
        assertEquals(a.getCost(), b.getCost());
    }

    @Test
    public void test_To_String() {
        final Node a = new Node('S', 0, 0);
        final String expected = "S";
        assertEquals(expected, a.toString());
    }
}
