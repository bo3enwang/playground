package com.bow3n.learn.algorithms.easy;

import java.util.Arrays;

public class ArrayAlgorithms {
    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        int[] nums2 = new int[]{1, 2, 3, 4, 5};
        System.out.println(maxProfit(nums2));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 1;
        int current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != current) {
                nums[index] = nums[i];
                current = nums[i];
                index++;
            }
        }
        return index;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int count = 0;
        int max = 0;
        int buyIn = 0;

        for (int i = 1; i < prices.length; i++) {
            int earn = prices[i] - prices[buyIn];
            if (earn > max) {
                max = earn;
                if (i == prices.length - 1) {
                    count += earn;
                }
            } else {
                count += max;
                max = 0;
                buyIn = i;
            }
        }
        return count;
    }

    public void rotate(int[] nums, int k) {

    }
}
