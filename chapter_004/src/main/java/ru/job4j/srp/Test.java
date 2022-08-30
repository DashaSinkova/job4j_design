package ru.job4j.srp;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        SimpleFileSearcher simpleFileSearcher = new SimpleFileSearcher();
        System.out.println(simpleFileSearcher.getCreatedDate(new File("C:\\Мои файлы\\Организационные материалы\\English.docx")));
        simpleFileSearcher.getFiles(new File("C:\\Мои файлы\\Организационные материалы")).forEach(el -> System.out.println(el.getName()));
    }
}
