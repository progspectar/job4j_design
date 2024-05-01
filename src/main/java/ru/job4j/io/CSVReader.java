package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        if (!out.equals("stdout")) {
            File outputFile = new File(out);
            PrintStream printStream = new PrintStream(new FileOutputStream(outputFile));
            System.setOut(printStream);
        }
        Scanner scanner = new Scanner(new FileInputStream(path));
        List<String> filterColumns = Arrays.stream(filter.split(",")).toList();
        boolean isFirst = true;
        List<Integer> headerIdx = new ArrayList<>();
        while (scanner.hasNextLine()) {
            List<String> data = Arrays.stream(scanner.nextLine().split(delimiter)).toList();
            StringJoiner sj = new StringJoiner(delimiter);
            if (isFirst) {
                headerIdx = getHeaderList(data, filterColumns);
                for (int index : headerIdx) {
                    sj.add(data.get(index));
                }
                System.out.println(sj);
                isFirst = false;
            } else {
                for (int index : headerIdx) {
                    sj.add(data.get(index));
                }
                System.out.println(sj);
            }
        }
        scanner.close();
    }

    private static List<Integer> getHeaderList(List<String> data, List<String> filterColumns) {
        List<Integer> res = new ArrayList<>();
        for (String field : filterColumns) {
            int index = data.indexOf(field);
            if (index != -1) {
                res.add(index);
            }
        }
        return res;
    }

    private static void validate(ArgsName argsName) {
        String path = argsName.get("path");
        checkEmpty(argsName, "path");
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File not found or is not a valid file: " + path);
        }
        checkEmpty(argsName, "delimiter");
        checkEmpty(argsName, "out");
        checkEmpty(argsName, "filter");
    }

    private static void checkEmpty(ArgsName argsName, String key) {
        if (argsName.get(key).isEmpty()) {
            throw new IllegalArgumentException(key + " is empty");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}