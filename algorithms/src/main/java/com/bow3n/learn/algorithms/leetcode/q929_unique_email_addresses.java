package com.bow3n.learn.algorithms.leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/unique-email-addresses/
 * 929. 独特的电子邮件地址
 * 执行用时: 53 ms, 在Unique Email Addresses的Java提交中击败了63.03% 的用户
 * TODO 优化
 */
public class q929_unique_email_addresses {

    public static int numUniqueEmails(String[] emails) {
        Set<String> result = new HashSet<>();
        for (String email : emails) {
            String[] emailNames = email.split("@");
            String[] one = emailNames[0].split("\\+");
            if (one.length > 0) {
                result.add(one[0].replaceAll("\\.", "") + emailNames[1]);
            }
        }
        return result.size();
    }

    public static void main(String[] args) {
        String[] emails = new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        numUniqueEmails(emails);
    }
}
