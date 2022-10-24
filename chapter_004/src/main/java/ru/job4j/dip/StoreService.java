package ru.job4j.dip;

import java.util.HashMap;

public class StoreService {
    private HashMap<Integer, Product> store = new HashMap<>();

    public boolean add(Product product) {
        if (store.containsKey(product.getStorageNumber())) {
            System.out.println("Product exist");
            return false;
        }
        return store.put(product.getStorageNumber(), product) != null;
    }

    public HashMap<Integer, Product> getStore() {
        return store;
    }

    public boolean remove(int id) {
        return store.remove(id) != null;
    }

    public void clear() {
        store.clear();
    }
}
