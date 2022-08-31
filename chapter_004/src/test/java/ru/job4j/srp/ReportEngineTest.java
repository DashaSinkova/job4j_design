package ru.job4j.srp;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.function.Predicate;

import static ru.job4j.srp.ReportEngine.DATE_FORMAT;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHtmlGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html lang=\"en-us\">")
                .append(System.lineSeparator())
                .append("<head>")
                .append(System.lineSeparator())
                .append("<meta charset=\"utf-8\">")
                .append(System.lineSeparator())
                .append("<meta name=\"viewport\" content=\"width=device-width\">")
                .append(System.lineSeparator())
                .append("<title>Report</title>")
                .append(System.lineSeparator())
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.htmlGenerate(el -> true)).isEqualTo(expect.toString());
    }
    @Test
    public void whenGeneratedForCounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary(USD);")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() / 60).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generateForCounting(em -> true)).isEqualTo(expect.toString());
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
        ReportEngine engine = new ReportEngine(store);
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

        assertThat(engine.generateForHR(em -> true)).isEqualTo(expect.toString());
    }
}
