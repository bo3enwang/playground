package com.bow3n.learn.aop;

import org.springframework.stereotype.Component;

@Component
public class CarFactory {

    public String makeCar(String brand) {
        String car = brand + new Honda("civie");
        return car;
    }
}
