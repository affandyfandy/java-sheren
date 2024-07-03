package com.example.assignment2q3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext; 
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Assignment2q3Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	}

}
