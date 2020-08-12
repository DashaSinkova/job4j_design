package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(lines :: add);
            System.out.println(lines.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String res = "";
            boolean flag = false;
           for (String str : lines) {
               if (str.contains("400") || str.contains("500")) {
                   if (!flag) {
                       res += str.split(" ")[1] + ";";
                       flag = true;
                   }
               } else {
                   if (flag == true) {
                       res += str.split(" ")[1] + ";";
                       flag = false;
                       out.write(res + System.lineSeparator());
                       res = "";
                   }
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("server.log", "res.log");
    }
}
