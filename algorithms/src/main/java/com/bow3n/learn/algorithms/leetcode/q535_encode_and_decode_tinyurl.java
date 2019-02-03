package com.bow3n.learn.algorithms.leetcode;


import java.util.HashMap;
import java.util.Random;

/**
 * @author bowen
 * https://leetcode-cn.com/problems/encode-and-decode-tinyurl/
 * 最佳答案：原样返回，我也是服了
 */
public class q535_encode_and_decode_tinyurl {
    HashMap<String, String> hashUrl = new HashMap<>();

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = getRandomString(6);
        hashUrl.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return hashUrl.get(shortUrl);
    }
}
