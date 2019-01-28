package com.bow3n.learn.algorithms.leetcode;

/**
 * @author bowen
 * https://leetcode-cn.com/problems/sort-array-by-parity/submissions/
 * <p>
 * 执行用时: 23 ms, 在Sort Array By Parity的Java提交中击败了60.36% 的用户
 * <p>
 * TODO 优化
 */
public class q905_sort_array_by_parity {
    public int[] sortArrayByParity(int[] A) {
        final int length = A.length;
        int[] arr = new int[length];
        int start = 0;
        int end = length - 1;
        for (int aA : A) {
            if (aA % 2 == 0) {
                arr[start] = aA;
                start++;
            } else {
                arr[end] = aA;
                end--;
            }
        }
        return arr;
    }
}
