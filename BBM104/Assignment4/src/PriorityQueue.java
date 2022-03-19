import java.util.*;

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {
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


    public PriorityQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public PriorityQueue(T... args) {
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
        if (isEmpty()) front = new Node(item, rear);
        else if (front.item.compareTo(item) < 0) {
            front = new Node(item, front);
        }
        else {
            Node currNode = front;
            while (true) {
                if (currNode.next == null ) {
                    currNode.next = new Node(item, null);
                    if (currNode == rear) rear = rear.next;
                    break;
                }
                else if (currNode.next.item.compareTo(item) < 0) {
                    Node brokeNext = currNode.next;
                    currNode.next = new Node(item, brokeNext);
                    break;
                }
                currNode = currNode.next;
            }
        }
        size ++;
    }


    // Removes and returns front.
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = front.item;
        front = front.next;
        size -= 1;
        if (isEmpty()) rear = null;
        size --;
        return item;
    }


    public void remove(T item) {
        Node node = front;
        if (node.item == item) {
            dequeue();
            size --;
            return;
        }
        while (true) {
            if (node.next.item == item) {
                node.next = node.next.next;
                size --;
                break;
            }
            node = node.next;
        }
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
