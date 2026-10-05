package com.example.task04;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler{
    private final String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    @Override
    public void handler(String message) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка! Не удалось записать в файл",e);
        }
    }
}