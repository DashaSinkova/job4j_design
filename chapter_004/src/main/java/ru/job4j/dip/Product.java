package ru.job4j.dip;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
    String name;
    int storageNumber;
    LocalDateTime dateTime;

    public Product(String name, int storageNumber, LocalDateTime dateTime) {
        this.name = name;
        this.storageNumber = storageNumber;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }


    public int getStorageNumber() {
        return storageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return storageNumber == product.storageNumber && Objects.equals(name, product.name) && Objects.equals(dateTime, product.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, storageNumber, dateTime);
    }
}
