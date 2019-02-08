package com.bow3n.learn.concurrency.forkjoin.cancel;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SearchNumberTask extends RecursiveTask<Integer> {
    private int[] numbers;

    private int start, end;

    private int number;

    private TaskManager taskManager;

    private final static int NOT_FOUNT = -1;

    public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager taskManager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.taskManager = taskManager;
    }


    @Override
    protected Integer compute() {
        System.out.println(String.format("Task: from %d to %d", start, end));
        int ret;
        if (end - start > 10) {
            ret = launchTasks();
        } else {
            ret = lookForNumber();
        }
        return ret;
    }

    private int lookForNumber() {
        for (int i = start; i < end; i++) {
            if (numbers[i] == number) {
                System.out.printf("Task: Number %d found in position %d\n", number, i);
                taskManager.cancelTasks(this);
                return i;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return NOT_FOUNT;
    }


    private int launchTasks() {
        int mid = (start + end) / 2;
        SearchNumberTask taskLeft = new SearchNumberTask(numbers, start, mid, number, taskManager);
        SearchNumberTask taskRight = new SearchNumberTask(numbers, mid, end, number, taskManager);
        taskManager.addTask(taskLeft);
        taskManager.addTask(taskRight);
        taskLeft.fork();
        taskRight.fork();
        int returnValue;
        returnValue = taskLeft.join();
        if (returnValue != -1) {
            return returnValue;
        }
        return taskRight.join();
    }
}
