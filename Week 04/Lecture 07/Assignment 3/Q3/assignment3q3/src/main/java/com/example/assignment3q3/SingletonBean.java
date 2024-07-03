package com.example.assignment3q3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SingletonBean {
    @Autowired
    private PrototypeBean prototypeBean;

    public void performAction() {
        System.out.println("PrototypeBean instance: " + prototypeBean);
        prototypeBean.doSomething();
    }

    // Solution 1: Using @Lookup
    // @Lookup
    // public PrototypeBean getPrototypeBean() {
    //     // Spring will override this method to return a new PrototypeBean instance
    //     return null;
    // }

    // public void performAction() {
    //     PrototypeBean prototypeBean = getPrototypeBean();
    //     System.out.println("PrototypeBean instance: " + prototypeBean);
    //     prototypeBean.doSomething();
    // }

    // Solution 2: Using ObjectFactory
    // @Autowired
    // private ObjectFactory<PrototypeBean> prototypeBeanFactory;

    // public void performAction() {
    //     PrototypeBean prototypeBean = prototypeBeanFactory.getObject();
    //     System.out.println("PrototypeBean instance: " + prototypeBean);
    //     prototypeBean.doSomething();
    // }
}
