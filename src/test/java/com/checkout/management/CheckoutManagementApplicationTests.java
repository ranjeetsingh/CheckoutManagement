package com.checkout.management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CheckoutManagementApplicationTests {
	@Test
	void testGet() {
		assertEquals("Hello JUnit 5", "Hello JUnit 5");
	}
}
