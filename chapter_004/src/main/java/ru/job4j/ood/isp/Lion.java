package ru.job4j.ood.isp;

public class Lion implements Animal {
    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String song() {
        return "lion growl";
    }

    @Override
    public String area() {
        return "savannah";
    }

    @Override
    public String moveType() {
        return "4 paws";
    }
}
