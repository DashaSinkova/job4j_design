package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();
    public Config(String path) {
        this.path = path;
    }
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().filter(el -> !el.equals("") & !el.contains("#")).forEach(el -> {
                String[] res = el.split("=");
                values.put(res[0], res[1]);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String value(String key) {
        //System.out.println(values.toString());
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
