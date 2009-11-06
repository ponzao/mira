package org.ponzao;


public class ListPriorityQueue<E extends Comparable<?>> implements
        PriorityQueue<E> {
    private Element<E> head;

    public ListPriorityQueue() {
    }

    @Override
    public void add(E e) {
        final Element<E> element = new Element<E>(e);
        if (head == null || element.compareTo(head) == -1) {

        }

    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public String toString() {
        String result = ""; // TODO

        return result;
    }

    private static class Element<E extends Comparable<?>> implements
            Comparable<Element<E>> {
        private final E value;
        private Element next;

        public Element(E value) {
            this.value = value;
        }

        public Element getNext() {
            return this.next;
        }

        public E getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }

        @Override
        public int compareTo(Element<E> o) {
            return value.compareTo(o.value) ? -1 : value.compareTo(o.value) ? 1
                    : 0;
        }

    }
}
