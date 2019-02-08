package com.bow3n.learn.concurrency.executor.executor;

import com.bow3n.learn.concurrency.executor.thead.MyTask;
import com.bow3n.learn.concurrency.executor.thead.MyThread;
import com.bow3n.learn.concurrency.executor.thead.MyThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory threadFactory = new MyThreadFactory("mf");
        ExecutorService executor = Executors.newCachedThreadPool(threadFactory);

        MyTask task = new MyTask();
        executor.submit(task);

        executor.shutdown();;

        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Main: End of the program.\\n");

    }
}
