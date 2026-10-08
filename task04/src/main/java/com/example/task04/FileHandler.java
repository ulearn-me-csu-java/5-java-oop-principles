package com.example.task04;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler{
    public void log(String s)
    {
        try {
            Files.writeString(Paths.get("output.txt"), s+ "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
