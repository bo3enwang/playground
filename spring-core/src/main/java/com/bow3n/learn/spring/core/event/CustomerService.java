package com.bow3n.learn.spring.core.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class CustomerService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void sendSomeThing(String msg) {
        if (!StringUtils.isEmpty(msg) && msg.startsWith("event")) {
            CustomerEvent event = new CustomerEvent(this, msg);
            publisher.publishEvent(event);
            return;
        }
        System.out.println(msg);
    }
}
