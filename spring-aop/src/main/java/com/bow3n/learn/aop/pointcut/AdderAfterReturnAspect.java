package com.bow3n.learn.aop.pointcut;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdderAfterReturnAspect {


    public void httpLog() {}



    @Before(value = "methodsToBeProfiled()")
    public void before()  {
        System.out.println("ooooooooooooo!");
    }


    @Pointcut("within(com.bow3n.learn.aop.*)")
    public void methodsToBeProfiled(){}

    @AfterReturning(pointcut = "methodsToBeProfiled()", returning = "retVal")
    public void afterReturn(Object retVal) throws Throwable {
        System.out.println(String.format("value return was %s", retVal));
    }
}
