package com.bow3n.learn.concurrency.class02;

/**
 * @author bowen
 */
public class TestWait {
    private static Express express = new Express(0, Express.CITY);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        Thread.sleep(1000);
        express.changeKm();
//        express.changeSite();
    }

    private static class CheckKm extends Thread {

        @Override
        public void run() {
            express.waitKm();
        }
    }

    private static class CheckSite extends Thread {

        @Override
        public void run() {
            express.waitSite();
        }
    }
}
