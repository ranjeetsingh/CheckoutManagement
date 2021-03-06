package com.checkout.management.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.apputil.ConstantUrl;
import com.checkout.management.iservice.ICheckoutService;
import com.checkout.management.model.request.CommonRequestModel;
import com.checkout.management.model.request.ShipmentRequest;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.request.cartorder.Data;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.cartitem.CartItemResponse;
import com.checkout.management.model.response.inventory.InventoryResponse;
import com.checkout.management.model.response.order.Createorder;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.placeorder.Address;
import com.checkout.management.model.response.placeorder.Cartitem;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.userdetails.Result;
import com.checkout.management.model.response.userdetails.UserDetailsResponse;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * All checkout method for call other micro servies
 * @author RanjeetSi
 *
 */
@Service
public class CheckoutServiceImpl implements ICheckoutService {
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Get All cart item from cart micro services
	 * @param userId
	 * @return CartItemResponse
	 */
	@Override
	//@HystrixCommand(fallbackMethod = "getCartItemFallback")
	public CartItemResponse getCartItem(String userId) {
		String userId1 = "20";
		//CartItemResponse cartItemResponse = restTemplate.getForObject(ConstantUrl.getCartItemUrl + userId1,
		//		CartItemResponse.class);
		String json = "{\r\n" + 
				"  \"status\": true,\r\n" + 
				"  \"message\": \"get payment method successfully\",\r\n" + 
				"  \"code\": 200,\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"cartitem\": [\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"101\",\r\n" + 
				"        \"productname\": \"mobile\",\r\n" + 
				"        \"price\": \"200\",\r\n" + 
				"		\"quantity\" :4,\r\n" + 
				"        \"description\": \"Blcak color mobile with 4 GB\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"102\",\r\n" + 
				"        \"productname\": \"HP laptop\",\r\n" + 
				"        \"price\": \"40000\",\r\n" + 
				"		\"quantity\" :1,\r\n" + 
				"        \"description\": \"Blcak color HP Laptop with 8 GB\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"103\",\r\n" + 
				"        \"productname\": \"Data card\",\r\n" + 
				"        \"price\": \"2500\",\r\n" + 
				"		\"quantity\" :1,\r\n" + 
				"        \"description\": \"Airtel Data card\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"104\",\r\n" + 
				"        \"productname\": \"Dell Laptop\",\r\n" + 
				"        \"price\": \"45000\",\r\n" + 
				"		\"quantity\" :1,\r\n" + 
				"        \"description\": \"Silver color laptop with 8 GB\"\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  }\r\n" + 
				"}";
		
		CartItemResponse cartItemResponse = new Gson().fromJson(json, CartItemResponse.class);
		return cartItemResponse;
	}

	/**
	 * Check item exist in Inventory microservices
	 * @param product
	 * @return InventoryResponse
	 */
	
	@Override
	//@HystrixCommand(fallbackMethod = "getInventoryStatusFallback")
	public InventoryResponse checkInventory(String productId) {
		// check item in inventory
		//String productId1 = "202";
		//InventoryResponse inventoryResponse = restTemplate.getForObject(ConstantUrl.getInventoryUrl + productId1,
		//		InventoryResponse.class);
		String json = "{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"id\": 9,\r\n" + 
				"    \"productID\": \"109\",\r\n" + 
				"    \"quantity\": 2\r\n" + 
				"  },\r\n" + 
				"  \"message\": \"success\",\r\n" + 
				"  \"status\": true,\r\n" + 
				"  \"statusCode\": 200\r\n" + 
				"}";
		InventoryResponse inventoryResponse = new Gson().fromJson(json, InventoryResponse.class);
		return inventoryResponse;
	}

	/**
	 * Get the user address from UserMangament microservices
	 * @param userId
	 * @return UserDetailsResponse
	 */
	
	@Override
	//@HystrixCommand(fallbackMethod = "getUserAddressFallback")
	public UserDetailsResponse getUserAddress(String userId) {
		// get user details
		//String userId1 = "202";
		//UserDetailsResponse userDetailsResponse = restTemplate.getForObject(ConstantUrl.getUserDetailsUrl + userId1,
		//		UserDetailsResponse.class);
		
		/*
		 * String json = "{\r\n" + "    \"status\": 200,\r\n" +
		 * "    \"message\": \"success\",\r\n" + "    \"result\": [\r\n" +
		 * "        {\r\n" + "            \"id\": 1,\r\n" +
		 * "            \"firstName\": \"admin\",\r\n" +
		 * "            \"lastName\": \"admin\",\r\n" +
		 * "            \"username\": \"admin\",\r\n" +
		 * "            \"password\": null,\r\n" +
		 * "            \"email\": \"admin@gmail.com\",\r\n" +
		 * "            \"contact\": 750339087799,\r\n" +
		 * "            \"billingAddress\": \"HCL IT CITY Sultanpur Road\",\r\n" +
		 * "            \"shippingAddress\": \"HCL IT CITY Sultanpur Road\",\r\n" +
		 * "            \"role\": [\r\n" + "                \"ADMIN\"\r\n" +
		 * "            ]\r\n" + "        }\r\n" + "    ]\r\n" + "}";
		 * UserDetailsResponse userDetailsResponse = new Gson().fromJson(json,
		 * UserDetailsResponse.class);
		 */
		 
		
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
		System.out.print(userDetailsResponse.getResult().get(0).getFirstName() + "=====>");
		 
		return userDetailsResponse;
	}

