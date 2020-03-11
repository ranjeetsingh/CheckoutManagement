package com.checkout.management.iservice;

import com.checkout.management.model.response.cartitem.CartItemResponse;
import com.checkout.management.model.response.inventory.InventoryResponse;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.userdetails.UserDetailsResponse;

/**
 * Define all checkout related methods
 * 
 * @author RanjeetSi
 *
 */
public interface ICheckoutService {

	/**
	 * Method fetch cart item from cart
	 * 
	 * @param userId
	 * @return CartItemResponse
	 */
	CartItemResponse getCartItem(String userId);

	/**
	 * Method for check item exist in inventory
	 * 
	 * @param productId
	 * @return InventoryResponse
	 */
	InventoryResponse checkInventory(String productId);

	/**
	 * Method for fetch user address
	 * 
	 * @param userId
	 * @return UserDetailsResponse
	 */
	UserDetailsResponse getUserAddress(String userId);

	/**
	 * Method fetch for place order
	 * 
	 * @param cartItemResponse
	 * @param userDetailsResponse
	 * @return PlaceOderResponse
	 */
	PlaceOderResponse placeOrder(CartItemResponse cartItemResponse, UserDetailsResponse userDetailsResponse);

	// void creatOrder(String userId, String itemObject);
	// void removeCartItem(String userId);
	// void updatInventory(String productId, String quantity);
	// void creatShipment(String orderId, String userId);

}
