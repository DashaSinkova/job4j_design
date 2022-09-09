package ru.job4j.serialization.json;

import java.util.Arrays;

public class CLient {
    private boolean isService;
    private String name;
    private int applicationNumber;
    private Engineer engineer;
    private String[] application;

    public CLient(boolean isService, String name, int applicationNumber, Engineer engineer, String[] application) {
        this.isService = isService;
        this.name = name;
        this.applicationNumber = applicationNumber;
        this.engineer = engineer;
        this.application = application;
    }

    @Override
    public String toString() {
        return "CLient{"
                + "isService=" + isService
                + ", name='" + name + '\''
                + ", applicationNumber=" + applicationNumber
                + ", engineer=" + engineer
                + ", application=" + Arrays.toString(application)
                + '}';
    }
}
