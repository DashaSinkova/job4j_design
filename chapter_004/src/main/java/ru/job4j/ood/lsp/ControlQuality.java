package ru.job4j.ood.lsp;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

public class ControlQuality {
    public static Calendar date = Calendar.getInstance();
    private Store store;
    private Food food;
    List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }
    private void findStore(int percent) {
       if (percent < 25) {
           store = stores.get(stores.indexOf(validate(new Warehouse())));
       } else if (percent >= 25 && percent < 75) {
           store = stores.get(stores.indexOf(validate(new Shop())));
       } else if (percent >= 75 && percent < 100) {
           BigDecimal price = food.getPrice().subtract(food.getPrice().multiply(new BigDecimal(food.getDiscount())).divide(new BigDecimal(100)));
           food.setPrice(price);
           store = stores.get(stores.indexOf(validate(new Shop())));
       } else if (percent >= 100) {
           store = stores.get(stores.indexOf(validate(new Trash())));
       }
    }

    private Store validate(Store store) {
        if (!stores.contains(store)) {
            stores.add(store);
        }
        return store;
    }

    public void productDistribution(Food food) {
        this.food = food;
        long expiration = Duration.between(food.getCreateDate().toInstant(), food.getExpiryDate().toInstant()).toDays();
        long fromCreatedToNow = Duration.between(food.getCreateDate().toInstant(), date.toInstant()).toDays();
        int percent = (int) (fromCreatedToNow * 100 / expiration);
        findStore(percent);
        store.add(food);
    }
}

