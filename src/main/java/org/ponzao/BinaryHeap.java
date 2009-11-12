package org.ponzao;

import java.util.Arrays;

public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    private E[] array;
    private static final int GROWTH_RATE = 2;
    private int count;

    public BinaryHeap() {
        this.array = (E[]) new Comparable[1]; // TODO this is ugly, can anything
        // be
        // done?
        this.count = 0;
    }

    @Override
    public void add(E e) {
        if (count == size()) {
            array = Arrays.copyOf(this.array, size() * GROWTH_RATE);
        }
        array[count] = e;
        ++count;
        heapifyUp(count - 1);
    }

    private void heapifyUp(final int childIndex) {
        final E child = array[childIndex];
        final int parentIndex = (int) Math.floor(childIndex / 2);
        final E parent = array[parentIndex];
        if (child.compareTo(parent) == -1) {
            array[parentIndex] = child;
            array[childIndex] = parent;
            heapifyUp(parentIndex);
        }
    }

    @Override
    public E remove() { // TODO empty array?
        final E removed = array[0];
        array[0] = array[count - 1];
        array[count - 1] = null;
        --count;
        heapifyDown(0);

        return removed;
    }

    private void heapifyDown(final int parentIndex) {
        final E parent = array[parentIndex];
        final int childLeftIndex = parentIndex == 0 ? 1 : 2 * parentIndex + 1;
        final int childRightIndex = parentIndex == 0 ? 2 : 2 * parentIndex + 2;
        final E childLeft = array[childLeftIndex];
        final E childRight = array[childRightIndex];
        if (childLeft != null || childRight != null) {
            if (childRight != null && childRight.compareTo(childLeft) == -1) {
                if (childRight.compareTo(parent) == -1) {
                    array[childRightIndex] = parent;
                    array[parentIndex] = childRight;
                    heapifyDown(childRightIndex);
                }
            } else {
                if (childLeft.compareTo(parent) == -1) {
                    array[childLeftIndex] = parent;
                    array[parentIndex] = childLeft;
                    heapifyDown(childLeftIndex);
                }
            }
        }
    }

    private int size() {
        return array.length;
    }

    @Override
    public String toString() {
        String result = "";
        for (E e : array) {
            result = result + e + " ";
        }
        return result;
    }

    public static void main(String args[]) {
        final PriorityQueue<Integer> pq = new BinaryHeap<Integer>();
        pq.add(7);
        pq.add(9);
        pq.add(-3);
        pq.add(6);
        pq.add(4);
        pq.add(-1);
        System.out.println(pq);
        pq.remove();
        System.out.println(pq);
        pq.remove();

        System.out.println(pq);
    }

}
