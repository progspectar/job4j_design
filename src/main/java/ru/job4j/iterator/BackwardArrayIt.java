package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        /* реализуйте конструктор */
    }

    @Override
    public boolean hasNext() {
        /* Реализуйте метод */
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[ ... ];
    }
}