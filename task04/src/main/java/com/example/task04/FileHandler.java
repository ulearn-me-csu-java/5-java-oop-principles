package com.example.task04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler {

    private final Path filePath;

    public FileHandler(Path filePath){
        this.filePath = filePath;
    }
    @Override
    public void handler(String message){
        try {
            Files.writeString(filePath,message,StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        catch (IOException ex){
            throw new RuntimeException();
        }
    }
}