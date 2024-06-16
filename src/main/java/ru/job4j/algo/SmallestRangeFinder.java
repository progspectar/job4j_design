package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int count = 1;
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] == nums[right - 1]) {
                count = 1;
            } else {
                count++;
            }
            if (count == k) {
                return new int[]{right - k + 1, right};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}