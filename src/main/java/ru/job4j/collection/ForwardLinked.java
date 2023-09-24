package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;


    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> currNode = this.head;
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> currNode = this.head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode == null ? null : currNode.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> old = head;
        head = old.next;
        old.next = null;
        size--;
        modCount++;
        return old.item;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> tIterator = new Iterator<T>() {
            private Node<T> currentNode = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T res = currentNode.item;
                currentNode = currentNode.next;
                return res;
            }
        };
        return tIterator;
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        list.add(1);
        int res = list.deleteFirst();
        System.out.println(res);
    }
}