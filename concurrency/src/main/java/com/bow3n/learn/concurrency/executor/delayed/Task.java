package com.bow3n.learn.concurrency.executor.delayed;

import java.util.concurrent.TimeUnit;

public class Task  implements Runnable {
    @Override
    public void run() {
        System.out.println("Task is start ");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        System.out.println("Task is end ");

    }
}
