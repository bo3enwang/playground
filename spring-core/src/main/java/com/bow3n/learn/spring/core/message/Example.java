package com.bow3n.learn.spring.core.message;

import org.springframework.context.MessageSource;

public class Example {
    private MessageSource messages;

    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }

    public void execute() {
        String message = this.messages.getMessage("argument.required",
                new Object[]{"userDao"}, "Required", null);
        System.out.println(message);
    }
}
