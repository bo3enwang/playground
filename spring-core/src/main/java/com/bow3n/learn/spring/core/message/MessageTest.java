package com.bow3n.learn.spring.core.message;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class MessageTest {

    public static void main(String[] args) {
//        MessageSource resources = new ClassPathXmlApplicationContext("message/beans.xml");
//        String message = resources.getMessage("message", null, "Default", null);
//        System.out.println(message);

        MessageSource resources = new ClassPathXmlApplicationContext("message/beans.xml");
        String message = resources.getMessage("argument.required",
                new Object [] {"userDao"}, "Required", Locale.UK);
        System.out.println(message);

    }
}
