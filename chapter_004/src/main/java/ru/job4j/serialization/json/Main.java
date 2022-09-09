package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
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
}
