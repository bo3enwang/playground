package com.bow3n.learn.concurrency;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author bowen
 */
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {


        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(new FuckU(), 1, TimeUnit.MINUTES);
        scheduledExecutorService.scheduleAtFixedRate(new Push(), 1, 5, TimeUnit.SECONDS);

    }

    static class FuckU implements Runnable {
        @Override
        public void run() {
            System.out.println("fuck it");
        }
    }

    static class Push implements Runnable {

        @Override
        public void run() {
            System.out.println("fuck it all");
        }
    }
}
