package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cashingDir;

    public DirFileCache(String cashingDir) {
        this.cashingDir = cashingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(cashingDir + "/" + key))) {
            in.lines().forEach(text :: append);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File not found");
        }
        return text.toString();
    }
}
