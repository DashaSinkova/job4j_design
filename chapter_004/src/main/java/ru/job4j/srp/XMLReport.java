package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private Store store;

    public XMLReport(Store store) {
        this.store = store;
    }
    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        List<Employee> employees = store.findBy(filter);

        Employees employeesWrap = new Employees();
        employeesWrap.setEmployee(employees);

        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employeesWrap, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
