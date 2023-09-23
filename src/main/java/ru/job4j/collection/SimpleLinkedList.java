package ru.job4j.collection;

import java.util.Iterator;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}