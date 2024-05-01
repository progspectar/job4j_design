package ru.job4j.io;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
    }

    public static void main(String[] args) throws Exception {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);

        if (argsName.get("path") == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }

        handle(argsName);
    }
}