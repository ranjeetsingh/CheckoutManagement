package com.checkout.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.iservice.ICheckoutService;
import com.checkout.management.model.request.CommonRequestModel;
import com.checkout.management.model.request.ShipmentRequest;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.request.cartorder.Data;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.ResponseModel;
import com.checkout.management.model.response.cartitem.CartItemResponse;
import com.checkout.management.model.response.inventory.InventoryResponse;
import com.checkout.management.model.response.order.Createorder;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.userdetails.UserDetailsResponse;

/**
 * All checkout related action method in the controller
 * 
 * @author RanjeetSi
 *
 */
@RestController
public class CheckoutController {
	@Autowired
	private ICheckoutService checkOutProdcut;

	/**
	 * For checkout Product
	 * 
	 * @param productId
	 * @param userId
	 * @return ResponseEntity<Object>
	 */
	@SuppressWarnings("unused")
	@GetMapping("/checkoutProduct/{productId}/{userId}")
	public ResponseEntity<Object> checkoutProduct(@PathVariable("productId") String productId,
			@PathVariable("userId") String userId) {
		ResponseEntity<Object> responseEntity = null;
		try {
			// for get cart item
			CartItemResponse cartItemResponse = checkOutProdcut.getCartItem(productId);
			if (cartItemResponse.getMessage().equalsIgnoreCase(AppConstant.CART_SERVICE_DOWN_MESSAGE)) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, cartItemResponse.getMessage(), null, 0), HttpStatus.OK);
				return responseEntity;
			}
			if (cartItemResponse.getData() == null && cartItemResponse.getData().getCartitem().size() == 0) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, AppConstant.ITEMS_NOT_EXIST_IN_CART, null, 0), HttpStatus.OK);
				return responseEntity;
			}

			// check item in inventory
			InventoryResponse inventoryStatus = checkOutProdcut.checkInventory(productId);
			if (inventoryStatus.getMessage().equalsIgnoreCase(AppConstant.INVENTORY_SERVICE_DOWN_MESSAGE)) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, inventoryStatus.getMessage(), null, 0), HttpStatus.OK);
				return responseEntity;
			}
			if (inventoryStatus.getData() == null) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, AppConstant.PRODUCT_IS_OUT_OF_STOCK, null, 0), HttpStatus.OK);
				return responseEntity;
			}

			// for get address from user
			UserDetailsResponse userDetailsResponse = checkOutProdcut.getUserAddress(userId);
			if (userDetailsResponse.getResult() == null
					&& userDetailsResponse.getResult().get(0).getShippingAddress() == null) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, AppConstant.PLEASE_COMPLETE_BILLING_ADDRESS, null, 0), HttpStatus.OK);
				return responseEntity;
			}

			PlaceOderResponse placeOrder = checkOutProdcut.placeOrder(cartItemResponse, userDetailsResponse);
			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, AppConstant.CHECKOUT_PRODUCT_SUCCESSFULLY, placeOrder, 0), HttpStatus.OK);

			return responseEntity;

		} catch (Exception e) {

			responseEntity = new ResponseEntity<Object>(new ResponseModel(false, e.getMessage(), null, 0),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return responseEntity;
	}

	@PostMapping("/inventory/updateInventory/")
	public ResponseEntity<Object> updateInventory(@RequestBody CommonRequestModel commonRequestModel) {
		ResponseEntity<Object> responseEntity = null;
		try {
			CommonResponseModel inventoryResponseModel = checkOutProdcut.updatInventory(commonRequestModel);
			if (inventoryResponseModel.getStatus() == false) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, inventoryResponseModel.getMessage(), null, 0), HttpStatus.OK);
				return responseEntity;
			}
			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, inventoryResponseModel.getMessage(), null, 0), HttpStatus.OK);

			return responseEntity;

		} catch (Exception e) {

			responseEntity = new ResponseEntity<Object>(new ResponseModel(false, e.getMessage(), null, 0),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	
	@GetMapping("/cart/removeCartItem/{userId}")
	public ResponseEntity<Object> removeCartItem(@PathVariable("userId") String userId) {
		ResponseEntity<Object> responseEntity = null;
		try {
			CommonResponseModel cartItemResponseModel = checkOutProdcut.removeCartItem(userId);
			if (cartItemResponseModel.getStatus() == false) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, cartItemResponseModel.getMessage(), null, 0), HttpStatus.OK);
				return responseEntity;
			}
			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, cartItemResponseModel.getMessage(), null, 0), HttpStatus.OK);

			return responseEntity;

		} catch (Exception e) {

			responseEntity = new ResponseEntity<Object>(new ResponseModel(false, e.getMessage(), null, 0),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@PostMapping("/order/createOrder")
	public ResponseEntity<Object> createOrder(@RequestBody CartOrderRequest cartOrderRequest) {
		ResponseEntity<Object> responseEntity = null;
		try {
			CreateOrderResponse createOrderResponse = checkOutProdcut.creatOrder(cartOrderRequest);
			if (createOrderResponse.getStatus() == false) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, createOrderResponse.getMessage(), null, 0), HttpStatus.OK);
				return responseEntity;
			}
			Createorder orderData = checkOutProdcut.orderDetails(createOrderResponse);
			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, createOrderResponse.getMessage(), orderData, 0), HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {

			responseEntity = new ResponseEntity<Object>(new ResponseModel(false, e.getMessage(), null, 0),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	
	@PostMapping("/cart/createshipment")
	public ResponseEntity<Object> createShipment(@RequestBody ShipmentRequest shipmentRequest) {
		ResponseEntity<Object> responseEntity = null;
		try {
			CommonResponseModel shipmentResponseModel = checkOutProdcut.creatShipment(shipmentRequest);
			if (shipmentResponseModel.getStatus() == false) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, shipmentResponseModel.getMessage(), null, 0), HttpStatus.OK);
				return responseEntity;
			}
			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, shipmentResponseModel.getMessage(), null, 0), HttpStatus.OK);

			return responseEntity;

		} catch (Exception e) {

			responseEntity = new ResponseEntity<Object>(new ResponseModel(false, e.getMessage(), null, 0),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;
	}
	

}
