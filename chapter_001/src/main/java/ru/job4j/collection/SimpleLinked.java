package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinked<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;
    private int modCount = 0;

    public void add(T el) {
        Node<T> lastEl = last;
        Node<T> newNode = new Node<>(lastEl, el, null);
        last = newNode;
        if (lastEl == null) {
            first = newNode;
        } else {
            lastEl.next = newNode;
        }
        size++;
        modCount++;
    }
    public T get(int index) {
        Objects.checkIndex(index, size);
        return findT(index);
    }
    private T findT(int index) {
        int i = 0;
        Node<T> el = last;
        while (i < size - 1 - index) {
            el = el.prev;
            i++;
        }
        return el.item;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int itPosition = 0;
            private final int expectedModCount = modCount;
            private int delta;
            @Override
            public boolean hasNext() {
                boolean res = size != 0;
                if (itPosition == size) {
                    delta = -1;
                    itPosition = size - 1 - 1;
                }
                if (itPosition == 0) {
                    delta = 1;
                }
                return res;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T res = findT(itPosition);
                itPosition += delta;
                return res;
            }
        };
    }
}
