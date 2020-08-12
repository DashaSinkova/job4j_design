package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        List<String> res = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(list :: add);
            for (String line : list) {
                if (line.contains("404")) {
                    String[] arr = line.split(" ");
                    if (arr[arr.length - 2].equals("404")) {
                        res.add(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.stream().forEach(el -> out.write(el + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}