package com.bow3n.learn.algorithms.leetcode;

import java.util.Arrays;

/**
 * <custom href="https://leetcode-cn.com/problems/remove-element/">移除元素</custom>
 * <p>
 * 执行用时: 10 ms, 在Remove Element的Java提交中击败了29.07% 的用户
 *
 * @author bowen
 */
public class RemoveElement {
    //TODO 需要优化
    public static int removeElement(int[] nums, int val) {
        int start = -1;
        int end = -1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (start == -1) {
                if (num == val) {
                    start = i;
                    end = i;
                }
            } else {
                if (num == val) {
                    end++;
                }
            }
        }

        if (start != -1) {
            int pos = start;
            for (int i = end + 1; i < nums.length; i++) {
                nums[pos++] = nums[i];
            }
            return nums.length - 1 - (end - start);
        }
        return nums.length;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(arr, 4));
        System.out.println(Arrays.toString(arr));
    }
}
