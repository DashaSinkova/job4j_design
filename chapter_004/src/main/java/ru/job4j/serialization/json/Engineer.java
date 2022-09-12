package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engineer")
public class Engineer {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String phone;

    public Engineer() {

    }

    public Engineer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
