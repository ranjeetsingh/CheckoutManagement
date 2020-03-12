package com.checkout.management.iservice;

import com.checkout.management.model.request.CommonRequestModel;
import com.checkout.management.model.response.CommonResponseModel;
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

	/**
	 * Method for use update item in inventory
	 * 
	 * @param commonRequestModel
	 * @return CommonResponseModel
	 */
	CommonResponseModel updatInventory(CommonRequestModel commonRequestModel);

	/**
	 * Method for remove item from cart
	 * 
	 * @param userId
	 * @return CommonResponseModel
	 */
	CommonResponseModel removeCartItem(String userId);

	// void creatOrder(String userId, String itemObject);

	// void creatShipment(String orderId, String userId);

}
