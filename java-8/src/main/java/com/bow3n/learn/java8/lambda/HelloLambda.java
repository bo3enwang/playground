package com.bow3n.learn.java8.lambda;

/**
 * @author bowen
 */
public class HelloLambda {
    Runnable r1 = () -> System.out.println(this);
    Runnable r2 = () -> System.out.println(toString());

    public static void main(String[] args) {
        new HelloLambda().r1.run();
        new HelloLambda().r2.run();
    }

    @Override
    public String toString() {
        return "Hello, lambda!";
    }
}
