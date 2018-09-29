package com.bow3n.learn.java8.lambda;

import java.util.function.Function;

/**
 * @author bowen
 */
public class FunctionInterfaceTest {
    public static void main(String[] args) {
        Function<String, String> atr = (name) -> {
            return "@" + name;
        };
        Function<String, Integer> leng = (name) -> name.length();
        Function<String, Integer> leng2 = String::length;

        for (String s : args) System.out.println(leng2.apply(s));
    }
}
