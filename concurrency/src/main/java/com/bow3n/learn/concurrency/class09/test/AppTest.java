package com.bow3n.learn.concurrency.class09.test;

import com.bow3n.learn.concurrency.class09.PendingJobPool;
import com.bow3n.learn.concurrency.class09.vo.TaskResult;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AppTest {

    private final static String JOB_NAME = "计算";

    private final static int TASK_LENGTH = 1000;

    private static class QueryJob implements Runnable {
        private PendingJobPool pendingJobPool;

        private QueryJob(PendingJobPool pendingJobPool) {
            this.pendingJobPool = pendingJobPool;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 1000) {
                List<TaskResult<String>> taskDetails = pendingJobPool.getTaskDetails(JOB_NAME);
                if (!taskDetails.isEmpty()) {
                    System.out.println(pendingJobPool.getTaskProgress(JOB_NAME));
                    System.out.println(taskDetails);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        PendingJobPool pendingJobPool = PendingJobPool.getInstance();
        pendingJobPool.registerJob(JOB_NAME, TASK_LENGTH, task, 10000 * 5);
        Random r = new Random();
        for (int i = 0; i < TASK_LENGTH; i++) {
            pendingJobPool.putTask(JOB_NAME, r.nextInt(1000));
        }
        Thread t = new Thread(new QueryJob(pendingJobPool));
        t.start();
    }
}
