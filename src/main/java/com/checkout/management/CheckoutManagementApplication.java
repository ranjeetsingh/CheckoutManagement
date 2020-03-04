package com.checkout.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CheckoutManagementApplication{

	public static void main(String[] args) {
		SpringApplication.run(CheckoutManagementApplication.class, args);
		System.out.println("!....Application Start....!!");
	}

}
