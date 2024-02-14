package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            String line;
            String startTime = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    if (startTime == null && (parts[0].startsWith("400") || parts[0].startsWith("500"))) {
                        writer.printf("%s;", parts[1]);
                        startTime = parts[1];
                    } else if (startTime != null && (parts[0].startsWith("200") || parts[0].startsWith("300"))) {
                        writer.printf("%s;%s", parts[1], System.lineSeparator());
                        startTime = null;
                    }
                }
            }
            if (startTime != null) {
                writer.printf("%s;", startTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}