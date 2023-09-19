package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T>{

    private List<T> data;
    /* здесь разместите поля класса, если они будут нужны  */

    public CyclicIterator(List<T> data) {
        /* код конструктора */
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}