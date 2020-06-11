package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int modCount = 0;

    public T get(int index) {
        T res = null;
        Objects.checkIndex(index, modCount);
        res = (T) container[index];
        return res;
    }

    public void add(T model) {
        if (modCount == container.length) {
            createArray();
        }
        container[modCount++] = model;
    }
    private void createArray() {
        Object[] newContainer = new Object[container.length * 2];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        container = newContainer;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int position = 0;
            @Override
            public boolean hasNext() {
                return expectedModCount == modCount;
            }

            @Override
            public T next() {
                if (position >= expectedModCount) {
                    throw new NoSuchElementException();
                }
                if (!hasNext()) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[position++];
            }
        };
    }
}
