package ru.job4j.ood.lsp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private static final int START_PERCENT = 25;
    private static final int MIDDLE_PERCENT = 75;
    private static final int END_PERCENT = 100;
    private final List<Food> products = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        boolean res = false;
        int percent = getPercent(food);
        if (percent >= START_PERCENT && percent < END_PERCENT) {
            res = true;
        }

        return res;
    }

    @Override
    public boolean add(Food food) {
        boolean res = false;
        int percent = getPercent(food);
        if (accept(food)) {
            if (percent >= MIDDLE_PERCENT && percent < END_PERCENT) {
                changePrice(food);
            }
            products.add(food);
            res = true;
        }
        return res;
    }

    private void changePrice(Food food) {
        BigDecimal price = food.getPrice().subtract(food.getPrice().multiply(new BigDecimal(food.getDiscount())).divide(new BigDecimal(100)));
        food.setPrice(price);
    }

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(products);
    }
}
