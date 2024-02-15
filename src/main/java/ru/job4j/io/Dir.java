package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {

    public static void printFileInfo(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                printFileInfo(Objects.requireNonNull(file.listFiles()));
            } else {
                System.out.printf("filename: %s, file length (in bytes): %d%s",
                        file.getAbsolutePath(),
                        file.length(),
                        System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        printFileInfo(Objects.requireNonNull(file.listFiles()));
    }
}