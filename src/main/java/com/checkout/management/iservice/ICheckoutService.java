package com.checkout.management.iservice;

public interface ICheckoutService {

	//parameter will change latter
	//return type will change as per requirement
	//only define method
	void getCartItem(String userId);
	void getInventoryStatus(String productId);
	void getUserAddress(String userId);
	void creatOrder(String userId, String itemObject);
	void removeCartItem(String userId);
	void updatInventory(String productId, String quantity);
	void creatShipment(String orderId, String userId);
}
