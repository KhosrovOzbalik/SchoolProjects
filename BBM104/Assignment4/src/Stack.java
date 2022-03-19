import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private Node top;
    private int size;


    private class Node {
        private T item;
        private Node next;

        public Node() {}
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }


    public Stack() {
        top = null;
        size = 0;
    }


    public Stack(T... args) {
        for (T arg : args) {
            push(arg);
        }
    }


    public boolean isEmpty() { return top == null; }


    public int size() { return size; }


    public void push(T item) {
        Node oldTop = top;
        top = new Node(item, oldTop);
        size += 1;
    }


    public T pop() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = top.item;
        top = top.next;
        size -= 1;
        return item;
    }


    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return top.item;
    }


    @Override
    public String toString() {
        StringBuilder bobTheBuilder = new StringBuilder();
        for (T item : this) {
            bobTheBuilder.append(item);
            bobTheBuilder.append(" ");
        }
        return bobTheBuilder.toString();
    }


    public Iterator<T> iterator()  { return new ListIterator();  }


    private class ListIterator implements Iterator<T> {
        private Node current = top;


        public boolean hasNext()  { return current != null; }


        public void remove()      { throw new UnsupportedOperationException(); }


        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
