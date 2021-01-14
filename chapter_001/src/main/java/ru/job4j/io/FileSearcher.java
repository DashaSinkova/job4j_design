/*
Поиск файлов по критерию [#297695]
 */
package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FileSearcher {
    private static String outputFile;
    public static void main(String[] args) throws IOException {
        JarArg jarArg = new JarArg(args);
        jarArg.checkArgs();
        outputFile = jarArg.getOutputFile();
        if (jarArg.getSearcher() != null) {
          writeToFile(search(new File(jarArg.getDirectory()).toPath(), jarArg.getSearcher()));
         System.out.println("Запись выполнена успешно");
        }
    }
    private static List<Path> search(Path root, SearchFiles searcher) throws IOException {
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
    private static void writeToFile(List<Path> files) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputFile, true)))) {
            for (Path path : files) {
                out.println(path.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
