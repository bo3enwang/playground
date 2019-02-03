package com.bow3n.learn.concurrency.class11;

import org.springframework.stereotype.Controller;

/**
 * @author bowen
 */
public class ControlDep {
    volatile int a = 0;
    volatile boolean flag = false;

    public static void main(String[] args) {
        ControlDep controlDep = new ControlDep();
        new Thread(controlDep::init).start();
        new Thread(controlDep::use).start();
    }

    public void init() {
        a = 1;
        flag = true;
    }

    public void use() {
        if (flag) {
            int i = a * a;
            System.out.println(i);
        }
    }
}
