package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;


public class MaxMin {
    public <T> T sort(List<T> value, Comparator<T> comparator) {
        T res = value.get(0);
        for (T el : value) {
            if (comparator.compare(el, res) > 0) {
                res = el;
            }
        }
        return res;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return sort(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return sort(value, comparator.reversed());
    }
}
