package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        V res;
        SoftReference<V> ref = cache.get(key);
        if (ref != null) {
            res = ref.get();
        } else {
            res = load(key);
            put(key, res);
        }
        return res;
    }

    protected abstract V load(K key);
}
