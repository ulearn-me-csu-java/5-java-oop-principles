package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler {

    private final String fileName;

    public FileHandler() {
        this("log.txt");
    }

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public FileHandler(File file) {
        this(file.getPath());
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void handle(String message) {
        File file = new File(fileName);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
