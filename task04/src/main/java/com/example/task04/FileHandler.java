package com.example.task04;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler
{
    private final File file;

    public FileHandler(File file)
    {
        this.file = file;
    }

    public FileHandler(String fileName)
    {
        this(new File(fileName));
    }

    public File getFile()
    {
        return file;
    }

    @Override
    public void handle(String message)
    {
        appendToFile(file, message);
    }

    protected static void appendToFile(File file, String message)
    {
        try
        {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists())
            {
                parent.mkdirs();
            }
            Files.writeString(
                    file.toPath(),
                    message + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }
}