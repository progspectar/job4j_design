package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    final SimpleStack<T> in = new SimpleStack<>();
    final SimpleStack<T> out = new SimpleStack<>();
    int inSize;
    int outSize;

    public T poll() {
        if (isEmpty(out, false)) {
            while (!isEmpty(in, true)) {
                out.push(in.pop());
                inSize--;
                outSize++;
            }
        }
        if (isEmpty(out, false)) {
            throw new NoSuchElementException("Queue is empty");
        } else {
            outSize--;
            return out.pop();
        }
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }

    public boolean isEmpty(SimpleStack<T> stack, boolean inout) {
        return inout ? inSize == 0 : outSize == 0;
    }
}