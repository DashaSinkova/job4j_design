/*
Поиск файлов по критерию [#297695]
 */
package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FileSearcher {
    static String outputFile = "";
    public static void main(String[] args) throws IOException {
        String directory = "";
        String nameFile = "";
        SearchFiles searcher = null;
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder and expansion are null. Please set them in Edit Configuration -> Program agruments");
        }
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    directory = args[i + 1];
                    break;
                case "-n":
                    nameFile = args[i + 1];
                    break;
                case "-m":
                    String[] mas = nameFile.split("\\.");
                    final String m = mas[1];
                    searcher = new SearchFiles(path -> path.toFile().getName().endsWith(m));
                    break;
                case "-f":
                    final String f = nameFile;
                    searcher = new SearchFiles(path -> path.toFile().getName().contains(f));
                    break;
                case "-r":
                    final String r = nameFile;
                    searcher = new SearchFiles(path -> path.toFile().getName().matches(r));
                    break;
                case "-o":
                    outputFile = args[i + 1];
                    break;
                default:
            }
        }
        if (searcher != null) {
          writeToFile(search(new File(directory).toPath(), searcher));
         System.out.println("Запись выполнена успешно");
        }
    }
    private static List<Path> search(Path root, SearchFiles searcher) throws IOException {
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
    private static void writeToFile(List<Path> files) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFile, true))) {
            for (Path path : files) {
                out.write(path + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
