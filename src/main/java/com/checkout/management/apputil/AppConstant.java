package com.checkout.management.apputil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * Define App constant 
 * @author RanjeetSi
 *
 */
public class AppConstant {
	public static final String USER_REGISTRATION_SUCCESSFULLY = "User registration successful";
	public static final String INVENTORY_SERVICE_DOWN_MESSAGE="No Response From Inventory Service at this moment. " + " Service will be back shortly - ";
	public static final String CART_SERVICE_DOWN_MESSAGE="No Response From Cart Service at this moment. " + " Service will be back shortly";
	public static final String USER_SERVICE_DOWN_MESSAGE="No Response From User Service at this moment. " + " Service will be back shortly";
	public static final String ITEMS_NOT_EXIST_IN_CART="No Item in cart";
	public static final String PRODUCT_IS_OUT_OF_STOCK = "Prodcut is out of stock";
	public static final String PLEASE_COMPLETE_BILLING_ADDRESS="Please complete billing address.";
	public static final String CHECKOUT_PRODUCT_SUCCESSFULLY="Check Out product successfully";
	public static final String PAYMENT_IS_NOT_AVAILABLE="Payment mode is not available";
	public static final String LIST_OF_PAYMENT_MODE="List of payment mode";
	public static final String INVENTORY_UPDATE_SUCCESSFULLY="Inventory Updated successfully";
	public static final String REMOVE_ITEM_SUCCESSFULLY= "Remove item  successfully";
	public static final String ORDER_SERVICE_DOWN_MESSAGE = "Order service is down";
	public static final String PAYMENT_SUCCESSFULL = "You payment is successfully";
	public static final String NETBANKING= "NetBanking";
	public static final String ATM_CARD_TYPE= "Credit/Debit/ATM card";
	public static final String SUCCESS = "Success";
	public static final String PAYMENT_MODE_LIST="Payment mode list is not available.";
	public static final String FAIL = "fail";
	public static final String  CREAT_ODER_SUCCESS= "create order  successfully";
	public static final String  CREAT_ODER_FAIL= "Order is not create.Something is wrong please check it.";
	public static final String CREAT_SHIPMENT_SUCCESS = "Create Shipment successfull";
	public static final String CREAT_SHIPMENT_FAIL = "Create Shipment fail";
	public static final Contact DEFAULT_CONTACT = new Contact(
			"Ranjeet Singh", 
			"http://www.hcl.com",
			"ranjeetsi@hcl.com");
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Checkout API",
			"OnLine Ecommarce API.", "1.0", "urn:tos", DEFAULT_CONTACT, "online Ecommarce 1.0",
			"http://www.hcl.com");

	public static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json"));  
}
