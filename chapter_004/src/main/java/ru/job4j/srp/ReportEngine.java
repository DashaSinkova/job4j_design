package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
    public String htmlGenerate(Predicate<Employee> filter) {
        return "<!DOCTYPE html>"
                + System.lineSeparator()
                + "<html lang=\"en-us\">"
                + System.lineSeparator()
                + "<head>"
                + System.lineSeparator()
                + "<meta charset=\"utf-8\">"
                + System.lineSeparator()
                + "<meta name=\"viewport\" content=\"width=device-width\">"
                + System.lineSeparator()
                + "<title>Report</title>"
                + System.lineSeparator()
                + "</head>"
                + System.lineSeparator()
                + "<body>"
                + System.lineSeparator()
                + generate(filter)
                + "</body>"
                + System.lineSeparator()
                + "</html>";
    }

    public String generateForCounting(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary(USD);")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() / 60).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public String generateForHR(Predicate<Employee> filter) {
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
