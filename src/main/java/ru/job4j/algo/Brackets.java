package ru.job4j.algo;

import java.util.Deque;
import java.util.LinkedList;

class Brackets {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}