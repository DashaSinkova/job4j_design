package ru.job4j.collection;
import java.util.*;
import java.util.stream.Collectors;

public class Article {
    public static boolean generateBy(String origin, String line) {
        boolean res = true;
        String[] stringOrigin = origin.replaceAll("\\p{Punct}", "").split(" ");
       HashMap<String, String> origins = new HashMap<>();
        origins.putAll(Arrays.stream(stringOrigin).collect(Collectors.toMap(el -> el, el -> el, (first, second) -> first)));
        String[] stringLine = line.split(" ");
        for (String str : stringLine) {
            if (!origins.containsKey(str)) {
                res = false;
                break;
            }
        }
        return res;
    }
}
