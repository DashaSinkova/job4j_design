package ru.job4j.srp;


import java.util.function.Predicate;

public class HtmlReport extends StandartReport {

    public static final String DELIMITER = System.lineSeparator();
    public HtmlReport(Store store) {
        super(store);
    }

    public String generateHTML(Predicate<Employee> filter) {
        return "<!DOCTYPE html>"
                + DELIMITER
                + "<html lang=\"en-us\">"
                + DELIMITER
                + "<head>"
                + DELIMITER
                + "<meta charset=\"utf-8\">"
                + DELIMITER
                + "<meta name=\"viewport\" content=\"width=device-width\">"
                + DELIMITER
                + "<title>Report</title>"
                + DELIMITER
                + "</head>"
                + DELIMITER
                + "<body>"
                + DELIMITER
                + generate(filter)
                + "</body>"
                + DELIMITER
                + "</html>";
    }
}
