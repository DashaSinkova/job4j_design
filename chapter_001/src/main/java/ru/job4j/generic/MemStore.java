package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();
    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean res = false;
        int index = getIndexById(id);
        if (index != -1) {
            mem.set(index, model);
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(String id) {
        boolean res = false;
        int index = getIndexById(id);
        if (index != -1) {
            mem.remove(index);
            res = true;
        }
        return res;
    }

    @Override
    public T findById(String id) {
        T res = null;
        int index = getIndexById(id);
        if (index != -1) {
            res = mem.get(index);
        }
        return res;
    }
    private int getIndexById(String id) {
        int index = -1;
        for (T el : mem) {
            if (el.getId().equals(id)) {
                index = mem.indexOf(el);
            }
        }
        return index;
    }
}
