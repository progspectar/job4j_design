package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean res = !contains(value);
        if (res) {
            set.add(value);
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}