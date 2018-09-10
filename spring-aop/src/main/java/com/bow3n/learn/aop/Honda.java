package com.bow3n.learn.aop;


public class Honda implements Car {

    private String type;

    public Honda(String type) {
        this.type = type;
    }

    @Override
    public String brand(String name) {

        return "Honda";
    }

    @Override
    public String toString() {
        return "Honda{" +
                "type='" + type + '\'' +
                '}';
    }
}
