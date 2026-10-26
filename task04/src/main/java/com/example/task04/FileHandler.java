package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler {
    private final String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    @Override
    public void handle(String formattedMessage) {
        try (FileWriter writer = new FileWriter(filename, true)) { // true = дописывать в конец
            writer.write(formattedMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
