package com.bow3n.learn.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array/submissions/
 * 执行用时: 54 ms, 在N-Repeated Element in Size 2N Array的Java提交中击败了18.33% 的用户
 * TODO too fucking slow
 * @author bowen
 */
public class q961_n_repeated_element_in_size_2n_array {
    public int repeatedNTimes(int[] A) {
        int n = A.length / 2;
        Map<Integer, Integer> integerIntegerMap = new HashMap<>();
        for (int a : A) {
            if (integerIntegerMap.containsKey(a)) {
                int num = integerIntegerMap.get(a) + 1;
                integerIntegerMap.put(a, num );
                if (n == num) {
                    return a;
                }
            } else {
                integerIntegerMap.put(a, 1);
            }
        }
        return 0;
    }
}
