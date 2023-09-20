package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
    }

    @Override
    public T set(int index, T newValue) {
    }

    @Override
    public T remove(int index) {
    }

    @Override
    public T get(int index) {
    }

    @Override
    public int size() {
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
            }

            @Override
            public T next() {
            }
        };
    }
}