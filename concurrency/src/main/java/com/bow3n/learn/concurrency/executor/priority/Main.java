package com.bow3n.learn.concurrency.executor.priority;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                2,
                1,
                TimeUnit.SECONDS,
                new PriorityBlockingQueue<>());
        for (int i = 0; i < 4; i++) {
            MyPriorityTask task = new MyPriorityTask(String.format("Task [%d]", i), i);
            executor.execute(task);
        }
        TimeUnit.SECONDS.sleep(1);

        for (int i = 4; i < 8; i++) {
            MyPriorityTask task = new MyPriorityTask(String.format("Task [%d]", i), i);
            executor.execute(task);
        }

        executor.shutdown();
        if (executor.awaitTermination(1, TimeUnit.DAYS)) {
            executor.shutdown();
        }
        System.out.printf("Main: End of the program.\n");
    }
}
