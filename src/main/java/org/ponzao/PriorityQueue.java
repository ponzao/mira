package org.ponzao;

/**
 * Interface for a priority queue.
 * 
 * @author vesa
 */
public interface PriorityQueue<E extends Comparable<E>> {
    /**
     * Adds object e to queue.
     */
    void add(E e);

    /**
     * Removes and returns object with best priority from queue.
     */
    E remove();

    /**
     * Returns object with best priority from queue.
     */
    E peek();

    /**
     * Returns size of queue.
     */
    int size();

    /**
     * Adds array into queue.
     */
    void add(E[] es);
}
