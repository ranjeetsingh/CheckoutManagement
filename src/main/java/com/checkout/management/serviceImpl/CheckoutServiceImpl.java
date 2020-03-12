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
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.cartitem.CartItemResponse;
import com.checkout.management.model.response.inventory.InventoryResponse;
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
				"  \"message\": \"checkout Item successfully\",\r\n" + 
				"  \"code\": 200,\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"cartitem\": [\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"1\",\r\n" + 
				"        \"productname\": \"mobile\",\r\n" + 
				"        \"price\": \"201\",\r\n" + 
				"        \"description\": \"Blcak color mobile with 4 GB\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"2\",\r\n" + 
				"        \"productname\": \"mobile\",\r\n" + 
				"        \"price\": \"202\",\r\n" + 
				"        \"description\": \"Blcak color mobile with 4 GB\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"3\",\r\n" + 
				"        \"productname\": \"mobile\",\r\n" + 
				"        \"price\": \"203\",\r\n" + 
				"        \"description\": \"Blcak color mobile with 4 GB\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"productid\": \"4\",\r\n" + 
				"        \"productname\": \"mobile\",\r\n" + 
				"        \"price\": \"204\",\r\n" + 
				"        \"description\": \"Blcak color mobile with 4 GB\"\r\n" + 
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
		Cartitem cartItemObj = new Cartitem();
		cartItemObj.setProductname(cartItemResponse.getData().getCartitem().get(0).getProductname());
		cartItemObj.setDescription(cartItemResponse.getData().getCartitem().get(0).getDescription());
		cartItemObj.setProductid(cartItemResponse.getData().getCartitem().get(0).getProductid());
		cartItemObj.setPrice(cartItemResponse.getData().getCartitem().get(0).getPrice());
		cartItemList.add(cartItemObj);
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
	
	/**
	 * Call the method when inventory micorservices down
	 * @param userId
	 * @return InventoryResponse
	 */
	
	@SuppressWarnings("unused")
	private InventoryResponse getInventoryStatusFallback(String productId) {
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
	
	@SuppressWarnings("unused")
	private CartItemResponse getCartItemFallback(String userId) {
		CartItemResponse cartItem = new CartItemResponse();
		cartItem.setMessage(AppConstant.CART_SERVICE_DOWN_MESSAGE);
		return cartItem;
	}

	/**
	 * Call the method when UserRole micorservices down
	 * @param userId
	 * @return UserDetailsResponse
	 */
	@SuppressWarnings("unused")
	private UserDetailsResponse getUserAddressFallback(String userId) {
		System.out.println("User Service is down!!! fallback route enabled...");
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		userDetailsResponse.setMessage(AppConstant.USER_SERVICE_DOWN_MESSAGE);
		return userDetailsResponse;
	}

	

}
