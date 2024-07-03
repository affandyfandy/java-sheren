package com.example.assignment3q3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Assignment3q3Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment3q3Application.class, args);
	}

	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
            SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);

            System.out.println("SingletonBean instance 1:");
            singletonBean1.performAction();
            singletonBean1.performAction();

            System.out.println("\nSingletonBean instance 2:");
            singletonBean2.performAction();
            singletonBean2.performAction();
        };
    }
}
