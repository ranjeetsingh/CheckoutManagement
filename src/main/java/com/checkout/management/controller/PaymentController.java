package com.checkout.management.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.exception.ExceptionResponse;
import com.checkout.management.iservice.IPaymentService;
import com.checkout.management.model.request.CommonRequestModel;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.GatewayData;
import com.checkout.management.model.response.PaymentGatewayResponse;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.ResponseModel;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.order.Createorder;

/**
 * All payment related action method
 * 
 * @author RanjeetSi
 *
 */
@RestController
public class PaymentController {
	@Autowired
	private IPaymentService iPaymentService;

	/**
	 * This method use to fetch the payment mode
	 * 
	 * @return {@link ResponseEntity}
	 */
	@GetMapping("/getPaymentMethod")
	public ResponseEntity<Object> PaymentMethod() {
		ResponseEntity<Object> responseEntity = null;
		try {
		PaymentModeModel paymentModeModel = iPaymentService.getPaymentMode();
		if (paymentModeModel == null) {
			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, AppConstant.PAYMENT_IS_NOT_AVAILABLE, null, 0), HttpStatus.OK);
			return responseEntity;
		}
		responseEntity = new ResponseEntity<Object>(
				new ResponseModel(true, AppConstant.LIST_OF_PAYMENT_MODE, paymentModeModel, 0), HttpStatus.OK);

		return responseEntity;
		}catch (Exception e) {

			responseEntity = new ResponseEntity<Object>(new ResponseModel(false, e.getMessage(), null, 0),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	/**
	 * This method is use for payment Internally multiple api call 1-Use create
	 * order api call from cart microservices 2-Use remove item api call from cart
	 * microservices 3-Use dummy payment method and get dummay gateway response
	 * 4-Use the creat shipment api from shipment microservices
	 * 
	 * @param cartOrderRequest
	 * @return
	 */
	@PostMapping("/payment")
	public ResponseEntity<Object> payment(@RequestBody CartOrderRequest cartOrderRequest) {
		ResponseEntity<Object> responseEntity = null;
		try {
			// create order and fetch order response from cart micro services
			CreateOrderResponse createOrderResponse = iPaymentService.fetchOrderDetails(cartOrderRequest);
			// remove user item from Cart in cart micro services
			String userId = (String) cartOrderRequest.getData().getAddress().getId();
			iPaymentService.removeCartItem(userId);

			if (createOrderResponse.getStatus() == false) {
				responseEntity = new ResponseEntity<Object>(
						new ResponseModel(true, createOrderResponse.getMessage(), null, 0), HttpStatus.OK);
				return responseEntity;
			}
			// Get the gateway response form gateway api
			GatewayData gatewayResponse = iPaymentService.payment(createOrderResponse);
			// Initiate the shipment
			iPaymentService.initiateProductShipment(gatewayResponse);

			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, AppConstant.PAYMENT_SUCCESSFULL, gatewayResponse, 0), HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {

			responseEntity = new ResponseEntity<Object>(new ResponseModel(false, e.getMessage(), null, 0),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return responseEntity;
	}
}
