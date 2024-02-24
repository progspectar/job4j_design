package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void throwIllegalArgEx(String msg) {
        throw new IllegalArgumentException(msg);
    }

    private ArgsName validateArgs(String[] args) {
        if (args.length != 3) {
            throwIllegalArgEx("only 3 arguments accepted, usage: java -d=<directory> -e=<excluded extension> -o=<target zip file>");
        }
        ArgsName argsName = ArgsName.of(args);
        var sourceDir = argsName.get("d");
        if (sourceDir == null || sourceDir.isBlank()) {
            throwIllegalArgEx("Please provide argument '-d");
        }
        File file = new File(sourceDir);
        if (!file.exists() || !file.isDirectory()) {
            throwIllegalArgEx(String.format("%s doesn't exist or is not a directory", sourceDir));
        }
        var excludedExt = argsName.get("e");
        if (excludedExt == null || excludedExt.isBlank()) {
            throwIllegalArgEx("Please provide argument '-e");
        }
        if (!excludedExt.matches("\\.\\w+")) {
            throwIllegalArgEx(String.format("Invalid file extension: %s. Please provide a valid file extension like '.txt'", excludedExt));
        }
        var targetFileName = argsName.get("o");
        if (targetFileName == null || targetFileName.isBlank()) {
            throwIllegalArgEx("Please provide argument '-o");
        }
        if (!targetFileName.matches("\\w+.zip")) {
            throwIllegalArgEx(String.format("Invalid zip archive name: %s. Please provide a valid file name with extension 'zip'", targetFileName));
        }
        return argsName;
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                File file = source.toFile();
                zip.putNextEntry(new ZipEntry(file.getName()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = zip.validateArgs(args);
        var sourceDir = argsName.get("d");
        var excludedExt = argsName.get("e");
        var targetFileName = argsName.get("o");
        final var sources = Search.search(Paths.get(sourceDir),
                path -> !path.toFile().getName().endsWith(excludedExt));
        String pathToZip = sourceDir + File.separator + targetFileName;
        zip.packFiles(sources, new File(pathToZip));
    }
}