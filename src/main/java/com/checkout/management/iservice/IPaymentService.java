package com.checkout.management.iservice;

import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.GatewayData;
import com.checkout.management.model.response.PaymentGatewayResponse;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.order.Createorder;

/**
 * All method related to payment
 * 
 * @author RanjeetSi
 *
 */
public interface IPaymentService {
	/**
	 * This method will return list payment mode
	 * 
	 * @return {@link PaymentModeModel}y
	 */
	PaymentModeModel getPaymentMode();

	/**
	 * final payment and order
	 * 
	 * @param cartOrderRequest
	 * @return {@link PaymentGatewayResponse}
	 */
	GatewayData payment(CreateOrderResponse orderResponse);
	
	
	/**
	 * Method for create order 
	 * @param cartOrderRequest
	 * @return CreateOrderResponse
	 */
	CreateOrderResponse fetchOrderDetails(CartOrderRequest cartOrderRequest);
}
