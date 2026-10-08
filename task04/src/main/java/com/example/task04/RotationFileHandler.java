package com.example.task04;
import java.time.temporal.ChronoUnit;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDateTime;
import java.nio.file.StandardOpenOption;

public class RotationFileHandler implements MessageHandler{
    LocalDateTime date = LocalDateTime.now();
    public void log(String s)
    {
        if(ChronoUnit.HOURS.between(date, LocalDateTime.now()) <= 1)
        {
            try {
                Files.writeString(Paths.get("output"+date.toString()+".txt"), s+ "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            date = LocalDateTime.now();
        }
        else
        {
            date = LocalDateTime.now();
            try {
                Files.writeString(Paths.get("output"+date.toString()+".txt"), s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
