package com.bow3n.learn.concurrency.class09.vo;

import com.bow3n.learn.concurrency.class09.CheckJobProcessor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class JobInfo<R> {

    private final String jobName;

    private final Integer taskLength;

    private final ITaskProcessor<?, ?> taskProcessor;

    private AtomicInteger successCount;
    private AtomicInteger taskProcessorCount;
    private LinkedBlockingDeque<TaskResult<R>> taskDetailDeque;

    private final long expireTime;

    public JobInfo(String jobName, Integer taskLength, ITaskProcessor<?, ?> taskProcessor, long expireTime) {
        this.jobName = jobName;
        this.taskLength = taskLength;
        this.taskProcessor = taskProcessor;
        this.expireTime = expireTime;

        successCount = new AtomicInteger(0);
        taskProcessorCount = new AtomicInteger(0);
        taskDetailDeque = new LinkedBlockingDeque<>();
    }


    public ITaskProcessor<?, ?> getTaskProcessor() {
        return taskProcessor;
    }

    public List<TaskResult<R>> getTaskDetail() {
        List<TaskResult<R>> taskResults = new LinkedList<>();
        TaskResult<R> taskResult;
        while ((taskResult = taskDetailDeque.pollFirst()) != null) {
            taskResults.add(taskResult);
        }
        return taskResults;
    }

    public void addTaskResult(TaskResult<R> taskResult, CheckJobProcessor checkJobProcessor) {
        taskDetailDeque.add(taskResult);
        if (TaskResultType.Success.equals(taskResult.getResultType())) {
            successCount.incrementAndGet();
        }
        taskProcessorCount.incrementAndGet();
        if (taskProcessorCount.get() == taskLength) {
            checkJobProcessor.putJob(jobName, expireTime);
        }
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getTaskProcessorCount() {
        return taskProcessorCount.get();
    }

    public int getFailureCount() {
        return getTaskProcessorCount() - getSuccessCount();
    }

    public String getTotalProcess() {
        return String.format("成功数:[%d], 当前已经处理:[%d]", getSuccessCount(), getTaskProcessorCount());
    }

}
