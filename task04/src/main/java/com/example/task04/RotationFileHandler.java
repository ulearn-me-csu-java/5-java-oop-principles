package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private String fileNamePattern;
    private ChronoUnit rotationUnit;

    public RotationFileHandler() {
        this("log_%s.txt", ChronoUnit.HOURS);
    }

    public RotationFileHandler(ChronoUnit rotationUnit) {
        this("log_%s.txt", rotationUnit);
    }

    public RotationFileHandler(String fileNamePattern) {
        this(fileNamePattern, ChronoUnit.HOURS);
    }

    public RotationFileHandler(String fileNamePattern, ChronoUnit rotationUnit) {
        this.fileNamePattern = fileNamePattern != null ? fileNamePattern : "log_%s.txt";
        this.rotationUnit = rotationUnit != null ? rotationUnit : ChronoUnit.HOURS;
    }

    public ChronoUnit getRotationUnit() {
        return rotationUnit;
    }

    public void setRotationUnit(ChronoUnit rotationUnit) {
        this.rotationUnit = rotationUnit;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public void setFileNamePattern(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    public String getCurrentFileName() {
        return resolveFileName(LocalDateTime.now());
    }

    public String resolveFileName(LocalDateTime time) {
        DateTimeFormatter formatter;
        if (rotationUnit == ChronoUnit.SECONDS) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        } else if (rotationUnit == ChronoUnit.MINUTES) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
        } else if (rotationUnit == ChronoUnit.HOURS) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
        } else if (rotationUnit == ChronoUnit.DAYS) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        } else if (rotationUnit == ChronoUnit.MONTHS) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        } else if (rotationUnit == ChronoUnit.YEARS) {
            formatter = DateTimeFormatter.ofPattern("yyyy");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
        }

        String timeStr = time.format(formatter);
        if (fileNamePattern.contains("%s")) {
            return String.format(fileNamePattern, timeStr);
        } else if (fileNamePattern.contains("{0}")) {
            return java.text.MessageFormat.format(fileNamePattern, timeStr);
        } else {
            int dot = fileNamePattern.lastIndexOf('.');
            if (dot >= 0) {
                return fileNamePattern.substring(0, dot) + "_" + timeStr + fileNamePattern.substring(dot);
            } else {
                return fileNamePattern + "_" + timeStr + ".txt";
            }
        }
    }

    @Override
    public void handle(String message) {
        String currentFileName = getCurrentFileName();
        File file = new File(currentFileName);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
