package org.ponzao;

public class PriorityQueue<E extends Comparable<E>> {

    Element<E> head;

    public void add(E e) {
        Element<E> element = new Element<E>(e);
        if (head == null || element.compareTo(head) == -1) {
            element.setNext(head);
            head = element;
        } else {
            Element<E> temp = head;
            while (temp.next() != null && temp.next().compareTo(element) == -1) {
                temp = temp.next();
            }
            element.setNext(temp.next());
            temp.setNext(element);
        }

    }

    @Override
    public String toString() {
        String result = this.getClass().getName().toString() + "( ";
        Element<E> current = head;
        while (current != null) {
            result = result + current + " ";
            current = current.next();
        }
        result = result + ")";
        return result;
    }

    private static class Element<E extends Comparable<E>> implements
            Comparable<Element<E>> {

        private final E value;
        private Element<E> next;

        public Element(E value) {
            this.value = value;
        }

        @Override
        public int compareTo(Element<E> e) {
            return value.compareTo(e.value);
        }

        @Override
        public String toString() {
            return value.toString();
        }

        public Element<E> next() {
            return next;
        }

        public void setNext(Element<E> next) {
            this.next = next;
        }
    }

    public static void main(String args[]) {
        final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(139);
        pq.add(10);
        pq.add(1000);
        pq.add(199999);
        pq.add(-10);
        System.out.println(pq);
    }
}
