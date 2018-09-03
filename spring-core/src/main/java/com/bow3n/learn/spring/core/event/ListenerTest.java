package com.bow3n.learn.spring.core.event;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ListenerTest {

    @EventListener({ContextStartedEvent.class, ContextStartedEvent.class})
    public void processContextStartedEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println(contextStartedEvent);
        System.out.println("context start !");
    }

    @EventListener
    @Async
    public void processBlackListEvent(CustomerEvent customerEvent) {
        System.out.println(customerEvent.getMsg());
    }

}
