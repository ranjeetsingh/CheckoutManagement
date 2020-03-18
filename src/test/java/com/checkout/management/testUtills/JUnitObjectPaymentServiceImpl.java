package com.checkout.management.testUtills;

import java.util.ArrayList;
import java.util.List;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.entity.Payment;
import com.checkout.management.model.request.ShipmentRequest;
import com.checkout.management.model.request.cartorder.Address;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.request.cartorder.Cartitem;
import com.checkout.management.model.request.cartorder.Data;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.GatewayData;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.cartitem.CartItemResponse;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.model.response.order.Createorder;
import com.checkout.management.model.response.placeorder.PlaceOderResponse;
import com.checkout.management.model.response.userdetails.UserDetailsResponse;

public class JUnitObjectPaymentServiceImpl extends JUnitObjectCheckoutControllerObj {

	/**
	 * return payment mode model
	 * 
	 * @return {@link PaymentModeModel}
	 */
	public PaymentModeModel getPaymentModeObj() {
		PaymentModeModel paymentModeModel = new PaymentModeModel();
		paymentModeModel.setNetBanking(AppConstant.NETBANKING);

		return paymentModeModel;
	}

	/**
	 * In this method set the value in gateway object and return success value
	 * 
	 * @param orderResponse
	 * @return {@link GatewayData}
	 */
	public GatewayData paymentSuccessObj() {
		GatewayData gatewayData = new GatewayData();
		gatewayData.setPaymenturl("https://beemaauction.com/newdemo/rest/api.php?paymentsuccess");
		gatewayData.setUserid("1");
		gatewayData.setOrderid("1");
		gatewayData.setCurrency("INR");
		gatewayData.setAmount("200");
		gatewayData.setCustomername("HCL");
		gatewayData.setAddress("Lucknow");
		gatewayData.setPaymentStatus(AppConstant.SUCCESS);
		gatewayData.setResponseCode(200);
		gatewayData.setPaymentmode(AppConstant.NETBANKING);
		return gatewayData;
	}

	/**
	 * In this method set the value in gateway object and return fail value
	 * 
	 * @return {@link GatewayData}
	 */
	public GatewayData paymentFailureObj() {
		GatewayData gatewayData = new GatewayData();
		gatewayData.setPaymenturl("https://beemaauction.com/newdemo/rest/api.php?paymentfail");
		gatewayData.setUserid("1");
		gatewayData.setOrderid("1");
		gatewayData.setCurrency("INR");
		gatewayData.setAmount("200");
		gatewayData.setCustomername("HCL");
		gatewayData.setAddress("Lucknow");
		gatewayData.setPaymentStatus(AppConstant.FAIL);
		gatewayData.setResponseCode(200);
		gatewayData.setPaymentmode(AppConstant.NETBANKING);
		return gatewayData;
	}

	/**
	 * This method return create order success response
	 * 
	 * @return {@link CreateOrderResponse}
	 */
	public CreateOrderResponse fetchOrderDetailsSuccessObj() {
		CreateOrderResponse orderResponse = new CreateOrderResponse();
		orderResponse.setStatus(true);
		orderResponse.setMessage(AppConstant.CREAT_ODER_SUCCESS);
		orderResponse.setErrorCode(0);
		Createorder createorder = new Createorder();
		createorder.setUserid("1");
		createorder.setOrderid("1");
		createorder.setCurrency("INR");
		createorder.setAmount("200");
		createorder.setCustomername("HCL");
		createorder.setPaymentmode(AppConstant.NETBANKING);
		orderResponse.setCreateorder(createorder);
		return orderResponse;
	}

	/**
	 * This method return create order fail response
	 * 
	 * @return {@link CreateOrderResponse}
	 */
	public CreateOrderResponse fetchOrderDetailsFailureObj() {
		CreateOrderResponse orderResponse = new CreateOrderResponse();
		orderResponse.setStatus(false);
		orderResponse.setMessage(AppConstant.CREAT_ODER_FAIL);
		orderResponse.setErrorCode(0);
		Createorder createorder = new Createorder();
		createorder.setUserid("1");
		createorder.setOrderid("1");
		createorder.setCurrency("INR");
		createorder.setAmount("200");
		createorder.setCustomername("HCL");
		createorder.setPaymentmode(AppConstant.NETBANKING);
		orderResponse.setCreateorder(createorder);
		return orderResponse;
	}

	/**
	 * this method use to create object of success shipment
	 * 
	 * @return {@link ShipmentRequest}
	 */
	public ShipmentRequest createShipmentTestSuccessObj() {
		GatewayData gatewayData = paymentSuccessObj();
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
	 * this method use to create object of fail shipment
	 * 
	 * @return {@link ShipmentRequest}
	 */
	public ShipmentRequest createShipmentTestFailObj() {
		GatewayData gatewayData = paymentFailureObj();
		ShipmentRequest shipmentRequestObj = new ShipmentRequest();
		shipmentRequestObj.setUserid(null);
		shipmentRequestObj.setOrderid(gatewayData.getOrderid());
		shipmentRequestObj.setCurrency(gatewayData.getCurrency());
		shipmentRequestObj.setAmount(gatewayData.getAmount());
		shipmentRequestObj.setPaymentmode(gatewayData.getPaymentmode());
		shipmentRequestObj.setCustomername(gatewayData.getCustomername());
		shipmentRequestObj.setAddress(gatewayData.getAddress());
		return shipmentRequestObj;
	}

	/**
	 * This method use to create shipment object success.
	 * 
	 * @return {@link CommonResponseModel}
	 */
	public CommonResponseModel commonResponseModelObjForSuccess() {

		ShipmentRequest createShipmentObj = createShipmentTestSuccessObj();
		CommonResponseModel commonResponseModel = new CommonResponseModel();
		commonResponseModel.setMessage(AppConstant.CREAT_SHIPMENT_SUCCESS);
		commonResponseModel.setStatusCode(0);
		commonResponseModel.setStatus(true);

		return commonResponseModel;

	}

	/**
	 * This method use to create shipment object fail.
	 * 
	 * @return {@link CommonResponseModel}
	 */
	public CommonResponseModel commonResponseModelObjForFail() {

		ShipmentRequest createShipmentObj = createShipmentTestSuccessObj();
		CommonResponseModel commonResponseModel = new CommonResponseModel();
		commonResponseModel.setMessage(AppConstant.FAIL);
		commonResponseModel.setStatusCode(0);
		commonResponseModel.setStatus(false);
		return commonResponseModel;

	}

	public Payment paymentEntityObj() {
		GatewayData gatewayData = paymentSuccessObj();
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
		return paymentEntity;
	}

	public CartOrderRequest cartOrderRequest() {
		CartOrderRequest cartOrderRequest = new CartOrderRequest();
		Data data = new Data();
		Address address = new Address();
		address.setContact(123456456);
		data.setAddress(address);
		Payment payment = new Payment();
		payment.setOrderid("1");
		List<Cartitem> cartitemList = new ArrayList<>();
		Cartitem cartItem = new Cartitem();
		cartItem.setDescription("Mobile");
		cartItem.setProductid("101");
		cartItem.setPrice("2000");
		cartItem.setProductname("Redmi");
		cartitemList.add(cartItem);
		data.setCartitem(cartitemList);

		cartOrderRequest.setData(data);

		return cartOrderRequest;

	}
	
	public CartItemResponse cartItemResponse(){
		CartItemResponse cartItemResponse = new CartItemResponse();
		cartItemResponse.setMessage("Place order successfully");
		cartItemResponse.setCode(200);
		cartItemResponse.setStatus(true);
		return cartItemResponse;
	}
	
	
}
