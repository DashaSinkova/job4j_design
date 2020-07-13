package ru.job4j.collection;


public class Article {
    public static boolean generateBy(String origin, String line) {
        String[] strings = line.split(" ");
        boolean res = true;
        for (String string : strings) {
            if (!origin.contains(string)) {
                res = false;
                break;
            }
        }
        return res;
    }
}
