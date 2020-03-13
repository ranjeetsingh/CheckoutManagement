package com.checkout.management.iservice;

import com.checkout.management.model.request.CommonRequestModel;
import com.checkout.management.model.request.ShipmentRequest;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.cartitem.CartItemResponse;
import com.checkout.management.model.response.inventory.InventoryResponse;
import com.checkout.management.model.response.order.Createorder;
import com.checkout.management.model.response.order.CreateOrderResponse;
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

	/**
	 * Method for create order 
	 * @param cartOrderRequest
	 * @return CreateOrderResponse
	 */
	CreateOrderResponse creatOrder(CartOrderRequest cartOrderRequest);
	
	/**
	 * create object of order details
	 * @param CreateOrderResponse
	 * @return {@link Createorder}
	 */
	Createorder orderDetails(CreateOrderResponse createOrderResponse);

	/**
	 * create shipment of product after payment successfull
	 * @param shipmentRequest
	 * @return {@link CommonResponseModel}
	 */
	CommonResponseModel creatShipment(ShipmentRequest shipmentRequest);

}
