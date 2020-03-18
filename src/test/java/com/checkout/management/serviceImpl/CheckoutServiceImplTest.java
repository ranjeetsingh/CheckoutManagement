package com.checkout.management.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import com.checkout.management.model.response.placeorder.Address;
import com.checkout.management.model.response.placeorder.Cartitem;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.userdetails.Result;
import com.checkout.management.model.response.userdetails.UserDetailsResponse;
import com.checkout.management.testUtills.JUnitObjectPaymentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class CheckoutServiceImplTest extends JUnitObjectPaymentServiceImpl {
	@MockBean
	private CheckoutServiceImpl mockCheckoutServiceImpl;

	@Test
	public void test_CartItemResponse_When_Success() {
		CartItemResponse cartItemResponse = new CartItemResponse();
		cartItemResponse.setCode(200);
		cartItemResponse.setMessage("get cart item successfully");
		cartItemResponse.setStatus(true);
		when(mockCheckoutServiceImpl.getCartItem("1")).thenReturn(cartItemResponse);
		assertEquals(true, cartItemResponse.getStatus());
	}

	@Test
	public void test_CartItemResponse_When_Fail() {
		CartItemResponse cartItemResponse = new CartItemResponse();
		cartItemResponse.setCode(200);
		cartItemResponse.setMessage("get cart item successfully");
		cartItemResponse.setStatus(false);
		when(mockCheckoutServiceImpl.getCartItem("1")).thenReturn(cartItemResponse);
		assertEquals(false, cartItemResponse.getStatus());
	}

	
	@Test
	public void test_checkInventory_When_Success() {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setMessage("success");
		inventoryResponse.setStatus(true);
		inventoryResponse.setStatusCode(200);
		when(mockCheckoutServiceImpl.checkInventory("1")).thenReturn(inventoryResponse);
		Assert.assertEquals(true, inventoryResponse.getStatus());
	}
	
	@Test
	public void test_checkInventory_When_Fail() {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setMessage("success");
		inventoryResponse.setStatus(false);
		inventoryResponse.setStatusCode(200);
		when(mockCheckoutServiceImpl.checkInventory("1")).thenReturn(inventoryResponse);
		Assert.assertEquals(false, inventoryResponse.getStatus());
	}

	@Test
	public void test_UserDetailsResponse_When_Success() {
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setMessage("User Adderess details");
		userDetailsResponse.setStatus(200);
		List<Result> resultList = new ArrayList<>();
		Result resultObj = new Result();
		resultObj.setFirstName("Admin");
		resultObj.setLastName("admin");
		resultObj.setUsername("admin");
		resultObj.setBillingAddress("HCL IT CITY Lucknow");
		resultObj.setShippingAddress("HCL IT City LKO");
		resultObj.setContact(1234567890);
		resultList.add(resultObj);
		userDetailsResponse.setResult(resultList);
		when(mockCheckoutServiceImpl.getUserAddress("1")).thenReturn(userDetailsResponse);
		Assert.assertEquals("User Adderess details", userDetailsResponse.getMessage());
	}
	
	@Test
	public void test_UserDetailsResponse_When_Fail() {
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setMessage("Some thing wrong");
		userDetailsResponse.setStatus(200);
		List<Result> resultList = new ArrayList<>();
		Result resultObj = null;
		resultList.add(resultObj);
		userDetailsResponse.setResult(resultList);
		when(mockCheckoutServiceImpl.getUserAddress("1")).thenReturn(userDetailsResponse);
		Assert.assertEquals("Some thing wrong", userDetailsResponse.getMessage());
	}
	

	@Test
	public void test_placeOrder_When_Success() {
		PlaceOderResponse placeOrder = new PlaceOderResponse();

		CartItemResponse cartItemResponse = new CartItemResponse();
		cartItemResponse.setMessage("Place order successfully");
		cartItemResponse.setCode(200);
		cartItemResponse.setStatus(true);

		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setMessage("User Adderess details");
		userDetailsResponse.setStatus(200);

		// set address
		Address address = new Address();
		address.setFirstName("Ranjeet");
		address.setLastName("Singh");

		List<Cartitem> cartItemList = new ArrayList<>();
		Cartitem cartItemObj = new Cartitem();
		cartItemObj.setProductname("Mobile");
		cartItemObj.setProductid("101");
		cartItemObj.setPrice("200");
		cartItemList.add(cartItemObj);
		placeOrder.setCartitem(cartItemList);
		placeOrder.setAddress(address);
		when(mockCheckoutServiceImpl.placeOrder(cartItemResponse, userDetailsResponse)).thenReturn(placeOrder);
		Assert.assertEquals("Mobile", placeOrder.getCartitem().get(0).getProductname());
	}
	
	@Test
	public void test_placeOrder_When_Fail() {
		PlaceOderResponse placeOrder = new PlaceOderResponse();

		CartItemResponse cartItemResponse = new CartItemResponse();
		cartItemResponse.setMessage("Place order successfully");
		cartItemResponse.setCode(200);
		cartItemResponse.setStatus(true);

		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setMessage("User Adderess details");
		userDetailsResponse.setStatus(200);

		// set address
		Address address = new Address();
		address.setFirstName("Ranjeet");
		address.setLastName("Singh");

		List<Cartitem> cartItemList = new ArrayList<>();
		Cartitem cartItemObj = new Cartitem();
		cartItemObj.setProductname(null);
		cartItemObj.setProductid("101");
		cartItemObj.setPrice("200");
		cartItemList.add(cartItemObj);
		placeOrder.setCartitem(cartItemList);
		placeOrder.setAddress(address);
		when(mockCheckoutServiceImpl.placeOrder(cartItemResponse, userDetailsResponse)).thenReturn(placeOrder);
		Assert.assertEquals(null, placeOrder.getCartitem().get(0).getProductname());
	}
	

	@Test
	public void test_updatInventory_When_Success() {
		CommonRequestModel commonRequestModel = new CommonRequestModel();
		commonRequestModel.setProductID("101");
		commonRequestModel.setQuantity(1);

		CommonResponseModel commonResponseModel = new CommonResponseModel();
		commonResponseModel.setStatus(true);
		commonResponseModel.setMessage(AppConstant.INVENTORY_UPDATE_SUCCESSFULLY);
		commonResponseModel.setStatusCode(200);
		when(mockCheckoutServiceImpl.updatInventory(commonRequestModel)).thenReturn(commonResponseModel);
		Assert.assertEquals(true, commonResponseModel.getStatus());
	}
	
	@Test
	public void test_updatInventory_When_Fail() {
		CommonRequestModel commonRequestModel = new CommonRequestModel();
		commonRequestModel.setProductID("101");
		commonRequestModel.setQuantity(1);

		CommonResponseModel commonResponseModel = new CommonResponseModel();
		commonResponseModel.setStatus(false);
		commonResponseModel.setMessage(AppConstant.INVENTORY_UPDATE_SUCCESSFULLY);
		commonResponseModel.setStatusCode(200);
		when(mockCheckoutServiceImpl.updatInventory(commonRequestModel)).thenReturn(commonResponseModel);
		Assert.assertEquals(false, commonResponseModel.getStatus());
	}

	@Test
	public void test_removeCartItem_When_Success() {

		CommonResponseModel commonResponseModel = new CommonResponseModel();
		commonResponseModel.setStatus(true);
		commonResponseModel.setMessage(AppConstant.REMOVE_ITEM_SUCCESSFULLY);
		commonResponseModel.setStatusCode(200);
		when(mockCheckoutServiceImpl.removeCartItem("1")).thenReturn(commonResponseModel);
		Assert.assertEquals(true, commonResponseModel.getStatus());

	}

	@Test
	public void test_removeCartItem_When_Fail() {

		CommonResponseModel commonResponseModel = new CommonResponseModel();
		commonResponseModel.setStatus(false);
		commonResponseModel.setMessage(AppConstant.REMOVE_ITEM_SUCCESSFULLY);
		commonResponseModel.setStatusCode(200);
		when(mockCheckoutServiceImpl.removeCartItem("1")).thenReturn(commonResponseModel);
		Assert.assertEquals(false, commonResponseModel.getStatus());

	}

	
	@Test
	public void test_creatOrder_When_Success() {

		CartOrderRequest cartOrderRequest = cartOrderRequest();
		CreateOrderResponse createOrderResponse = fetchOrderDetailsSuccessObj();
		when(mockCheckoutServiceImpl.creatOrder(cartOrderRequest)).thenReturn(createOrderResponse);
		Assert.assertEquals(true, createOrderResponse.getStatus());

	}
	
	@Test
	public void test_creatOrder_When_Fail() {

		CartOrderRequest cartOrderRequest = cartOrderRequest();
		CreateOrderResponse createOrderResponse = fetchOrderDetailsFailureObj();
		when(mockCheckoutServiceImpl.creatOrder(cartOrderRequest)).thenReturn(createOrderResponse);
		Assert.assertEquals(false, createOrderResponse.getStatus());

	}
	
	
	@Test
	public void test_orderDetails_When_Success() {

		CreateOrderResponse createOrderResponse = fetchOrderDetailsSuccessObj();
		Createorder order = new Createorder();
		order.setUserid("1");
		order.setAmount("200");
		order.setCurrency("INR");
		order.setOrderid("1");
		order.setCustomername("HCL");
		when(mockCheckoutServiceImpl.orderDetails(createOrderResponse)).thenReturn(order);
		Assert.assertEquals("HCL", order.getCustomername());

	}
	
	
	@Test
	public void test_creatShipment_When_Success() {
		ShipmentRequest shipmentRequest = createShipmentTestSuccessObj();
		CommonResponseModel shipmentResponse = new CommonResponseModel();
		shipmentResponse.setMessage("Success");
		shipmentResponse.setStatus(true);
		shipmentResponse.setStatusCode(200);
		when(mockCheckoutServiceImpl.creatShipment(shipmentRequest)).thenReturn(shipmentResponse);
		Assert.assertEquals(true, shipmentResponse.getStatus());

	}
	
	@Test
	public void test_creatShipment_When_Fail() {
		ShipmentRequest shipmentRequest = createShipmentTestSuccessObj();
		CommonResponseModel shipmentResponse = new CommonResponseModel();
		shipmentResponse.setMessage("fail");
		shipmentResponse.setStatus(false);
		shipmentResponse.setStatusCode(200);
		when(mockCheckoutServiceImpl.creatShipment(shipmentRequest)).thenReturn(shipmentResponse);
		Assert.assertEquals(false, shipmentResponse.getStatus());

	}
	
	

}
