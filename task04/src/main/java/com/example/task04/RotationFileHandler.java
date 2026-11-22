package com.example.task04;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler
{
    private static final DateTimeFormatter FILE_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private final String baseFileName;
    private ChronoUnit rotationUnit;

    public RotationFileHandler(String baseFileName, ChronoUnit rotationUnit)
    {
        this.baseFileName = baseFileName;
        this.rotationUnit = rotationUnit;
    }

    public RotationFileHandler(String baseFileName)
    {
        this(baseFileName, ChronoUnit.HOURS);
    }

    public ChronoUnit getRotationUnit()
    {
        return rotationUnit;
    }

    public void setRotationUnit(ChronoUnit rotationUnit)
    {
        this.rotationUnit = rotationUnit;
    }

    @Override
    public void handle(String message)
    {
        LocalDateTime periodStart = LocalDateTime.now().truncatedTo(rotationUnit);
        String suffix = periodStart.format(FILE_DATE_FORMAT);
        File file = new File(baseFileName + "_" + suffix + ".log");
        FileHandler.appendToFile(file, message);
    }
}