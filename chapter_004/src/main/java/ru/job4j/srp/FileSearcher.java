package ru.job4j.srp;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface FileSearcher {
    String getCreatedDate(File file) throws IOException;
    List<File> getFiles(File directory);
}
