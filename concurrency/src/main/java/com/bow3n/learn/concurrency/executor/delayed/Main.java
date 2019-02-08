package com.bow3n.learn.concurrency.executor.delayed;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);

        Task task = new Task();
        System.out.println("北京时间：" + new Date());

        executor.schedule(task, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(3);

        task = new Task();

        System.out.println("北京时间:" + new Date());

        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Main End");
    }
}
