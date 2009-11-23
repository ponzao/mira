package org.ponzao;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryHeapTest {

    @Test
    public void testAdd_And_Peek_Twice() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        final Integer expected = 10;
        binaryHeap.add(expected);
        assertEquals(expected, binaryHeap.peek());
        assertEquals(expected, binaryHeap.peek());
    }

    @Test
    public void testPeek() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd_And_Remove() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        final Integer expected = 10;
        binaryHeap.add(expected);
        assertEquals(expected, binaryHeap.remove());
    }

    @Test
    public void testRemove() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd_One_Remove_It_And_Verify_Empty() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        final int expected = 0;
        binaryHeap.add(expected);
        binaryHeap.remove();
        assertEquals(expected, binaryHeap.size());
    }

    @Test
    public void testRemove_From_Empty_Heap_Throws_SOME_EXCEPTION() {
        fail("Not yet implemented");
    }

    @Test
    public void testAdd_Several_And_Peek() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        final Integer expected = -100;
        binaryHeap.add(14);
        binaryHeap.add(24);
        binaryHeap.add(13);
        binaryHeap.add(-14);
        binaryHeap.add(114);
        binaryHeap.add(expected);
        binaryHeap.add(214);
        binaryHeap.add(4);
        binaryHeap.add(3);
        binaryHeap.add(-4);
        binaryHeap.add(11114);
        assertEquals(expected, binaryHeap.peek());
    }

    @Test
    public void testAdd_Several_And_Remove_All() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        final Integer expected = 10000000;
        binaryHeap.add(14);
        binaryHeap.add(24);
        binaryHeap.add(13);
        binaryHeap.add(-14);
        binaryHeap.add(114);
        binaryHeap.add(expected);
        binaryHeap.add(214);
        binaryHeap.add(4);
        binaryHeap.add(3);
        binaryHeap.add(-4);
        binaryHeap.add(11114);
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        binaryHeap.remove();
        assertEquals(expected, binaryHeap.remove());
    }

    @Test
    public void testSize_When_Empty() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        assertEquals(0, binaryHeap.size());
    }

    @Test
    public void testSize_When_1() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        binaryHeap.add(100);
        assertEquals(1, binaryHeap.size());
    }

    @Test
    public void testSize_When_2() {
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        binaryHeap.add(100);
        binaryHeap.add(100);
        assertEquals(2, binaryHeap.size());
    }

    @Test
    public void testToString_Empty_Heap() {
        final String expected = "BinaryHeap( null )";
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        assertEquals(expected, binaryHeap.toString());
    }

    @Test
    public void testToString_One_Value() {
        final String expected = "BinaryHeap( 1000 )";
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        binaryHeap.add(1000);
        assertEquals(expected, binaryHeap.toString());
    }

    @Test
    public void testToString_Three_Values() {
        final String expected = "BinaryHeap( -10 12 100 null )";
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        binaryHeap.add(-10);
        binaryHeap.add(12);
        binaryHeap.add(100);
        assertEquals(expected, binaryHeap.toString());
    }

    @Test
    public void testToString_Few_Values() {
        final String expected = "BinaryHeap( -10 12 100 10000 1000 null null null )";
        final BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>();
        binaryHeap.add(-10);
        binaryHeap.add(12);
        binaryHeap.add(100);
        binaryHeap.add(10000);
        binaryHeap.add(1000);
        assertEquals(expected, binaryHeap.toString());
    }

}
