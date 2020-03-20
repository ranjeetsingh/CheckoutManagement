package com.checkout.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableSwagger2
public class CheckoutManagementApplication{

	public static void main(String[] args) {
		SpringApplication.run(CheckoutManagementApplication.class, args);
		System.out.println("!....Application Start....!!");
	}

}
