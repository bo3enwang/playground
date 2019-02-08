package com.bow3n.learn.concurrency.executor.thead;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory factory = new MyThreadFactory("testFactory");

        MyTask task = new MyTask();

        Thread thread = factory.newThread(task);

        thread.start();
        thread.join();

        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n",thread);
        System.out.printf("Main: End of the example.\n");

    }
}
