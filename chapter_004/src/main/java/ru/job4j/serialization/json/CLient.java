package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class CLient {
    @XmlAttribute
    private boolean isService;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int applicationNumber;
    private Engineer engineer;
    @XmlElementWrapper(name = "applications")
    @XmlElement(name = "application")
    private String[] application;

    public CLient() {

    }

    public CLient(boolean isService, String name, int applicationNumber, Engineer engineer, String[] application) {
        this.isService = isService;
        this.name = name;
        this.applicationNumber = applicationNumber;
        this.engineer = engineer;
        this.application = application;
    }

    public boolean isService() {
        return isService;
    }

    public String getName() {
        return name;
    }

    public int getApplicationNumber() {
        return applicationNumber;
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public String[] getApplication() {
        return application;
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
