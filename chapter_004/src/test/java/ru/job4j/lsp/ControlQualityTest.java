package ru.job4j.lsp;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlQualityTest {

    @Test
    public void whenAddToWarehouse() {
        List<Store> stores = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        stores.add(warehouse);
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 8).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);
        control.foodDistribution(food);
        assertThat(warehouse.findAll().get(0)).isEqualTo(food);
    }
    @Test
    public void whenAddToShop() {
        List<Store> stores = new ArrayList<>();
        Shop shop = new Shop();
        stores.add(shop);
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);
        control.foodDistribution(food);
        assertThat(shop.findAll().get(0)).isEqualTo(food);
    }

    @Test
    public void whenAddToShopWithDiscount() {
        List<Store> stores = new ArrayList<>();
        Shop shop = new Shop();
        stores.add(shop);
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2021, 12, 25).build(),
                new Calendar.Builder().setDate(2022, 01, 14).build(),
                new BigDecimal(1000), 10);
        control.foodDistribution(food);
        assertThat(shop.findAll().get(0).getPrice()).isEqualTo(new BigDecimal(900));
    }

    @Test
    public void whenAddToTrash() {
        List<Store> stores = new ArrayList<>();
        Trash trash = new Trash();
        stores.add(trash);
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 10).build(),
                new BigDecimal(1000), 10);
        control.foodDistribution(food);
        assertThat(trash.findAll().get(0)).isEqualTo(food);
    }

    @Test
    public void whenAddToMixStore() {
        List<Store> stores = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality control = new ControlQuality(stores);

        Food trashFood = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 10).build(),
                new BigDecimal(1000), 10);

        Food shopFood = new MeatFood("свинина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);

        Food warehouseFood = new MeatFood("курица",
                new Calendar.Builder().setDate(2022, 01, 8).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);

        control.foodDistribution(trashFood);
        control.foodDistribution(shopFood);
        control.foodDistribution(warehouseFood);

        assertThat(trash.findAll().get(0)).isEqualTo(trashFood);
        assertThat(shop.findAll().get(0)).isEqualTo(shopFood);
        assertThat(warehouse.findAll().get(0)).isEqualTo(warehouseFood);
    }
}
