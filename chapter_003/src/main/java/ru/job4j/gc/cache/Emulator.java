package ru.job4j.gc.cache;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Класс для работы с пользователем.
 * Предоставить пользователю возможности:
 *
 * - указать кэшируемую директорию
 *
 * - загрузить содержимое файла в кэш
 *
 * - получить содержимое файла из кэша
 */
public class Emulator {

    private static Boolean create = false;
    private static DirFileCache cache = null;

    public static void main(String[] args) {
        for (;;) {
            File dir = new File("C:\\Projects\\job4j_design");
            File[] dirs = getDirs(dir);
            outputFiles(dirs);
            System.out.println("Choose a directory:");
            File inputDir = Path.of(userInput()).toFile();
            if (exist(dirs, inputDir)) {
                choseFile(inputDir);
               } else {
                   System.out.println("=====File not found=====");
               }
            }
        }

    private static void choseFile(File dir)  {
        List<String> files = getFiles(dir);
        System.out.println(files);
        System.out.println("Choose a file:");
        String userInput = userInput();
        if (files.contains(userInput)) {
            cacheFile(userInput, dir);
        }
    }
    private static void cacheFile(String file, File dir) {
        if (!create) {
            cache = new DirFileCache(dir.toString());
            create = true;
        }
        System.out.println(cache.get(file));
    }
    private static boolean exist(File[] files, File file) {
        boolean res = false;
        if (Arrays.stream(files).anyMatch(p -> p.equals(file))) {
            res = true;
        } else {
            System.out.println("=====File not found. Try again=====");
        }
        return  res;
    }
        private static File[] getDirs(File dir) {
            File[] files = null;
            if (dir.isDirectory()) {
                files = dir.listFiles(p -> p.isDirectory());
            }

            return files;
        }

        private static void outputFiles(File[]files) {
            for (File file : files) {
                System.out.println(file.getAbsoluteFile());
            }
        }

        private static List<String> getFiles(File dir) {
            List<String> res = new ArrayList<>();
            File[] files = dir.listFiles(p -> p.isFile());
            for (File file : files) {
                res.add(file.getName());
            }
            return res;
        }

        private static String userInput() {
        String res;
            Scanner scanner = new Scanner(System.in);
            res = scanner.nextLine();
        return res;
        }
    }



