package com.example.assignment3q3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {
    public void doSomething() {
        System.out.println("Doing something with " + this);
    }
}
