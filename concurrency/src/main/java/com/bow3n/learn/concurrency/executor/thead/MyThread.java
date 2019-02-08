package com.bow3n.learn.concurrency.executor.thead;

import java.util.Date;

public class MyThread extends Thread {
    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }

    @Override
    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    private void setFinishDate() {
        finishDate = new Date();
    }

    private void setStartDate() {
        startDate = new Date();
    }

    private void setCreationDate() {
        creationDate = new Date();
    }

    public long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName());
        builder.append(": ");
        builder.append(" Creation Date: ");
        builder.append(creationDate);
        builder.append(" : Running time: ");
        builder.append(getExecutionTime());
        builder.append(" Milliseconds.");
        return builder.toString();
    }
}