	/**
	 * Return the place order response
	 * @param userId
	 * @return PlaceOderResponse
	 */
	
	@Override
	public PlaceOderResponse placeOrder(CartItemResponse cartItemResponse, UserDetailsResponse userDetailsResponse) {
		PlaceOderResponse placeOrder = new PlaceOderResponse();
		//set address
		Address address = new Address();
		address.setFirstName(userDetailsResponse.getResult().get(0).getFirstName());
		address.setLastName(userDetailsResponse.getResult().get(0).getLastName());
		address.setUsername(userDetailsResponse.getResult().get(0).getUsername());
		address.setBillingAddress(userDetailsResponse.getResult().get(0).getBillingAddress());
		address.setShippingAddress(userDetailsResponse.getResult().get(0).getShippingAddress());
		address.setContact(userDetailsResponse.getResult().get(0).getContact());
		placeOrder.setAddress(address);
		//set item in list
		List<Cartitem> cartItemList = new ArrayList<>();
		for (Cartitem cartitem : cartItemResponse.getData().getCartitem()) {
			cartItemList.add(cartitem);
		}
		/*
		 * Cartitem cartItemObj = new Cartitem();
		 * cartItemObj.setProductname(cartItemResponse.getData().getCartitem().get(0).
		 * getProductname());
		 * cartItemObj.setDescription(cartItemResponse.getData().getCartitem().get(0).
		 * getDescription());
		 * cartItemObj.setProductid(cartItemResponse.getData().getCartitem().get(0).
		 * getProductid());
		 * cartItemObj.setPrice(cartItemResponse.getData().getCartitem().get(0).getPrice
		 * ()); cartItemList.add(cartItemObj);
		 */
		placeOrder.setCartitem(cartItemList);
		return placeOrder;
	}
	
	/**
	 * This method use for call inventory api for update item quantity in inventory
	 * 
	 * @param commonRequestModel
	 * @return CommonResponseModel
	 */
	@Override
	//@HystrixCommand(fallbackMethod = "updateInventoryFallback")
	public CommonResponseModel updatInventory(CommonRequestModel commonRequestModel) {

		CommonResponseModel commonResponseModel = new CommonResponseModel();
		/*
		 * String url = ConstantUrl.updateInventory; Gson gson = new Gson(); String
		 * requestBody = gson.toJson(commonRequestModel); HttpHeaders headers = new
		 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
		 * HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
		 * ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT,
		 * entity, String.class); String responseBody = response.getBody();
		 * commonResponseModel = new
		 * Gson().fromJson(responseBody,CommonResponseModel.class);
		 */
		commonResponseModel.setStatus(true);
		commonResponseModel.setMessage(AppConstant.INVENTORY_UPDATE_SUCCESSFULLY);
		commonResponseModel.setStatusCode(200);
		return commonResponseModel;
	}

	/**
	 * This method use for call cart remove item api for remove item from cart service
	 * 
	 * @param userId
	 * @return CommonResponseModel
	 */
	@Override
	//@HystrixCommand(fallbackMethod = "removeCartItemFallback")
	public CommonResponseModel removeCartItem(String userId) {
		//CommonResponseModel removeCartItemResponse = restTemplate.getForObject(ConstantUrl.getCartItemUrl + userId,CommonResponseModel.class);
		String json = "{\r\n" + 
				"  \"status\": true,\r\n" + 
				"  \"message\": \" item remove from cart successfully\",\r\n" + 
				"  \"statusCode\": 200,\r\n" + 
				"  \"data\": null\r\n" + 
				"}";
		CommonResponseModel commonResponseModel = new Gson().fromJson(json, CommonResponseModel.class);
		/*
		 * CommonResponseModel commonResponseModel = new CommonResponseModel();
		 * commonResponseModel.setStatus(true);
		 * commonResponseModel.setMessage(AppConstant.REMOVE_ITEM_SUCCESSFULLY);
		 * commonResponseModel.setStatusCode(200);
		 */
		return commonResponseModel;
	}
	
