package com.bow3n.learn.java8.lambda;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * @author bowen
 */
public class LambdaUseCase {
    public static void main(String[] args) {
        Comparator<String> c = (s1, s2) -> s1.compareTo(s2);
        Runnable runnable = () -> System.out.println("hello");
        runnable.run();
        getRunnable("su").run();

        Function<String, String> atr = (name) -> {return "@" + name;};
    }

    static Runnable getRunnable(String name) {
        String hello = "hello";
        return () -> System.out.println(hello + "," + name);
    }

    static void wrong_case() {
        List<Integer> list = new LinkedList<>();
        int sum = list.stream().mapToInt(i -> i).sum();
    }
}
