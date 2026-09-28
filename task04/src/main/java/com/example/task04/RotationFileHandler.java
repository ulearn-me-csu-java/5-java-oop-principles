package com.example.task04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RotationFileHandler implements MessageHandler{

    private final Path pathToDir;
    private final String baseNameFile;
    private final DateTimeFormatter formatter;

    public RotationFileHandler(Path pathToDir, String baseNameFile, DateTimeFormatter formatter){
        this.pathToDir = pathToDir;
        this.baseNameFile = baseNameFile;
        this.formatter = formatter;
    }


    @Override
    public void handler(String message){
        String DateTimeNow = LocalTime.now().format(formatter);
        Path pathToFile = pathToDir.resolve(baseNameFile+DateTimeNow+".log");

        try {
            Files.createDirectories(pathToDir);
            Files.writeString(pathToFile,message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        catch (IOException ex){
            throw new RuntimeException();
        }
    }
}