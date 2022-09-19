package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {
    private final Gson gson;

    private Store store;

    public JSONReport(Store store) {
        this.store = store;
        gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        Employees employeesToJson = new Employees();
        employeesToJson.setEmployee(employees);
        return gson.toJson(employeesToJson);
    }
}
