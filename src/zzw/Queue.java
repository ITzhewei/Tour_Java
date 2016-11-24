package zzw;

/**
 * Created by john on 2016/11/21.
 */
public class Queue<T> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enQueue(T item) {
        Node oldLast = this.last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = this.last;
        else oldLast.next = last;
        N++;
    }

    public T deQueue() {
        T item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
}
