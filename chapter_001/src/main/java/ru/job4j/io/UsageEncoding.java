package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.List;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            int read;
            while ((read = reader.read()) > 0) {
                builder.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeInFile(String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            writer.write(data + System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        for (String s : strings) {
            encoding.writeInFile(path, s);
        }
        System.out.println(encoding.readFile(path));
    }
}
