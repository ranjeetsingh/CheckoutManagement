package com.checkout.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	/**
	 * This is a Get method is used to test
	 * 
	 * @return String
	 * @Exception
	 */
	@GetMapping("/getTest")
	public String getTest() {
		return "Hello Test";
	}
}
