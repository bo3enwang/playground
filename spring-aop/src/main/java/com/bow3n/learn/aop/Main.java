package com.bow3n.learn.aop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class Main {


    @Autowired
    private CarFactory carFactory;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.bow3n.learn");
        ((AnnotationConfigApplicationContext) applicationContext).start();
        CarFactory carFactory = applicationContext.getBean(CarFactory.class);
        System.out.println(carFactory.makeCar("Honda"));
    }
}
