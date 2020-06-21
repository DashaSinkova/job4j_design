package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> container = new SimpleArray<>();

    public boolean add(T model) {
        boolean res = !contains(model);
        if (res) {
            container.add(model);
        }
        return res;
    }
    private boolean contains(T model) {
        boolean res = false;
        for (T el : container) {
            if (Objects.equals(model, el)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
