package com.example.assignment1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.assignment1.entity.Employee;

@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(com.example.assignment1.config.AppConfig.class);
		Employee employee = context.getBean("employee", Employee.class);
		employee.working();
	}

}
