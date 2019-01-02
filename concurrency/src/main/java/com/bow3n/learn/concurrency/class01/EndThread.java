package com.bow3n.learn.concurrency.class01;

public class EndThread {

    private static class UserTread extends Thread {
        public UserTread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
                System.out.println("Name is " + threadName);
            }
            System.out.println(threadName + " flag is " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UserTread("kka");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }
}
