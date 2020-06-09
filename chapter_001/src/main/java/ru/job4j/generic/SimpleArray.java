package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> {

    private int position = 0;
    private T[] arr;

    public SimpleArray(int length) {
        arr = (T[]) new Object[length];
    }

    public void add(T model) {
            arr[position++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
            arr[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, position);
            arr[index] = null;
            System.arraycopy(arr, index + 1, arr, index, position - 1 - index);
                position--;
    }

    public T get(int index) {
        T res = null;
        Objects.checkIndex(index, position);
            res = arr[index];
        return res;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int iteratorPosition = 0;
            @Override
            public boolean hasNext() {
                return iteratorPosition < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[iteratorPosition++];
            }
        };
    }
}
