package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HrReport implements Report {

    private Store store;

    public HrReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> sortedEmployee = store.findBy(filter).stream().sorted(new EmployeesDescSalary().reversed()).collect(Collectors.toList());
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : sortedEmployee) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
