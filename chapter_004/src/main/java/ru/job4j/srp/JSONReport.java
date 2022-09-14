package ru.job4j.srp;

import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {
    private Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        List<Employee> employees = store.findBy(filter);
        Employees employeesToJson = new Employees();
        employeesToJson.setEmployee(employees);
        var gson = new GsonBuilder().create();
        return gson.toJson(employeesToJson);
    }
}
