package com.bow3n.learn.concurrency.class09.vo;

public interface ITaskProcessor<T, R> {
    TaskResult<R> execute(T d);
}
