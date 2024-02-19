package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> fMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        addDuplicates(file, attributes.size(), file.getFileName().toString());
        return FileVisitResult.CONTINUE;
    }

    private void addDuplicates(Path file, long size, String name) {
        FileProperty fileProperty = new FileProperty(size, name);
        fMap.computeIfAbsent(fileProperty, k -> new ArrayList<>())
                .add(file);
    }

    public void printDuplicates() {
        fMap
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e ->
                        System.out.println("filename: "
                                + e.getKey().getName()
                                + " ,size: "
                                + e.getKey().getSize()
                                + System.lineSeparator()
                                + e.getValue()));
    }

}

