package ru.job4j.ood.lsp;

import java.math.BigDecimal;
import java.util.Calendar;

public class MeatFood extends Food {
    public MeatFood(String name, Calendar expiryDate, Calendar createDate, BigDecimal price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
