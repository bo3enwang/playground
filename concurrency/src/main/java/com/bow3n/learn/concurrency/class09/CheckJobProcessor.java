package com.bow3n.learn.concurrency.class09;

import com.bow3n.learn.concurrency.class09.vo.ItemVo;

import java.util.concurrent.DelayQueue;

public class CheckJobProcessor<E> {


    private CheckJobProcessor() {
    }

    private static class CheckJobProcessorHolder {
        static CheckJobProcessor processor = new CheckJobProcessor();
    }

    public static CheckJobProcessor getInstance() {
        return CheckJobProcessorHolder.processor;
    }


    private static DelayQueue<ItemVo<String>> queue = new DelayQueue<>();

    private static class FetchJob implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    // 拿到已经过期的任务
                    ItemVo<String> item = queue.take();
                    String jobName = item.getData();
                    PendingJobPool.getJobInfoMap().remove(jobName);
                    System.out.println(String.format("任务 [%s] 已经过期被清除", jobName));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void putJob(String jobName, long expireTime) {
        ItemVo<String> item = new ItemVo<>(expireTime, jobName);
        queue.offer(item);
        System.out.println(String.format("[%s] 已经放入过期检查，过期时长 %d", jobName, expireTime));
    }

    static {
        Thread thread = new Thread(new FetchJob());
        thread.setDaemon(true);
        thread.start();
        // 开启任务过期检查
    }

}
