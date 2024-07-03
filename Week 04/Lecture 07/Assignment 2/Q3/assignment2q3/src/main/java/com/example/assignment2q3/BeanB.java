package com.example.assignment2q3;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Component; 

@Component
public class BeanB {
    private BeanA beanA;

    @Autowired
    public BeanB(BeanA beanA) {
        this.beanA = beanA;
    }
}
