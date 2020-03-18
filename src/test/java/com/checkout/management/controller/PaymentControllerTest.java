package com.checkout.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.GatewayData;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.serviceImpl.PaymentServiceImpl;
import com.checkout.management.testUtills.JUnitObjectPaymentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaymentControllerTest extends JUnitObjectPaymentServiceImpl {

	@InjectMocks
	private PaymentController mockPaymentController;
	
	@Mock
	private PaymentServiceImpl mockPaymentServiceImpl;

	/**
	 * Test case success when payment mode return success response
	 * expected result   200
	 */
	@Test
	public void test_PaymentMethod_When_Success() {
		PaymentModeModel paymentModeModel = getPaymentModeObj();
		when(mockPaymentServiceImpl.getPaymentMode()).thenReturn(paymentModeModel);
		ResponseEntity<Object> responseEntity = mockPaymentController.PaymentMethod();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		
		
	}
	
	/**
	 * Test case success when  payment mode not return false response
	 * expected result   200
	 */
	@Test
	public void test_PaymentMethod_When_Fail() {
		when(mockPaymentServiceImpl.getPaymentMode()).thenReturn(null);
		ResponseEntity<Object> responseEntity = mockPaymentController.PaymentMethod();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		
		
	}
	
	/**
	 * Test case success when  payment mode not return Exception response
	 * expected result   500
	 */
	@Test
	public void test_PaymentMethod_When_Exception() {
		when(mockPaymentServiceImpl.getPaymentMode()).thenThrow(NullPointerException.class);
		ResponseEntity<Object> responseEntity = mockPaymentController.PaymentMethod();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
		
		
	}
	
	/**
	 * Test case success when payment gateway return success response
	 * expected result   200
	 */
	@Test
	public void test_payment_When_Success() {
		CartOrderRequest cartOrderRequest =cartOrderRequest();
		CreateOrderResponse createOrderResponse = fetchOrderDetailsSuccessObj();
		when(mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest)).thenReturn(createOrderResponse);
		
		//when(mockPaymentServiceImpl.removeCartItem("1")).doNothing();
		
		GatewayData gatewayData = paymentSuccessObj();
		when(mockPaymentServiceImpl.payment(createOrderResponse)).thenReturn(gatewayData);
		
		//when(mockPaymentServiceImpl.initiateProductShipment(gatewayData)).doNothing();
		ResponseEntity<Object> responseEntity = mockPaymentController.payment(cartOrderRequest);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		
		
	}
	
	/**
	 * Test case success when payment gateway return fail response
	 * expected result   200
	 * need to work on that
	 */
	@Test
	public void test_payment_When_Fail() {
		CartOrderRequest cartOrderRequest =cartOrderRequest();
		CreateOrderResponse createOrderResponse = fetchOrderDetailsFailureObj();
		when(mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest)).thenReturn(createOrderResponse);
		GatewayData gatewayData = paymentSuccessObj();
		when(mockPaymentServiceImpl.payment(createOrderResponse)).thenReturn(gatewayData);
		ResponseEntity<Object> responseEntity = mockPaymentController.payment(cartOrderRequest);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		
		
	}
	
	/**
	 * Test case success when payment gateway return exception response
	 * expected result   500
	 */
	@Test
	public void test_payment_When_Exception() {
		CartOrderRequest cartOrderRequest =cartOrderRequest();
		CreateOrderResponse createOrderResponse = fetchOrderDetailsFailureObj();
		when(mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest)).thenThrow(NullPointerException.class);
		GatewayData gatewayData = paymentSuccessObj();
		when(mockPaymentServiceImpl.payment(createOrderResponse)).thenReturn(gatewayData);
		ResponseEntity<Object> responseEntity = mockPaymentController.payment(cartOrderRequest);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
		
		
	}
	
}
