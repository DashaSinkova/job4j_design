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
        stores.add(new Warehouse());
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);
        control.date = new Calendar.Builder().setDate(2022, 01, 04).build();
        control.productDistribution(food);
        assertThat(stores.get(0).findAll().get(0)).isEqualTo(food);
    }
    @Test
    public void whenAddToShop() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Shop());
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);
        control.date = new Calendar.Builder().setDate(2022, 01, 20).build();
        control.productDistribution(food);
        assertThat(stores.get(0).findAll().get(0)).isEqualTo(food);
    }

    @Test
    public void whenAddToShopWithDiscount() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Shop());
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);
        control.date = new Calendar.Builder().setDate(2022, 01, 24).build();
        control.productDistribution(food);
        assertThat(stores.get(0).findAll().get(0).getPrice()).isEqualTo(new BigDecimal(900));
    }

    @Test
    public void whenAddToTrash() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Trash());
        ControlQuality control = new ControlQuality(stores);
        Food food = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);
        control.date = new Calendar.Builder().setDate(2022, 01, 28).build();
        control.productDistribution(food);
        assertThat(stores.get(0).findAll().get(0)).isEqualTo(food);
    }

    @Test
    public void whenAddToMixStore() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
        ControlQuality control = new ControlQuality(stores);
        control.date = new Calendar.Builder().setDate(2022, 01, 28).build();

        Food trashFood = new MeatFood("говядина",
                new Calendar.Builder().setDate(2022, 01, 01).build(),
                new Calendar.Builder().setDate(2022, 01, 28).build(),
                new BigDecimal(1000), 10);

        Food shopFood = new MeatFood("свинина",
                new Calendar.Builder().setDate(2022, 01, 20).build(),
                new Calendar.Builder().setDate(2022, 02, 06).build(),
                new BigDecimal(1000), 10);

        Food warehouseFood = new MeatFood("курица",
                new Calendar.Builder().setDate(2022, 01, 27).build(),
                new Calendar.Builder().setDate(2022, 02, 12).build(),
                new BigDecimal(1000), 10);

        control.productDistribution(trashFood);
        control.productDistribution(shopFood);
        control.productDistribution(warehouseFood);

        assertThat(stores.get(2).findAll().get(0)).isEqualTo(trashFood);
        assertThat(stores.get(1).findAll().get(0)).isEqualTo(shopFood);
        assertThat(stores.get(0).findAll().get(0)).isEqualTo(warehouseFood);
    }
}
