package ru.job4j.collection;

import java.util.Objects;

public class Entry<K, V> {
    private K key;
    private int hash;
    private V value;
    Entry(K key, int hash, V value) {
        this.key = key;
        this.hash = hash;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public int getHash() {
        return hash;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

