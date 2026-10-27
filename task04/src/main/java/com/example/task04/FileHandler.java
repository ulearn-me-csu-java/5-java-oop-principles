package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler {
    private String name = "log.txt";
    private String directory = new java.io.File(".").getAbsolutePath();
    private File file;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    private File getFile() {
        return new File(directory, name);
    }

    @Override
    public void handle(String message) {
        try (FileWriter writer = new FileWriter(getFile(), true)) {
            writer.write(message + "\n");
            System.out.println("Сообщение успешно записано в файл!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}