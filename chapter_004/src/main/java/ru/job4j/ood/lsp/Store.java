package ru.job4j.ood.lsp;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;

public interface Store {
    boolean accept(Food food);
    Calendar DATE = new Calendar.Builder().setDate(2022, 01, 10).build();;

    boolean add(Food food);

    List<Food> findAll();

    default int getPercent(Food food) {
        long expiration = Duration.between(food.getCreateDate().toInstant(), food.getExpiryDate().toInstant()).toDays();
        long fromCreatedToNow = Duration.between(food.getCreateDate().toInstant(), DATE.toInstant()).toDays();

        return (int) (fromCreatedToNow * 100 / expiration);
    }
}
