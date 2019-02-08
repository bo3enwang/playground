package com.bow3n.learn.concurrency.class02;

import java.util.concurrent.TimeUnit;

/**
 * @author bowen
 */
public class UseJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JumpQueue(previous), String.valueOf(i));
            System.out.println(previous.getName() + " jump custom queue the priority: " + thread.getName());
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + " terminated");
    }

    static class JumpQueue implements Runnable {
        private Thread thread;

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();// 将执行权让出
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminated");
        }
    }
}
