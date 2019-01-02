package com.bow3n.learn.concurrency.class09.test;

import com.bow3n.learn.concurrency.class09.vo.ITaskProcessor;
import com.bow3n.learn.concurrency.class09.vo.TaskResult;
import com.bow3n.learn.concurrency.class09.vo.TaskResultType;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyTask implements ITaskProcessor<Integer, Integer> {
    @Override
    public TaskResult<Integer> execute(Integer d) {
        Random r = new Random();
        int flag = r.nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer returnValue = d + flag;
        if (flag <= 300) {
            return new TaskResult<Integer>(TaskResultType.Success, returnValue);
        } else if (flag < 400) {
            return new TaskResult<Integer>(TaskResultType.Failure, -1, "任务失败");
        } else {
            return new TaskResult<Integer>(TaskResultType.Exception, -1, "任务发生异常 ");
        }
    }
}
