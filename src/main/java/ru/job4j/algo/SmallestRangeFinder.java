package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int left = 0;
        int rigth = 1;
        boolean flag = false;
        for (; rigth < nums.length; rigth++) {
            if (nums[rigth - 1] == nums[rigth]) {
                left = rigth;
            }
            if ((rigth - left) + 1 == k) {
                flag = true;
                break;
            }
        }
        return flag ? new int[]{left, rigth} : null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 4;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}