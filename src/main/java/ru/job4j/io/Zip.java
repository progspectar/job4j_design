package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    void validateSourceDir(Map<String, String> values) {
        var sourceDir = values.get("d");
        if (sourceDir == null) {
            throwIllegalArgEx("Please provide argument '-d");
        }
        File file = new File(sourceDir);
        if (!file.exists() || !file.isDirectory()) {
            throwIllegalArgEx(String.format("%s doesn't exist or is not a directory", sourceDir));
        }
    }

    void validateExcludedExt(Map<String, String> values) {
        var excludedExt = values.get("e");
        if (excludedExt == null) {
            throwIllegalArgEx("Please provide argument '-e");
        }
        if (!excludedExt.matches("\\.\\w+")) {
            throwIllegalArgEx(String.format("Invalid file extension: %s. Please provide a valid file extension like '.txt'", excludedExt));
        }
    }

    void validateTargetFileName(Map<String, String> values) {
        var targetFileName = values.get("o");
        if (targetFileName == null) {
            throwIllegalArgEx("Please provide argument '-o");
        }

        if (!targetFileName.matches("\\w+.zip")) {
            throwIllegalArgEx(String.format("Invalid zip archive name: %s. Please provide a valid file name with extension 'zip'", targetFileName));
        }
    }

    private static void throwIllegalArgEx(String msg) {
        throw new IllegalArgumentException(msg);
    }

    private Map<String, String> validateArgs(String[] args) {
        if (args.length != 3) {
            throwIllegalArgEx("only 3 arguments accepted, usage: java -d=<directory> -e=<excluded extension> -o=<target zip file>");
        }
        ArgsName argsName = ArgsName.of(args);
        Map<String, String> values = argsName.getValues();
        validateSourceDir(values);
        validateExcludedExt(values);
        validateTargetFileName(values);
        return values;
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
        Map<String, String> values = zip.validateArgs(args);
        var sourceDir = values.get("d");
        var excludedExt = values.get("e");
        var targetFileName = values.get("o");
        final var sources = Search.search(Paths.get(sourceDir),
                path -> !path.toFile().getName().endsWith(excludedExt));
        String pathToZip = sourceDir + File.separator + targetFileName;
        zip.packFiles(sources, new File(pathToZip));
    }
}