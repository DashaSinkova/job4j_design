package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[10];
    private int position = 0;
    private int modCount = 0;

    public int getPosition() {
        return position;
    }

    public T get(int index) {
        T res = null;
        Objects.checkIndex(index, position);
        res = (T) container[index];
        return res;
    }

    public void add(T model) {
        if (position == container.length) {
            createArray();
        }
        container[position++] = model;
        modCount++;
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
            private int itPosition = 0;
            @Override
            public boolean hasNext() {
                return itPosition < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[itPosition++];
            }
        };
    }
}
