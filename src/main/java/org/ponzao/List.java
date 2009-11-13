package org.ponzao;

public class List<E extends Comparable<E>> implements PriorityQueue<E> {

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

    public E remove() {
        final E value = this.head.value;
        this.head = head.next();
        return value;
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

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
