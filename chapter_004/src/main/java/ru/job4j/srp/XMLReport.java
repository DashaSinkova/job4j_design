package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {

    public static final String DELIMITER = "\n";
    private JAXBContext context;
    private Marshaller marshaller;
    private Store store;

    public XMLReport(Store store) {
        this.store = store;
        initialize();
    }

    private void initialize() {
        try {
            context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        String xml = "";
        Employees employeesWrap = new Employees();
        employeesWrap.setEmployee(employees);
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(employeesWrap, writer);
                xml = writer.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xml;
    }
}
