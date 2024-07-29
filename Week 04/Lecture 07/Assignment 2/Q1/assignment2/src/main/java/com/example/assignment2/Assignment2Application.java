package com.example.assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.assignment2.service.EmployeeServiceConstructor;
import com.example.assignment2.service.EmployeeServiceField;
import com.example.assignment2.service.EmployeeServiceSetter;

@SpringBootApplication
public class Assignment2Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);

		// Remove this one as we don't need AppConfig:
		// ApplicationContext context = new AnnotationConfigApplicationContext(com.example.assignment2.config.AppConfig.class);
		ApplicationContext context = SpringApplication.run(Assignment2Application.class, args);

		// Use constructor DI
		EmployeeServiceConstructor employeeServiceConstructor = context.getBean(EmployeeServiceConstructor.class);
        employeeServiceConstructor.notifyEmployee("sheren@gmail.com");

		// Use field DI
		EmployeeServiceField employeeServiceField = context.getBean(EmployeeServiceField.class);
        employeeServiceField.notifyEmployee("sheren@gmail.com");

		// Use setter DI
		EmployeeServiceSetter employeeServiceSetter =  context.getBean(EmployeeServiceSetter.class);
		employeeServiceSetter.notifyEmployee("sheren@gmail.com");
	}
}
