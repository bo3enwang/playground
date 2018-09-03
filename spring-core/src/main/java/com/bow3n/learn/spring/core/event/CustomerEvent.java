package com.bow3n.learn.spring.core.event;

import org.springframework.context.ApplicationEvent;

public class CustomerEvent extends ApplicationEvent {

    private final String msg;

    public CustomerEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
