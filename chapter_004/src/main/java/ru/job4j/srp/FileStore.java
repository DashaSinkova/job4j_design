package ru.job4j.srp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileStore {

    private static final FileStore STORE = new FileStore();
    private List<File> files = new ArrayList<>();

    private FileStore() {
    }

    public File add(File file) {
        files.add(file);
        return file;
    }

    public static FileStore getStore() {
        return STORE;
    }

    public static void main(String[] args) {
        FileStore store = getStore();
        FileStore store1 = getStore();
        if (store.equals(store1)) {
            System.out.println("Объекты равны");
        }
    }
}
