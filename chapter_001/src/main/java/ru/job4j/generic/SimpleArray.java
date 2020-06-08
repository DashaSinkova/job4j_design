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
    public boolean add(T model) {
        boolean res = false;
        if (Objects.checkIndex(position, arr.length) == position) {
            arr[position++] = model;
            res = true;
        }
        return res;
    }

    public boolean set(int index, T model) {
        boolean res = false;
        if (Objects.checkIndex(index, arr.length) == index) {
            arr[index] = model;
            res = true;
        }
        return res;
    }
    public boolean remove(int index) {
        boolean res = false;
        if (Objects.checkIndex(index, arr.length) == index) {
            arr[index] = null;
            res = true;
            while (index < position) {
                T val = arr[index + 1];
                arr[index] = val;
                arr[index + 1] = null;
                index++;
                position--;
            }
        }
        return res;

    }
    public T get(int index) {
        T res = null;
        if (Objects.checkIndex(index, arr.length) == index) {
            res = arr[index];
        }
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
