package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    final SimpleStack<T> in = new SimpleStack<>();
    final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (isEmpty(out)) {
            while (!isEmpty(in)) {
                out.push(in.pop());
            }
        }
        if (isEmpty(out)) {
            throw new NoSuchElementException("Queue is empty");
        } else {
            return out.pop();
        }
    }

    public void push(T value) {
        in.push(value);
    }

    public boolean isEmpty(SimpleStack<T> stack) {
        return stack.getLinked().size() == 0;
    }
}