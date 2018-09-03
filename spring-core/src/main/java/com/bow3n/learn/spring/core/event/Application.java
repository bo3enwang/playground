package com.bow3n.learn.spring.core.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.bow3n.learn.spring.core.event");
        ((AnnotationConfigApplicationContext) applicationContext).start();
        CustomerService customerService = applicationContext.getBean( CustomerService.class);
        customerService.sendSomeThing("s");
        customerService.sendSomeThing("event haha");
    }
}
