package com.checkout.management.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.controller.CheckoutController;
import com.checkout.management.entity.Payment;
import com.checkout.management.exception.NotFoundException;
import com.checkout.management.iservice.IPaymentService;
import com.checkout.management.model.request.ShipmentRequest;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.GatewayData;
import com.checkout.management.model.response.PaymentGatewayResponse;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.order.Createorder;
import com.checkout.management.repository.PaymentRepository;
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
	@Autowired
	private PaymentRepository PaymentRepo;

	/**
	 * This method will return the list of payment mode
	 * 
	 * @return {@link PaymentModeModel}
	 */
	@SuppressWarnings({ "unused", "unused" })
	@Override
	public PaymentModeModel getPaymentMode() {
		PaymentModeModel paymentModeModel = new PaymentModeModel();
		paymentModeModel.setNetBanking(AppConstant.NETBANKING);
		paymentModeModel.setCard(AppConstant.ATM_CARD_TYPE);
		/*
		 * if (paymentModeModel == null) { throw new
		 * NotFoundException(AppConstant.PAYMENT_MODE_LIST); }
		 */
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
		System.out.println("fetch gateway response successfully");
		GatewayData gatewayData = new GatewayData();
		gatewayData.setPaymenturl("https://beemaauction.com/newdemo/rest/api.php?paymentsuccess");
		gatewayData.setUserid(orderResponse.getCreateorder().getUserid());
		gatewayData.setOrderid(orderResponse.getCreateorder().getOrderid());
		gatewayData.setCurrency(orderResponse.getCreateorder().getCurrency());
		gatewayData.setAmount(orderResponse.getCreateorder().getAmount());
		gatewayData.setCustomername(orderResponse.getCreateorder().getCustomername());
		gatewayData.setAddress(orderResponse.getCreateorder().getShipingaddress());
		gatewayData.setPaymentStatus(AppConstant.SUCCESS);
		gatewayData.setResponseCode(200);
		gatewayData.setPaymentmode(orderResponse.getCreateorder().getPaymentmode());
		savePaymentInfo(gatewayData);
		return gatewayData;
	}

	/**
	 * Create order and fetch order details using cart Microservices
	 * 
	 * @return {@link CreateOrderResponse}
	 */
	@Override
	public CreateOrderResponse fetchOrderDetails(CartOrderRequest cartOrderRequest) {
		System.out.println("Create order successfully");
		CreateOrderResponse orderResponse = checkoutServiceImpl.creatOrder(cartOrderRequest);
		return orderResponse;
	}

	/**
	 * create shipment object for ship the product Using Shipment Microservices
	 * 
	 * @param gatewayData
	 * @return {@link ShipmentRequest}
	 */
	@Override
	public ShipmentRequest createShipmentObj(GatewayData gatewayData) {
		ShipmentRequest shipmentRequestObj = new ShipmentRequest();
		shipmentRequestObj.setUserid(gatewayData.getUserid());
		shipmentRequestObj.setOrderid(gatewayData.getOrderid());
		shipmentRequestObj.setCurrency(gatewayData.getCurrency());
		shipmentRequestObj.setAmount(gatewayData.getAmount());
		shipmentRequestObj.setPaymentmode(gatewayData.getPaymentmode());
		shipmentRequestObj.setCustomername(gatewayData.getCustomername());
		shipmentRequestObj.setAddress(gatewayData.getAddress());
		return shipmentRequestObj;
	}

	/**
	 * Initiate product shipment after payment successfully
	 * 
	 * @param shipmentRequest
	 */
	@Override
	public void initiateProductShipment(GatewayData gatewayData) {
		System.out.println("Create shipment Successfully");
		ShipmentRequest createShipmentObj = createShipmentObj(gatewayData);
		CommonResponseModel shipmentResponseModel = checkoutServiceImpl.creatShipment(createShipmentObj);
		System.out.println(shipmentResponseModel.getMessage());

	}

	/**
	 * save user payment information in database
	 * 
	 * @param gatewayData
	 */
	@Override
	@Transactional
	public void savePaymentInfo(GatewayData gatewayData) {
		System.out.println("Save payment Info in DB");
		Payment paymentEntity = new Payment();
		paymentEntity.setPaymenturl(gatewayData.getPaymenturl());
		paymentEntity.setUserid(gatewayData.getUserid());
		paymentEntity.setOrderid(gatewayData.getOrderid());
		paymentEntity.setCurrency(gatewayData.getCurrency());
		paymentEntity.setAmount(Double.valueOf(gatewayData.getAmount()));
		paymentEntity.setPaymentmode(gatewayData.getPaymentmode());
		paymentEntity.setCustomername(gatewayData.getCustomername());
		paymentEntity.setAddress(gatewayData.getAddress());
		paymentEntity.setPayment_status(gatewayData.getPaymentStatus());
		paymentEntity.setResponse_code(gatewayData.getResponseCode());
		PaymentRepo.save(paymentEntity);
	}

	/**
	 * Remove item from cart on the basis of userId this method use to call remove
	 * api from checkout controller
	 * 
	 * @param userId
	 */
	@Override
	public void removeCartItem(String userId) {
		CommonResponseModel cartItemResponseModel = checkoutServiceImpl.removeCartItem(userId);
		System.out.println(cartItemResponseModel.getMessage());

	}

}
