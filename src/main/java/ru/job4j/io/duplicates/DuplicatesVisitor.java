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
    private final Map<FileProperty, Path> fMap = new HashMap<>();
    private final List<Path> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        addDuplicates(file, attributes.size(), file.getFileName().toString());
        return FileVisitResult.CONTINUE;
    }

    private void addDuplicates(Path file, long size, String name) {
        FileProperty fileProperty = new FileProperty(size, name);
        if (fMap.containsKey(fileProperty)) {
            duplicates.add(file);
            var v = fMap.get(fileProperty);
            if (!duplicates.contains(v)) {
                duplicates.add(v);
            }
        } else {
            fMap.put(fileProperty, file);
        }
    }

    public void printDuplicates() {
        duplicates.forEach(System.out::println);
    }
}
