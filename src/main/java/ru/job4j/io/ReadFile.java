package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new FileReader("data/input.txt"))) {
            input.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//package ru.job4j.io;
//
//import java.io.FileInputStream;
//
//public class ReadFile {
//    public static void main(String[] args) {
//        try (FileInputStream input = new FileInputStream("data/input.txt")) {
//            StringBuilder text = new StringBuilder();
//            int read;
//            while ((read = input.read()) != -1) {
//                text.append((char) read);
//            }
//            System.out.println(text);
//            String[] lines = text.toString().split(System.lineSeparator());
//            for (String line : lines) {
//                System.out.println(line);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}