	@Override
	//@HystrixCommand(fallbackMethod = "creatOrderFallback")
	public CreateOrderResponse creatOrder(CartOrderRequest cartOrderRequest) {
		
		/*
		 * String url = ConstantUrl.createOrderUrl; Gson gson = new Gson(); String
		 * requestBody = gson.toJson(cartOrderRequest); HttpHeaders headers = new
		 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
		 * HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
		 * ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
		 * entity, String.class); String responseBody = response.getBody();
		 */
		String responseBody = "{\r\n" + 
				"  \"status\": true,\r\n" + 
				"  \"message\": \"create order  successfully\",\r\n" + 
				"  \"createorder\": {\r\n" + 
				"          \"userid\":\"15\",\r\n" + 
				"          \"orderid\": \"232323\",\r\n" + 
				"          \"currency\": \"INR\",\r\n" + 
				"          \"amount\": \"200\",\r\n" + 
				"         \"paymentmode\":\"NetBanking\",\r\n" + 
				"         \"customername\": \"HCL\",\r\n" + 
				"        \"shipingaddress\": \"lucknow\"\r\n" + 
				"  },\r\n" + 
				"  \"errorCode\": 0\r\n" + 
				"}";
		
		CreateOrderResponse createOrderResponse = new Gson().fromJson(responseBody, CreateOrderResponse.class);
		
		return createOrderResponse;
		
	}
	
	/**
	 * Call the method when inventory micorservices down
	 * @param userId
	 * @return InventoryResponse
	 */
	
	public InventoryResponse getInventoryStatusFallback(String productId) {
		System.out.println("Inventory Service is down!!! fallback route enabled...");
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setMessage(AppConstant.INVENTORY_SERVICE_DOWN_MESSAGE);
		return inventoryResponse;
	}

	/**
	 * Call the method when Cart micorservices down
	 * @param userId
	 * @return CartItemResponse
	 */
	
	public CartItemResponse getCartItemFallback(String userId) {
		CartItemResponse cartItem = new CartItemResponse();
		cartItem.setMessage(AppConstant.CART_SERVICE_DOWN_MESSAGE);
		return cartItem;
	}

	/**
	 * Call the method when UserRole micorservices down
	 * @param userId
	 * @return UserDetailsResponse
	 */
	public UserDetailsResponse getUserAddressFallback(String userId) {
		System.out.println("User Service is down!!! fallback route enabled...");
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setMessage(AppConstant.USER_SERVICE_DOWN_MESSAGE);
		return userDetailsResponse;
	}

	/**
	 * Call the method when inventory micorservices down
	 * @param userId
	 * @return UserDetailsResponse
	 */
	public CommonResponseModel updateInventoryFallback(String userId) {
		System.out.println("Inventory Service is down!!! fallback route enabled...");
		CommonResponseModel updateInventory = new CommonResponseModel();
		updateInventory.setMessage(AppConstant.INVENTORY_SERVICE_DOWN_MESSAGE);
		return updateInventory;
	}

	/**
	 * Call the method when cart service down
	 * @param userId
	 * @return UserDetailsResponse
	 */
	public CommonResponseModel removeCartItemFallback(String userId) {
		System.out.println("Cart Service is down!!! fallback route enabled...");
		CommonResponseModel userDetailsResponse = new CommonResponseModel();
		userDetailsResponse.setMessage(AppConstant.CART_SERVICE_DOWN_MESSAGE);
		return userDetailsResponse;
	}
	
	/**
	 * Call the method when Order service down
	 * @param userId
	 * @return UserDetailsResponse
	 */
	public CreateOrderResponse creatOrderFallback(String userId) {
		System.out.println("Order Service is down!!! fallback route enabled...");
		CreateOrderResponse orderResp = new CreateOrderResponse();
		orderResp.setMessage(AppConstant.ORDER_SERVICE_DOWN_MESSAGE);
		return orderResp;
	}

	/**
	 * This method return the createOrder response
	 * 
	 * @param CreateOrderResponse
	 * @return {@link Createorder}
	 */
	@Override
	public Createorder orderDetails(CreateOrderResponse createOrderResponse) {
		Createorder order = new Createorder();
		order.setUserid(createOrderResponse.getCreateorder().getUserid());
		order.setAmount(createOrderResponse.getCreateorder().getAmount());
		order.setCurrency(createOrderResponse.getCreateorder().getCurrency());
		order.setOrderid(createOrderResponse.getCreateorder().getOrderid());
		order.setCustomername(createOrderResponse.getCreateorder().getCustomername());
		order.setPaymentmode(createOrderResponse.getCreateorder().getPaymentmode());
		order.setShipingaddress(createOrderResponse.getCreateorder().getShipingaddress());
		return order;
	}

	/**
	 * create shipment status after payment successfully.
	 * @param ShipmentRequest
	 * @return {@link CommonResponseModel}
	 */
	@Override
	public CommonResponseModel creatShipment(ShipmentRequest shipmentRequest) {
		
		/*
		 * String url = ConstantUrl.createShipmentUrl; Gson gson = new Gson(); String
		 * requestBody = gson.toJson(shipmentRequest); HttpHeaders headers = new
		 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
		 * HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
		 * ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
		 * entity, String.class); String responseBody = response.getBody();
		 */
		 
		String responseBody = "{\r\n" + 
				"  \"status\": true,\r\n" + 
				"  \"message\": \" Create shipment successfully\",\r\n" + 
				"  \"statusCode\": 200,\r\n" + 
				"  \"data\": null\r\n" + 
				"}";
		
		CommonResponseModel shipmentResponse = new Gson().fromJson(responseBody, CommonResponseModel.class);
		return shipmentResponse;
	}

	

}
