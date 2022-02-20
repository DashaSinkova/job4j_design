package ru.job4j.gc.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cashingDir;

    public DirFileCache(String cashingDir) {
        this.cashingDir = cashingDir;
    }

    @Override
    protected String load(String key) {
        String res = null;
        try {
            System.out.println("File will load into cache!");
            res = Files.readString(Path.of(cashingDir, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
       return res;
    }
}
