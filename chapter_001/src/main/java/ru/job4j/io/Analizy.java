package ru.job4j.io;

import java.io.*;


public class Analizy {
    boolean flag = false;
    public static void main(String[] args) {
        new Analizy().unavailable("server.log", "res.log");
    }
    public void unavailable(String source, String target) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(el -> {
                if (el.contains("400") || el.contains("500")) {
                    if (!flag) {
                        stringBuilder.append(el.split(" ")[1] + ";");
                        flag = true;
                    }
                } else {
                    if (flag) {
                        stringBuilder.append(el.split(" ")[1] + ";" + System.lineSeparator());
                        flag = false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
           out.write(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
