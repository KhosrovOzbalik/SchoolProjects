import java.util.*;

public class Queue<T extends Comparable<T>> implements Iterable<T> {
    private Node front;
    private Node rear;
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


    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    public Queue(T... args) {
        for (T arg : args) {
            enqueue(arg);
        }
    }


    public boolean isEmpty() { return front == null; }


    public int size() { return size; }


    // Returns last item.
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return front.item;
    }


    public void enqueue(T item) {
        Node oldRear = rear;
        rear = new Node(item, null);
        if (isEmpty()) front = rear;
        else oldRear.next = rear;
        size += 1;
    }


    // Removes and returns front.
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = front.item;
        front = front.next;
        size -= 1;
        if (isEmpty()) rear = null;
        return item;
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


    @Override
    public Iterator<T> iterator() { return new LinkedIterator(); }

    private class LinkedIterator implements Iterator<T> {
        private Node current = front;


        public boolean hasNext()  { return current != null;}


        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
