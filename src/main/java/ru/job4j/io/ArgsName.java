package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        var value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return value;
    }

    private void parse(String[] args) {
        for (String arg : args) {
            checkArg(arg);
            String[] parts = arg.substring(1).split("=", 2);
            values.put(parts[0], parts[1]);
        }
    }

    private void checkArg(String arg) {
        if (arg.charAt(0) != '-') {
            throwIllegalArgEx(String.format("Error: This argument '%s' does not start with a '-' character", arg));
        }
        String[] parts = arg.substring(1).split("=", 2);
        if (parts.length < 2) {
            throwIllegalArgEx(String.format("Error: This argument '%s' does not contain an equal sign", arg));
        }
        if (parts.length == 2) {
            var k = parts[0];
            var v = parts[1];
            if (k.isBlank()) {
                throwIllegalArgEx(String.format("Error: This argument '%s' does not contain a key", arg));
            }
            if (v.isBlank()) {
                throwIllegalArgEx(String.format("Error: This argument '%s' does not contain a value", arg));
            }
        }
    }

    private static void throwIllegalArgEx(String msg) {
        throw new IllegalArgumentException(msg);
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throwIllegalArgEx("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(args);
    }
}