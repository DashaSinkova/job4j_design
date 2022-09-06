package ru.job4j.ood.ocp;

import java.util.ArrayList;

public class Store {
    private static final ArrayList<Computer> DEVICES = new ArrayList<>();

    public Computer add(Computer computer) {
        DEVICES.add(computer);
        return computer;
    }

    public ArrayList<Computer> getDevices() {
        return DEVICES;
    }
}