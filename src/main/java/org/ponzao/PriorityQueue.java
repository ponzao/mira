package org.ponzao;

public interface PriorityQueue<E extends Comparable<?>> {
    void add(E e);

    E remove();
}
