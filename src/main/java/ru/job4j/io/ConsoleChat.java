package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        boolean answerOn = true;
        while (run) {
            String input = sc.nextLine();
            log.add(input);
            run = !OUT.equals(input);
            if (STOP.equals(input)) {
                answerOn = false;
            }
            if (CONTINUE.equals(input)) {
                answerOn = true;
            }
            if (answerOn && run) {
                String answer = botPhrases.get(new Random().nextInt(botPhrases.size()));
                System.out.println(answer);
                log.add(answer);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> res = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            res = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, false))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/botLog.txt", "data/botAnswers.txt");
        consoleChat.run();
    }
}