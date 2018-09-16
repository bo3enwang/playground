package com.bow3n.learn.algorithms.easy;

import org.junit.jupiter.api.Test;

public class StringAlgorithms {

    public String reverseString(String s) {
        char[] arr = s.toCharArray();
        char[] newChar = new char[arr.length];
        for (int i = 0, j = arr.length - 1; i < arr.length; i++, j--) {
            newChar[i] = arr[j];
        }
        return new String(newChar);
    }

    public int reverse(int x) {
        if(x == 0)return x;
        if(x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) return 0;
        int sign = x > 0 ? 1 : -1;
        int num = Math.abs(x);
        StringBuilder numBuilder = new StringBuilder();
        while (num != 0) {
            numBuilder.append(num % 10);
            num /= 10;
        }
        long result = Long.valueOf(numBuilder.toString());
        if (result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) result * sign;
    }


    @Test
    public void test_reverse() {
        System.out.println(reverse(1534236469));
    }


}
