package com.checkout.management.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.checkout.management.controller.CheckoutController;
import com.checkout.management.iservice.IPaymentService;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.GatewayData;
import com.checkout.management.model.response.PaymentGatewayResponse;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.order.Createorder;
import com.google.gson.Gson;

/**
 * All payment related method
 * 
 * @author RanjeetSi
 *
 */
@Service
public class PaymentServiceImpl implements IPaymentService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CheckoutServiceImpl checkoutServiceImpl;
	/**
	 * This method will return the list of payment mode
	 * 
	 * @return {@link PaymentModeModel}
	 */
	@Override
	public PaymentModeModel getPaymentMode() {
		PaymentModeModel paymentModeModel = new PaymentModeModel();
		paymentModeModel.setNetBanking("NetBanking");
		paymentModeModel.setCard("credit/debit/ATM card");
		return paymentModeModel;
	}

	/**
	 * This is used to pay amount and return response from gateway
	 * 
	 * @param CartOrderRequest
	 * @return {@link PaymentGatewayResponse}
	 */
	@Override
	public GatewayData payment(CreateOrderResponse orderResponse) {
		GatewayData gatewayData = new GatewayData();
		gatewayData.setPaymenturl("https://beemaauction.com/newdemo/rest/api.php?paymentsuccess");
		gatewayData.setUserid(orderResponse.getCreateorder().getUserid());
		gatewayData.setOrderid(orderResponse.getCreateorder().getOrderid());
		gatewayData.setCurrency(orderResponse.getCreateorder().getCurrency());
		gatewayData.setAmount(orderResponse.getCreateorder().getAmount());
		gatewayData.setCustomername(orderResponse.getCreateorder().getCustomername());
		gatewayData.setAddress(orderResponse.getCreateorder().getShipingaddress());
		gatewayData.setPaymentStatus("Success");
		gatewayData.setResponseCode(200);
		gatewayData.setPaymentmode(orderResponse.getCreateorder().getPaymentmode());
		return gatewayData;
	}

	/**
	 * Fetch Order details
	 * @return {@link CreateOrderResponse}
	 */
	@Override
	public CreateOrderResponse fetchOrderDetails(CartOrderRequest cartOrderRequest) {
		CreateOrderResponse orderResponse =checkoutServiceImpl.creatOrder(cartOrderRequest);
		return orderResponse;
	}

}
