package com.bow3n.learn.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bowen
 * https://leetcode-cn.com/problems/unique-morse-code-words/submissions/
 * <p>
 * 执行用时: 9 ms, 在Unique Morse Code Words的Java提交中击败了77.99% 的用户
 * <p>
 * TODO 可以再优化优化
 */
public class q804_unique_morse_code_words {

    public static void main(String[] args) {
        System.out.println(uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
        ;
    }

    public static int uniqueMorseRepresentations(String[] words) {
        Set<String> result = new HashSet<>();
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        for (String word : words) {
            StringBuilder morseStr = new StringBuilder();
            for (char c : word.toCharArray()) {
                int num = c - 97;
                morseStr.append(morse[num]);
            }
            result.add(morseStr.toString());
        }
        return result.size();
    }
}
