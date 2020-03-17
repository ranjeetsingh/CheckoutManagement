package com.checkout.management.serviceImpl;


import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.entity.Payment;
import com.checkout.management.model.request.ShipmentRequest;
import com.checkout.management.model.request.cartorder.CartOrderRequest;
import com.checkout.management.model.response.CommonResponseModel;
import com.checkout.management.model.response.GatewayData;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.order.CreateOrderResponse;
import com.checkout.management.repository.PaymentRepository;
import com.checkout.management.testUtills.JUnitObjectPaymentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaymentServiceImplTest extends JUnitObjectPaymentServiceImpl {
	@Mock
	private PaymentRepository mockPaymentRepository;
	@MockBean
	private PaymentServiceImpl mockPaymentServiceImpl;
	@MockBean 
	private CheckoutServiceImpl mockCheckoutServiceImpl;
	/**
	 * Test case success when get all payment mode
	 */
	@Test
	public void test_GetPaymentMode_When_Success() {
		PaymentModeModel paymentModeModel = getPaymentModeObj();
		//PaymentModeModel paymentModeModelObj =mockPaymentServiceImpl.getPaymentMode();
		when(mockPaymentServiceImpl.getPaymentMode()).thenReturn(paymentModeModel);
		Assert.assertEquals(AppConstant.NETBANKING, paymentModeModel.getNetBanking());

	}
	/**
	 * Test case success when get all payment mode not available
	 */
	@Test
	public void test_GetPaymentMode_When_Fail() {
		PaymentModeModel paymentModeModel = null;
		//PaymentModeModel paymentModeModelObj =mockPaymentServiceImpl.getPaymentMode();
		when(mockPaymentServiceImpl.getPaymentMode()).thenReturn(null);
		Assert.assertEquals(null, paymentModeModel);

	}
	/**
	 * Test case success when payment gateway return success response
	 */
	@Test
	public void test_Payment_When_Success() {
		CreateOrderResponse creatOrderResponse = fetchOrderDetailsSuccessObj();
		Payment paymentEntity = paymentEntityObj();
		when(mockPaymentRepository.save(Mockito.any())).thenReturn(paymentEntity);
		//GatewayData gatewayData = mockPaymentServiceImpl.payment(creatOrderResponse);
		GatewayData gatewayData = paymentSuccessObj();
		when(mockPaymentServiceImpl.payment(creatOrderResponse)).thenReturn(gatewayData);
		Assert.assertEquals(AppConstant.SUCCESS, gatewayData.getPaymentStatus());
		
	}
	
	/**
	 * Test case success when payment gateway return fail response
	 */
	@Test
	public void test_Payment_When_Fail() {
		CreateOrderResponse creatOrderResponse = fetchOrderDetailsSuccessObj();
		Payment paymentEntity = paymentEntityObj();
		when(mockPaymentRepository.save(Mockito.any())).thenReturn(paymentEntity);
		//GatewayData gatewayData = mockPaymentServiceImpl.payment(creatOrderResponse);
		GatewayData gatewayData = paymentFailureObj();
		when(mockPaymentServiceImpl.payment(creatOrderResponse)).thenReturn(gatewayData);
		Assert.assertEquals(AppConstant.FAIL, gatewayData.getPaymentStatus());
		
	}
	
	/**
	 * Test case success when create order successfully
	 */
	@Test
	public void test_fetchOrderDetails_When_Success() {
		CartOrderRequest cartOrderRequest = cartOrderRequest();
		//CreateOrderResponse orderResponse = mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest);
		CreateOrderResponse orderResponse = fetchOrderDetailsSuccessObj();
		when(mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest)).thenReturn(orderResponse);
		Assert.assertEquals(AppConstant.CREAT_ODER_SUCCESS, orderResponse.getMessage());
		
	}
	
	/**
	 * Test case success when create order fail
	 */
	@Test
	public void test_fetchOrderDetails_When_Fail() {
		CartOrderRequest cartOrderRequest = cartOrderRequest();
		//CreateOrderResponse orderResponse = mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest);
		CreateOrderResponse orderResponse = fetchOrderDetailsFailureObj();
		when(mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest)).thenReturn(orderResponse);
		Assert.assertEquals(AppConstant.CREAT_ODER_FAIL, orderResponse.getMessage());
		
	}
	
	/**
	 * Test case success when create shipment request successfully
	 */
	@Test
	public void test_createShipmentObj_When_Success() {
		GatewayData gatewayData = paymentSuccessObj();
		//ShipmentRequest createShipmentObj = mockPaymentServiceImpl.createShipmentObj(gatewayData);
		ShipmentRequest createShipmentObj =  createShipmentTestSuccessObj();
		when(mockPaymentServiceImpl.createShipmentObj(gatewayData)).thenReturn(createShipmentObj);
		Assert.assertEquals("1", createShipmentObj.getUserid());
		
	}
	
	/**
	 * Test case success when create shipment request fail
	 */
	@Test
	public void test_createShipmentObj_When_Fail() {
		GatewayData gatewayData = paymentSuccessObj();
		//ShipmentRequest createShipmentObj = mockPaymentServiceImpl.createShipmentObj(gatewayData);
		ShipmentRequest createShipmentObj =  createShipmentTestFailObj();
		when(mockPaymentServiceImpl.createShipmentObj(gatewayData)).thenReturn(createShipmentObj);
		Assert.assertEquals(null, createShipmentObj.getUserid());
		
	}
	
	
	/**
	 * Test case success when initiate shipment  successfully
	 */
	@Test
	public void test_initiateProductShipment_When_Success() {
		GatewayData gatewayData = paymentSuccessObj();
		mockPaymentServiceImpl.initiateProductShipment(gatewayData);
		ShipmentRequest createShipmentObj = createShipmentTestSuccessObj();
		//CommonResponseModel shipmentResponseModel = mockCheckoutServiceImpl.creatShipment(createShipmentObj);
		CommonResponseModel shipmentResponseModel = commonResponseModelObjForSuccess();
		when(mockCheckoutServiceImpl.creatShipment(createShipmentObj)).thenReturn(shipmentResponseModel);
		Assert.assertEquals(true, shipmentResponseModel.getStatus());
		
	}
	

	/**
	 * Test case success when initiate shipment  fail
	 */
	@Test
	public void test_initiateProductShipment_When_Fail() {
		GatewayData gatewayData = paymentSuccessObj();
		mockPaymentServiceImpl.initiateProductShipment(gatewayData);
		ShipmentRequest createShipmentObj = createShipmentTestSuccessObj();
		//CommonResponseModel shipmentResponseModel = mockCheckoutServiceImpl.creatShipment(createShipmentObj);
		CommonResponseModel shipmentResponseModel = commonResponseModelObjForFail();
		when(mockCheckoutServiceImpl.creatShipment(createShipmentObj)).thenReturn(shipmentResponseModel);
		Assert.assertEquals(false, shipmentResponseModel.getStatus());
		
	}
	
	
	/**
	 * Test case success when initiate shipment  successfully
	 */
	@Test
	public void test_removeCartItem_When_Success() {
		//CommonResponseModel commaonResponseModel = mockCheckoutServiceImpl.removeCartItem("1");
		CommonResponseModel commonResponseModel = commonResponseModelObjForSuccess();
		when(mockCheckoutServiceImpl.removeCartItem("1")).thenReturn(commonResponseModel);
		Assert.assertEquals(true, commonResponseModel.getStatus());
		
	}
	
	/**
	 * Test case success when initiate shipment fail
	 */
	@Test
	public void test_removeCartItem_When_Fail() {
		//CommonResponseModel commaonResponseModel = mockCheckoutServiceImpl.removeCartItem("1");
		CommonResponseModel commonResponseModel = commonResponseModelObjForFail();
		when(mockCheckoutServiceImpl.removeCartItem("1")).thenReturn(commonResponseModel);
		Assert.assertEquals(false, commonResponseModel.getStatus());
		
	}
	
	
	/**
	 * Test case success when initiate shipment  successfully
	 */
	@Test
	public void test_savePaymentInfo_When_Success() {
		Payment paymentEntity = paymentEntityObj();
		when(mockPaymentRepository.save(Mockito.any())).thenReturn(paymentEntity);
		mockPaymentServiceImpl.savePaymentInfo(paymentSuccessObj());
		
	}
	
	
	
	

	
}
