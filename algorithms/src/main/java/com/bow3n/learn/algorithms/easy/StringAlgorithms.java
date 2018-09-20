package com.bow3n.learn.algorithms.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.print.Pageable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
        if (x == 0) return x;
        if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) return 0;
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


    public int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        int position = -1;
        Map<Integer, Integer> maps = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int cNum = chars[i];
            if (maps.containsKey(cNum)) {
                chars[i] = 0;
            }
            maps.put(cNum, i);
        }
        for (int i = 0; i < chars.length; i++) {
            int cNum = chars[i];
            if (cNum != 0) {
                Integer index = maps.get(cNum);
                if (index == null || index == i) {
                    return i;
                }
            }
        }
        return position;
    }

    public int firstUniqChar_a(String s) {
        if (s.length() == 0) {
            return -1;
        }
        int position = -1;
        for (char i = 'a'; i < 'z'; i++) {
            int index = s.indexOf(i);
            if (index != -1 && index == s.lastIndexOf(i)) {
                position = position == -1 ? index : Math.min(index, position);
            }
        }
        return position;
    }


    @Test
    public void test_firstUniqChar() {
//        AssertionfirstUniqChar_as.assertEquals(0, firstUniqChar("leetcode"));
//        Assertions.assertEquals(-1, firstUniqChar("cc"));
        Assertions.assertEquals(-1, firstUniqChar_a("aaadd"));
        Assertions.assertEquals(0, firstUniqChar_a("leetcode"));
    }


    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] != tChar[i]) {
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome_a(String s) {

        if (s.length() == 1) {
            return true;
        }
        int length = s.length();
        Stack stack = new Stack();
        for (int i = 0; i < length; i++) {
            char c = lowCase(s.charAt(i));
            if (c != 0) {
                if (i < length / 2) {
                    stack.push(c);
                }
                if (i > length / 2) {
                    System.out.print((char) stack.pop());
                    System.out.println(c);
                    if ((char) stack.pop() != c) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        int length = s.length();
        int firstIndex = 0;
        int lastIndex = length - 1;
        while (firstIndex <= lastIndex) {
            char first = lowCase(s.charAt(firstIndex));
            char last = lowCase(s.charAt(lastIndex));
            if (first == 0) {
                firstIndex += 1;
                continue;
            }
            if (last == 0) {
                lastIndex -= 1;
                continue;
            }
            if (first != last) {
                return false;
            }
            firstIndex++;
            lastIndex--;
        }
        return true;
    }

    private char lowCase(char c) {
        if (c >= '0' && c <= '9') {
            return c;
        }
        if (c >= 'a' && c <= 'z') {
            return c;
        }
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + 32);
        }
        return 0;
    }


    @Test
    public void test_isPalindrome() {
//        Assertions.assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
//        Assertions.assertFalse(isPalindrome("race a car"));
        Assertions.assertFalse(isPalindrome("0P"));
        Assertions.assertTrue(isPalindrome("a."));
    }


    public int myAtoi(String str) {
        if (str.length() == 0) {
            return 0;
        }
        int start = -1;
        int last = -1;
        int sign = 1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (start == -1) {
                if (ch == '-') {
                    start = i + 1;
                    sign = -1;
                    continue;
                }
                if (ch == '+') {
                    start = i + 1;
                    continue;
                }
                if (ch <= '9' && ch >= '0') {
                    start = i;
                    continue;
                }
                if (ch == ' ') {
                    continue;
                }
            }
            if (ch <= '9' && ch >= '0') {
            } else {
                last = i;
                break;
            }
        }
        if (start == -1) {
            return 0;
        }
        if (last == -1) {
            last = str.length();
        }
        String strNum = str.substring(start, last);
        Long num;
        if ("".equals(strNum)) {
            num = 0L;
        } else {
            try {
                num = sign * Long.valueOf(strNum);
            } catch (Exception e) {
                if (sign == -1) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }

        if (num > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (num < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return num.intValue();
    }

    @Test
    public void test_myAtoi() {
//        Assertions.assertEquals(3, myAtoi("3.14159"));
//        Assertions.assertEquals(4193, myAtoi("4193 with words"));
//        Assertions.assertEquals(0, myAtoi("-"));
//        Assertions.assertEquals(0, myAtoi("-+1"));
//        Assertions.assertEquals(0, myAtoi("words and 987"));
//        Assertions.assertEquals(42, myAtoi("42"));
//        Assertions.assertEquals(42, myAtoi("20000000000000000000"));
        Assertions.assertEquals(-42, myAtoi("   -42"));
        Assertions.assertEquals(1, myAtoi("+1"));

    }


    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        return haystack.indexOf(needle);
    }

    @Test
    public void test_strStr() {
        Assertions.assertEquals(2, strStr("hello", "ll"));
    }


    public String countAndSay(int n) {
        Map<String, String> dict = new HashMap<>();
        dict.put("1", "11");
        dict.put("2", "12");
        dict.put("11", "21");

        Map<Integer, String> first = new HashMap<>();
        first.put(1, "1");
        first.put(2, "11");
        first.put(3, "21");
        if (n <= 3) {
            return first.get(n);
        }
        String result = first.get(3);
        for (int i = 4; i < n; i++) {
            StringBuilder newS = new StringBuilder();
            for (int j = 0; j < result.length(); j++) {
                newS.append(dict.get(String.valueOf(result.charAt(j))));
            }
            result = newS.toString();
        }
        return result;
    }


    @Test
    public void test_countAndSay() {
        Assertions.assertEquals("1211", countAndSay(4));
        Assertions.assertEquals("111221", countAndSay(5));
    }

}
