package ru.job4j.it;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            Iterator<Integer> iterator = null;
            @Override
            public boolean hasNext() {
                boolean res = true;
                if (iterator == null || !iterator.hasNext()) {
                    res = false;
                    while (it.hasNext()) {
                        iterator = it.next();
                        if (iterator.hasNext()) {
                            res = true;
                            break;
                        }
                    }
                }
                return res;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }
}

