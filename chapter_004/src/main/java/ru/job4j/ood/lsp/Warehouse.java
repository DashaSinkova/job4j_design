package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Warehouse implements Store {

    private final List<Food> products = new ArrayList<>();

    @Override
    public void add(Food food) {
        products.add(food);
    }

    @Override
    public List<Food> findAll() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(products, warehouse.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
