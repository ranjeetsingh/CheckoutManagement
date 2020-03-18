package com.checkout.management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.model.request.CommonRequestModel;
import com.checkout.management.model.request.ShipmentRequest;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.cartitem.CartItemResponse;
import com.checkout.management.model.response.inventory.InventoryResponse;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.order.Createorder;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.userdetails.UserDetailsResponse;
import com.checkout.management.serviceImpl.CheckoutServiceImpl;
import com.checkout.management.testUtills.JUnitObjectPaymentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class CheckoutControllerTest extends JUnitObjectPaymentServiceImpl {
	@Autowired
	private CheckoutController mockCheckoutController;

	@Mock
	private CheckoutServiceImpl mockCheckoutServiceImpl;

	/**
	 * Test case success when checkout product return success response expected
	 * result 200
	 */
	@Test
	public void test_checkoutProduct_When_Success() {
		CartItemResponse cartItemResponse = cartItemResponse();
		PlaceOderResponse placeOrder = placeOrderData();

		when(mockCheckoutServiceImpl.getCartItem("1")).thenReturn(cartItemResponse);
		//when(cartItemResponse.getMessage()).thenReturn(AppConstant.CART_SERVICE_DOWN_MESSAGE);
		//assertEquals(cartItemResponse.getMessage(), AppConstant.CART_SERVICE_DOWN_MESSAGE);
		// when(cartItemResponse.getData()).thenReturn(null);
		assertEquals(cartItemResponse.getData(), null);

		InventoryResponse inventoryStatus = inventoryStatus();
		when(mockCheckoutServiceImpl.checkInventory("1")).thenReturn(inventoryStatus);
		// when(inventoryStatus.getMessage()).thenReturn(AppConstant.INVENTORY_SERVICE_DOWN_MESSAGE);
		// when(inventoryStatus.getData()).thenReturn(null);
		assertEquals(inventoryStatus.getData(), null);

		UserDetailsResponse userDetailsResponse = userDetailsResponse();
		when(mockCheckoutServiceImpl.getUserAddress("1")).thenReturn(userDetailsResponse);
		// when(userDetailsResponse.getResult()).thenReturn(null);
		assertEquals(userDetailsResponse.getResult().size(), 1);

		when(mockCheckoutServiceImpl.placeOrder(cartItemResponse, userDetailsResponse)).thenReturn(placeOrder);
		ResponseEntity<Object> responseEntity = mockCheckoutController.checkoutProduct("1", "1");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		// Assert.assertEquals(200, responseEntity.);
	}

	/**
	 * Test case success when update inventory return success response expected
	 * result 200
	 */
	@Test
	public void test_updateInventory_When_Success() {
		CommonRequestModel commonRequestModel = commonRequestModelObj();
		CommonResponseModel inventoryResponseModel = commonResponseModelObjForSuccess();
		when(mockCheckoutServiceImpl.updatInventory(commonRequestModel)).thenReturn(inventoryResponseModel);

		ResponseEntity<Object> responseEntity = mockCheckoutController.updateInventory(commonRequestModel);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	/**
	 * Test case success when remove item from cart return success response expected
	 * result 200
	 */
	@Test
	public void test_removeCartItem_When_Success() {
		CommonResponseModel inventoryResponseModel = commonResponseModelObjForSuccess();
		when(mockCheckoutServiceImpl.removeCartItem(Mockito.anyString())).thenReturn(inventoryResponseModel);

		ResponseEntity<Object> responseEntity = mockCheckoutController.removeCartItem("1");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	/**
	 * Test case success when update create order return success response expected
	 * result 200
	 */
	@Test
	public void test_createOrder_When_Success() {
		CartOrderRequest cartOrderRequest = cartOrderRequest();
		CreateOrderResponse createOrderResponse = fetchOrderDetailsSuccessObj();
		when(mockCheckoutServiceImpl.creatOrder(cartOrderRequest)).thenReturn(createOrderResponse);
		Createorder orderData = new Createorder();
		orderData.setOrderid("1");
		orderData.setUserid("1");
		when(mockCheckoutServiceImpl.orderDetails(createOrderResponse)).thenReturn(orderData);
		ResponseEntity<Object> responseEntity = mockCheckoutController.createOrder(cartOrderRequest);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	/**
	 * Test case success when update create order return success response expected
	 * result 200
	 */
	@Test
	public void test_createShipment_When_Success() {
		ShipmentRequest shipmentRequest = createShipmentTestSuccessObj();
		CommonResponseModel shipmentResponseModel = commonResponseModelObjForSuccess();

		when(mockCheckoutServiceImpl.creatShipment(shipmentRequest)).thenReturn(shipmentResponseModel);

		ResponseEntity<Object> responseEntity = mockCheckoutController.createShipment(shipmentRequest);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	/**
	 * Test case success when update create order return success response expected
	 * result 200
	 */
	@Test
	public void test_createShipment_When_Fail() {
		ShipmentRequest shipmentRequest = createShipmentTestSuccessObj();
		CommonResponseModel shipmentResponseModel = commonResponseModelObjForFail();

		when(mockCheckoutServiceImpl.creatShipment(shipmentRequest)).thenReturn(shipmentResponseModel);
		//assertEquals(shipmentResponseModel.getStatus(), false);
		ResponseEntity<Object> responseEntity = mockCheckoutController.createShipment(shipmentRequest);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	
}
