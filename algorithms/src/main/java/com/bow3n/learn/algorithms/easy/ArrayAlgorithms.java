package com.bow3n.learn.algorithms.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayAlgorithms {
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

    public static boolean containsDuplicate_a(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        return nums.length != s.size();
    }

    public void rotate1(int[] nums, int k) {
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

    public boolean containsDuplicate_b(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test_containsDuplicate() {
        Assertions.assertTrue(containsDuplicate_a(new int[]{1, 2, 3, 1}));
        Assertions.assertTrue(containsDuplicate_a(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        Assertions.assertFalse(containsDuplicate_a(new int[]{1, 2, 3, 4}));

        Assertions.assertTrue(containsDuplicate_b(new int[]{1, 2, 3, 1}));
        Assertions.assertTrue(containsDuplicate_b(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        Assertions.assertFalse(containsDuplicate_b(new int[]{1, 2, 3, 4}));
    }


    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * @param nums 非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次
     * @return 只出现了一次的元素。
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || i == nums.length - 1) {
                if (i == 0 && nums[0] != nums[1]) {
                    return nums[0];
                }
                if (i == nums.length - 1 && nums[nums.length - 1] != nums[nums.length - 2]) {
                    return nums[nums.length - 1];
                }
            } else {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
        }
        return nums[0];
    }

    public int singleNumber_a(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int aA : A) {
            rst ^= aA;
        }
        return rst;
    }

    @Test
    public void test_singleNumber() {
        Assertions.assertEquals(1, singleNumber(new int[]{2, 2, 1}));
        Assertions.assertEquals(4, singleNumber(new int[]{4, 1, 2, 1, 2}));
        Assertions.assertEquals(4, singleNumber_a(new int[]{4, 1, 2, 1, 2}));
    }
}
