package zzw;

/**
 * Created by john on 2016/11/21.
 * 链表栈
 */
public class Stack<T> {

    private Node top;
    private int N;


    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return N;
    }

    public T pop() {
        T item = top.item;
        top = top.next;
        N--;
        return item;
    }

    public void push(T item) {
        Node oldTop = top;
        top = new Node();
        top.item = item;
        top.next = oldTop;
        N++;
    }

}
