package com.example.task04;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler{
    private final String baseName; //базовое имя файла
    private final ChronoUnit rotationUnit; //через какой интервал создавать новый файл
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
    private LocalDateTime curretndPeriod;
    private Path currentFile;

    public RotationFileHandler(String baseName, ChronoUnit rotationUnit){
        this.baseName = baseName;
        this.rotationUnit = rotationUnit;
        rotateIfNeeded(); //определяем, в какой файл будем записывать в первый раз
    }

    @Override
    public void handle(String message){
        rotateIfNeeded();
        try{
            Files.writeString(
                    currentFile,
                    message + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e){
            throw new RuntimeException("Не удалось записать в файл", e);
        }
    }
    private void rotateIfNeeded(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime period = now.truncatedTo(rotationUnit);

        if (!period.equals(curretndPeriod)){ //изменился ли период?
            curretndPeriod = period;
            String suffix = period.format(formatter);
            currentFile = Path.of(baseName + "_" + suffix + ".log");
        }
    }
}
