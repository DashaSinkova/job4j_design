package ru.job4j.ood.ocp;

public class Computer implements Device {

    @Override
    public String getSound() {
        return "soundOfComputer";
    }

    @Override
    public String getSize() {
        return "sizeOfComputer";
    }
}
