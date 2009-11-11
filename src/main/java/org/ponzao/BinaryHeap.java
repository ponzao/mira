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
            this.array = Arrays.copyOf(this.array, size() * GROWTH_RATE);
        }
        this.array[count] = e;
        ++count;
        heapifyUp(count -1);
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
    public E remove() {
        // TODO Auto-generated method stub
        return null;
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
        System.out.println(pq);
        pq.add(6);
        pq.add(9);
        pq.add(-3);
        pq.add(101);
        pq.add(200);
        pq.add(6);
        pq.add(9);
        pq.add(-3);
        pq.add(101);
        pq.add(200);
        System.out.println(pq);
    }

}
