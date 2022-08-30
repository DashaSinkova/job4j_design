package ru.job4j.srp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleFileSearcher implements FileSearcher {
    @Override
    public String getCreatedDate(File file) throws IOException {
        BasicFileAttributes  attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        FileTime time = attr.creationTime();
        LocalDateTime localDateTime = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDate = localDateTime.format(formatter);
        return formatDate;
    }

    @Override
    public List<File> getFiles(File directory) {
        List<File> result = null;
        if (directory.isDirectory()) {
            result = Arrays.stream(directory.listFiles()).collect(Collectors.toList());
        }
        return result;
    }
}
