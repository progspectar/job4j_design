package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnalysisTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        String source = tempDir.resolve("server.log").toFile().getAbsolutePath();
        String target  = tempDir.resolve("target.txt").toFile().getAbsolutePath();
        createServerLog(source);
        Analysis analysis = new Analysis();
        analysis.unavailable(source,target);
        List<String> list = getListFromFile(target);
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo("10:57:01;11:02:02;");
    }

    private List<String> getListFromFile(String str) throws IOException {
        List<String> list = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(str))) {
            input.lines().forEach(list::add);
        }
        return list;
    }

    void createServerLog(String fileName) throws FileNotFoundException {
           try (PrintWriter output = new PrintWriter(fileName)) {
            output.println("200 10:56:01" + System.lineSeparator() +
                    "500 10:57:01" +System.lineSeparator() +
                    "400 10:58:01" +System.lineSeparator() +
                    "500 10:59:01" +System.lineSeparator() +
                    "400 11:01:02" +System.lineSeparator() +
                    "300 11:02:02");
        }
    }
}