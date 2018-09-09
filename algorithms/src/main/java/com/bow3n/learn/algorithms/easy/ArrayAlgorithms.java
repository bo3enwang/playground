package com.bow3n.learn.algorithms.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayAlgorithms {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        int[] nums2 = new int[]{1, 2, 3, 4, 5, 6};
        int k2 = 1;
        int[] nums3 = new int[]{1, 2, 3};
        int k3 = 4;
        int[] nums4 = new int[]{1, 2, 3, 4, 5, 6};
        int k4 = 2;

//        rotate(nums1, k1);
//        rotate(nums2, k2);
//        rotate(nums3, k3);
        rotate1(nums4, k4);
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

    public static void rotate(int[] nums, int k) {
        if (nums.length == 0 || (k %= nums.length) == 0) {
            return;
        }
        int length = nums.length;
        int start = 0;
        int i = 0;
        int cur = nums[0];
        int cnt = 0;
        while (cnt++ < length) {
            i = (i + k) % length;
            int t = nums[i];
            nums[i] = cur;
            if (i == start) {
                ++start;
                ++i;
                cur = nums[i];
            } else {
                cur = t;
            }
        }

        System.out.println(Arrays.toString(nums));
    }

    public static void rotate1(int[] nums, int k) {
        if (nums.length == 0 || (k %= nums.length) == 0) {
            return;
        }
        int length = nums.length;
        int start = 0;
        int i = 0;
        int cur = nums[0];
        for (int j = 0; j < length; j++) {
            i = (i + k) % length;
            int t = nums[i];
            nums[i] = cur;
            if (i == start) {
                ++start;
                ++i;
                cur = nums[i];
            } else {
                cur = t;
            }
        }
    }

    public static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

        }
        return true;
    }
}
