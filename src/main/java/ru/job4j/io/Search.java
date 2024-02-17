package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide two arguments: <starting_folder> <file_extension>");
        }
        Path startDir = Path.of(args[0]);
        String fileExtension = args[1];
        if (!Files.exists(startDir)) {
            throw new IllegalArgumentException("The starting directory does not exist: " + args[0]);
        }
        if (!Files.isDirectory(startDir)) {
            throw new IllegalArgumentException("The starting directory is not a directory: " + args[0]);
        }
        if (!fileExtension.matches("\\.\\w+")) {
            throw new IllegalArgumentException(String.format("Non valid file extension: \"%s\". Provide a valid file extension like (e.g.: '.txt'.)", fileExtension));
        }
    }
}