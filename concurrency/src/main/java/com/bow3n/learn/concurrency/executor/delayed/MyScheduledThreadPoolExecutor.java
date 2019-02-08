package com.bow3n.learn.concurrency.executor.delayed;

import java.util.concurrent.*;

public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable callable, RunnableScheduledFuture<V> task) {
        return new MyScheduledTask<V>(callable, null, task, this);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> task= super.scheduleAtFixedRate(command, initialDelay, period, unit);
        MyScheduledTask<?> myTask=(MyScheduledTask<?>)task;
        myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period,unit));
        return task;
    }
}
