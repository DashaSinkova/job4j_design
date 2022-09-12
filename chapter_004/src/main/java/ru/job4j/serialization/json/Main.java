package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        serializeJSON();
        serializeXML();
    }

    public static void serializeJSON() {
        final CLient cLient = new CLient(true, "Job4j", 134,
                new Engineer("Dasha", "+7-000-000-00-00"), new String[]{"App1", "App2"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cLient));

        final String clientJson =
                "{"
                        + "\"isService\":true,"
                        + "\"name\":\"Job4j\","
                        + "\"applicationNumber\":134,"
                        + "\"engineer\":"
                        + "{"
                        + "\"name\":\"Dasha\","
                        + "\"phone\":\"+7-000-000-00-00\""
                        + "},"
                        + "\"application\":"
                        + "[\"App1\", \"App2\"]"
                        + "}";

        final CLient clientMod = gson.fromJson(clientJson, CLient.class);
        System.out.println(clientMod);
    }

    public static void serializeXML() throws Exception {
        CLient cLient = new CLient(true, "Job4j", 134,
                new Engineer("Dasha", "+7-000-000-00-00"), new String[]{"App1", "App2"});
        JAXBContext context = JAXBContext.newInstance(CLient.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(cLient, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            CLient result = (CLient) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

}
