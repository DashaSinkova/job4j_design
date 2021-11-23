package ru.job4j.gc;

public class Person {
    private int id;
    private String name;

    public Person(int age, String name) {
        this.id = age;
        this.name = name;
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", id, name);
    }

}
