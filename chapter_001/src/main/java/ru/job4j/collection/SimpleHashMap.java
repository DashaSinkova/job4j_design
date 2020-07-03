package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private Entry<K, V>[] container = new Entry[8];
    private int modCount = 0;
    private final double loadFactor = 0.75;
    private int size = 0;

    public boolean insert(K key, V value) {
        boolean res = !contains(key);
        if (res) {
            increaseArray();
            int hash = hash(key);
            int i = indexFor(hash);
            container[i] = new Entry<>(key, hash, value);
            modCount++;
            size++;
        }
        return res;
    }
    public int getLength() {
        return container.length;
    }
    private void increaseArray() {
        int capacity = container.length;
        if ((size / capacity) > loadFactor) {
            int prevCapacity = capacity;
            capacity = capacity << 1;
            Entry<K, V>[] newContainer = new Entry[capacity];
            for (int i = 0; i < prevCapacity; i++) {
                if (container[i] != null) {
                    int hash = hash(container[i].getKey());
                    int index = indexFor(hash);
                    newContainer[index] = new Entry<>(container[i].getKey(), hash, container[i].getValue());
                }
            }
            container = newContainer;
        }
    }

    public V get(K key) {
        V res = null;
        int i = indexFor(hash(key));
        if (container[i] != null && container[i].getKey().equals(key)) {
            res = container[i].getValue();
        }
        return res;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode() ^ key.hashCode() >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (container.length - 1);
    }

    private boolean contains(K key) {
        boolean res = false;
        int i = indexFor(hash(key));
        if (container[i] != null && container[i].getKey().equals(key)) {
            res = true;
        }
        return res;
    }

    public boolean delete(K key) {
        boolean res = false;
        int i = indexFor(hash(key));
        if (container[i] != null && container[i].getKey().equals(key)) {
            res = true;
            container[i] = null;
            modCount++;
            size--;
        }
        return res;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private final int expectedModCount = modCount;
            int position = 0;
            @Override
            public boolean hasNext() {
                boolean res = false;
                    while (position < container.length) {
                        if (container[position] != null) {
                            res = true;
                            break;
                        }
                        position++;
                    }
                return res;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[position++].getValue();
            }
        };
    }
}
