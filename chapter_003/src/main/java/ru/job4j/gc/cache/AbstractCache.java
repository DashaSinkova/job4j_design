package ru.job4j.gc.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    private void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        V res = cache.getOrDefault(key, new SoftReference<V>(null)).get();
        System.out.println(res);
        if (res == null) {
            res = load(key);
            put(key, res);
        } else {
            System.out.println("File exists in the cache!");
        }
        System.out.println(cache);
        return res;
    }

    protected abstract V load(K key);
}
