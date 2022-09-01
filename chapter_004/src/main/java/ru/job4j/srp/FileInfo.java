package ru.job4j.srp;

import java.io.File;
import java.io.IOException;

public class FileInfo {
    private String createdDate;
    private String text;

    public FileInfo(String createdDate, String text) {
        this.createdDate = createdDate;
        this.text = text;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate(File file) throws IOException {
        SimpleFileSearcher simpleFileSearcher = new SimpleFileSearcher();
        return simpleFileSearcher.getCreatedDate(file);
    }
}
