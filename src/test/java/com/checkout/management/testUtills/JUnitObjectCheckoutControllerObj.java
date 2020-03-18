package com.checkout.management.testUtills;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.checkout.management.model.request.CommonRequestModel;
import com.checkout.management.model.response.inventory.InventoryResponse;
import com.checkout.management.model.response.placeorder.Address;
import com.checkout.management.model.response.placeorder.Cartitem;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.userdetails.Result;
import com.checkout.management.model.response.userdetails.UserDetailsResponse;

class JUnitObjectCheckoutControllerObj {

	public PlaceOderResponse placeOrderData() {
		PlaceOderResponse placeOrder = new PlaceOderResponse();

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

		return placeOrder;

	}

	public UserDetailsResponse userDetailsResponse() {
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
		return userDetailsResponse;
	}
	
	public InventoryResponse inventoryStatus() {
		InventoryResponse inventoryResponse = new InventoryResponse();
		inventoryResponse.setMessage("success");
		inventoryResponse.setStatus(false);
		inventoryResponse.setStatusCode(200);
		return inventoryResponse;
	}
	public CommonRequestModel commonRequestModelObj(){
		CommonRequestModel commonRequestModel = new CommonRequestModel();
		commonRequestModel.setProductID("101");
		commonRequestModel.setQuantity(1);
		return commonRequestModel;
	}

}
