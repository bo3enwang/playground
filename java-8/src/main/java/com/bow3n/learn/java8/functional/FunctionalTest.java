package com.bow3n.learn.java8.functional;


import java.util.stream.Stream;

/**
 * @author bowen
 */
public class FunctionalTest {

    public static void main(String[] args) {

        FunctionalTest1 functionInterfaceTest1 = () -> {
            System.out.println("haha");
        };
        functionInterfaceTest1.say();

        Horse f1 = FunctionalTest::getInstance;
        Horse f2 = FunctionalTest::getMessage;
        String msg1 = joinStr("你好", f1);
        String msg2 = joinStr("你好", item -> "世界," + item + "!");
        System.out.println(msg1);
        System.out.println(msg2);

        Horse horse = item -> item + ", 种群";

    }

    public static String getInstance(String item) {
        return item + "！世界";
    }

    public static String getMessage(String massage) {
        return "世界," + massage + "!";
    }

    public static String joinStr(String str, Horse horse) {
        return horse.brand(str);
    }
}
