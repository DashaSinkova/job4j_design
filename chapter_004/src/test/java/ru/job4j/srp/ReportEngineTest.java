package ru.job4j.srp;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.util.Calendar;


import static ru.job4j.srp.StandartReport.DATE_FORMAT;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new StandartReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHtmlGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        HtmlReport report = new HtmlReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(HtmlReport.DELIMITER)
                .append("<html lang=\"en-us\">")
                .append(HtmlReport.DELIMITER)
                .append("<head>")
                .append(HtmlReport.DELIMITER)
                .append("<meta charset=\"utf-8\">")
                .append(HtmlReport.DELIMITER)
                .append("<meta name=\"viewport\" content=\"width=device-width\">")
                .append(HtmlReport.DELIMITER)
                .append("<title>Report</title>")
                .append(HtmlReport.DELIMITER)
                .append("</head>")
                .append(HtmlReport.DELIMITER)
                .append("<body>")
                .append(HtmlReport.DELIMITER)
                .append("Name; Hired; Fired; Salary;")
                .append(HtmlReport.DELIMITER)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(HtmlReport.DELIMITER)
                .append("</body>")
                .append(HtmlReport.DELIMITER)
                .append("</html>");
        assertThat(report.generateHTML(el -> true)).isEqualTo(expect.toString());
    }
    @Test
    public void whenGeneratedForCounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new AccountingReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary(USD);")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() / AccountingReport.EXCHANGE_RATES).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
    @Test
    public void whenGenerateForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Dasha", now, now, 1000);
        Employee worker2 = new Employee("Masha", now, now, 200);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        Report report = new HrReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())

                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())

                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());

        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGenerateXMLReport() {
        MemStore store = new MemStore();
        Calendar now = new Calendar.Builder().setDate(2000, 01, 01).setTimeOfDay(9, 26, 00).build();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new XMLReport(store);
        String res = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + XMLReport.DELIMITER
                + "<employees>"
                + XMLReport.DELIMITER
                + "    <employee>"
                + XMLReport.DELIMITER
                + "        <fired>2000-02-01T09:26:00+03:00</fired>"
                + XMLReport.DELIMITER
                + "        <hired>2000-02-01T09:26:00+03:00</hired>"
                + XMLReport.DELIMITER
                + "        <name>Ivan</name>"
                + XMLReport.DELIMITER
                + "        <salary>100.0</salary>"
                + XMLReport.DELIMITER
                + "    </employee>"
                + XMLReport.DELIMITER
                + "</employees>"
                + XMLReport.DELIMITER;

        assertThat(report.generate(el -> true)).isEqualTo(res);
    }
    @Test
    public void whenGenerateJsonReport() {
        MemStore store = new MemStore();
        Calendar now = new Calendar.Builder().setDate(2000, 01, 01).setTimeOfDay(9, 26, 00).build();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new JSONReport(store);
        String res = "{"
                + "\"employee\":"
                + "[{\"name\":\"Ivan\","
                + "\"hired\":{\"year\":2000,\"month\":1,\"dayOfMonth\":1,\"hourOfDay\":9,\"minute\":26,\"second\":0},"
                + "\"fired\":{\"year\":2000,\"month\":1,\"dayOfMonth\":1,\"hourOfDay\":9,\"minute\":26,\"second\":0},"
                + "\"salary\":100.0}]"
                + "}";
        assertThat(report.generate(el -> true)).isEqualTo(res);
    }
}
