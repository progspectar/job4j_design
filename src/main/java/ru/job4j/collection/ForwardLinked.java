package ru.job4j.collection;

import java.util.Iterator;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        /*используйте код из предыдущего задания*/
    }

    public T get(int index) {
        /*используйте код из предыдущего задания*/
    }

    public T deleteFirst() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        /*используйте код из предыдущего задания*/
    }

    private static class Node<T> {
        /*используйте код из предыдущего задания*/
    }
}