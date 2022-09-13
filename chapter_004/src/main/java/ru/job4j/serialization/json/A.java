package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

public class A {

    private B b;

    public void setB(B b) {
        this.b = b;
    }

    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public String getHello() {
        return "Hello";
    }
}
