package org.ponzao;

import java.util.Arrays;

public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    private E[] array;
    private static final int GROWTH_RATE = 2;
    private int count;

    @SuppressWarnings("unchecked")
    public BinaryHeap() {
        this.array = (E[]) new Comparable[1];
        this.count = 0;
    }

    @Override
    public E peek() {
        return array[0];
    }

    @Override
    public void add(E e) {
        if (e == null) {
            throw new NullPointerException(
                    "Cannot input null values into BinaryHeap!");
        }
        if (count == array.length) {
            array = Arrays.copyOf(this.array, array.length * GROWTH_RATE);
        }
        array[count] = e;
        ++count;
        heapifyUp(count - 1);
    }

    private void heapifyUp(final int childIndex) {
        final E child = array[childIndex];
        final int parentIndex = childIndex / 2;
        final E parent = array[parentIndex];
        if (child.compareTo(parent) == -1) {
            array[parentIndex] = child;
            array[childIndex] = parent;
            heapifyUp(parentIndex);
        }
    }

    @Override
    public E remove() {
        if (size() == 0) {
            throw new NullPointerException(
                    "Cannot remove from empty BinaryHeap!");
        }
        final E removed = array[0];
        array[0] = array[count - 1];
        array[count - 1] = null;
        --count;
        heapifyDown(0);

        return removed;
    }

    private void heapifyDown(final int parentIndex) {
        final E parent = array[parentIndex];
        final int childLeftIndex = 2 * parentIndex + 1;
        final int childRightIndex = 2 * parentIndex + 2;
        if (childLeftIndex >= array.length && childRightIndex >= array.length) {
            return;
        }
        final E childLeft = array[childLeftIndex];
        final E childRight = (childRightIndex >= array.length) ? null
                : array[childRightIndex];
        if (childRight != null && childRight.compareTo(childLeft) == -1) {
            if (childRight.compareTo(parent) == -1) {
                array[childRightIndex] = parent;
                array[parentIndex] = childRight;
                heapifyDown(childRightIndex);
            }
        } else if (childLeft != null) {
            if (childLeft.compareTo(parent) == -1) {
                array[childLeftIndex] = parent;
                array[parentIndex] = childLeft;
                heapifyDown(childLeftIndex);
            }
        }
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public void add(final E[] es) {
        for (E e : es) {
            if (e != null) {
                add(e);
            }
        }
    }

    @Override
    public String toString() {
        String result = "BinaryHeap( ";
        for (E e : array) {
            result = result + e + " ";
        }
        return result + ")";
    }

}
