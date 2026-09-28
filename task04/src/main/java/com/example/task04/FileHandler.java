package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Обработчик, выводящий сообщения в файл
 */
public class FileHandler implements MessageHandler {

    private final String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void handle(String message) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
