package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private final List<Food> products = new ArrayList<>();
    private static final int PERCENT = 25;

    @Override
    public boolean accept(Food food) {
        boolean res = false;
        if (getPercent(food) < PERCENT) {
            res = true;
        }
        return res;
    }

    @Override
    public boolean add(Food food) {
        boolean res = false;
        if (accept(food)) {
            products.add(food);
            res = true;
        }
        return res;
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(products);
    }
}
