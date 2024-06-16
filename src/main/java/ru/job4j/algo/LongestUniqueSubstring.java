package ru.job4j.algo;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        var res = new StringBuilder();
        var sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (set.contains(c)) {
                sb.setLength(0);
                sb.append(c);
                set.clear();
                set.add(c);
            } else {
                set.add(c);
                sb.append(c);
                if (sb.length() > res.length()) {
                    res = new StringBuilder(sb);
                }
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        var str = "abcdefgh";
        System.out.println(longestUniqueSubstring(str));
    }
}