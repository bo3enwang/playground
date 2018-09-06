package com.bow3n.learn.algorithms.easy;

import java.util.Arrays;

public class ArrayAlgorithms {
    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(nums));
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
//        for (int i = 0, j = prices.length - 1; i < prices.length || j >= 0; i++, j--) {
//            if (prices[i] < prices[j] && i < j) {
//                count += prices[j] - prices[i];
//            }
//        }
        for (int i = 0; i < prices.length; i++) {
            
        }
        return count;
    }
}
