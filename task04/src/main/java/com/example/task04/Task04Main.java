package com.example.task04;

public class Task04Main
{
    public static void main(String[] args)
    {
        Logger logger = Logger.getLogger("demo");

        logger.addHandler(new ConsoleHandler());

        MemoryHandler memory = new MemoryHandler(new ConsoleHandler(), 3);
        logger.addHandler(memory);

        logger.info("meow1");
        logger.info("meow2");
        System.out.println(memory.getBufferSize());

        logger.info("meow3");
        System.out.println(memory.getBufferSize());

        logger.info("meow4");
        System.out.println(memory.getBufferSize());
        memory.flush();
        System.out.println(memory.getBufferSize());

        logger.setLevel(Logger.Level.WARNING);
        logger.debug("hidden");
        logger.warning("visible warning");
    }
}