package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String str : lines) {
                if (Integer.valueOf(str) % 2 == 0) {
                    System.out.println(str + " четное");
                } else {
                    System.out.println(str + " нечетное");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
