package org.ponzao;


public interface PriorityQueue<E extends Comparable<E>> {
    void add(E e);

    E remove();
    
    E peek();
}
