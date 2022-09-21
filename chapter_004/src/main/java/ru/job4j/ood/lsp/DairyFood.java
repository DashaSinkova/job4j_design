package ru.job4j.ood.lsp;

import java.math.BigDecimal;
import java.util.Calendar;

public class DairyFood extends Food {

    public DairyFood(String name, Calendar expiryDate, Calendar createDate, BigDecimal price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
