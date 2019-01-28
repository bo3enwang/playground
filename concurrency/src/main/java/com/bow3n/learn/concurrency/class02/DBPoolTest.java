package com.bow3n.learn.concurrency.class02;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bowen
 */
public class DBPoolTest {

    private static DBPool pool = new DBPool(10);
    private static CountDownLatch end;


    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notGot), "work_no: " + i);
            thread.start();
        }
        end.await();
        System.out.println("总共尝试了: " + (threadCount * count));
        System.out.println("拿到连接次数" + got);
        System.out.println("没有拿到连接次数" + notGot);
    }

    static class Worker implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    Connection connection = pool.getConn(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConn(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + " 等待超时");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
