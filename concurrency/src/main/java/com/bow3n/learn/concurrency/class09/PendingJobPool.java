package com.bow3n.learn.concurrency.class09;

import com.bow3n.learn.concurrency.class09.vo.ITaskProcessor;
import com.bow3n.learn.concurrency.class09.vo.JobInfo;
import com.bow3n.learn.concurrency.class09.vo.TaskResult;
import com.bow3n.learn.concurrency.class09.vo.TaskResultType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class PendingJobPool {

    private PendingJobPool() {
    }

    private static class PendingJobPoolHolder {
        static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance() {
        return PendingJobPoolHolder.pool;
    }

    private static CheckJobProcessor checkJobProcessor = CheckJobProcessor.getInstance();


    private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();


    private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);

    // 线程池
    private static ExecutorService taskExecutor =
            new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS, 60, TimeUnit.SECONDS, taskQueue);

    // Job
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();


    private static class PendingTask<T, R> implements Runnable {

        private JobInfo<R> jobInfo;
        private T processData;

        public PendingTask(JobInfo<R> jobInfo, T processData) {
            this.jobInfo = jobInfo;
            this.processData = processData;
        }

        @Override
        public void run() {
            ITaskProcessor<T, R> processor = (ITaskProcessor<T, R>) jobInfo.getTaskProcessor();
            // 业务方法执行
            TaskResult<R> result = null;
            result = processor.execute(processData);
            try {
                if (result == null) {
                    result = new TaskResult<R>(TaskResultType.Exception, null, "业务方法返回为空。");
                } else if (result.getResultType() == null) {
                    if (result.getReason() == null) {
                        result = new TaskResult<R>(TaskResultType.Exception, null, "返回原因为空");
                    } else {
                        result = new TaskResult<R>(TaskResultType.Exception, null, result.getReason());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = new TaskResult<R>(TaskResultType.Exception, null, e.getMessage());
            } finally {
                jobInfo.addTaskResult(result, checkJobProcessor);
            }
        }
    }


    // 注册任务
    public void registerJob(String jobName, Integer taskLength, ITaskProcessor<?, ?> taskProcessor, long expireTime) {
        JobInfo<Object> jobInfo = new JobInfo<>(jobName, taskLength, taskProcessor, expireTime);
        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException(String.format("Job : [%s] 已经存在。", jobName));
        }
    }

    public <T, R> void putTask(String jobName, T t) {
        JobInfo<R> jobInfo = getJobInfo(jobName);
        PendingTask pendingTask = new PendingTask<T, R>(jobInfo, t);
        taskExecutor.execute(pendingTask);
    }


    public <R> List<TaskResult<R>> getTaskDetails(String jobName) {
        JobInfo<R> jobInfo = getJobInfo(jobName);
        return jobInfo.getTaskDetail();
    }

    public <R> String getTaskProgress(String jobName) {
        JobInfo<R> jobInfo = getJobInfo(jobName);
        return jobInfo.getTotalProcess();
    }

    public static Map<String, JobInfo<?>> getJobInfoMap() {
        return jobInfoMap;
    }

    private <R> JobInfo<R> getJobInfo(String jobName) {
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if (jobInfo == null) {
            throw new RuntimeException(String.format("任务 [%s] 并不存在于系统中。", jobName));
        }
        return jobInfo;
    }

}
