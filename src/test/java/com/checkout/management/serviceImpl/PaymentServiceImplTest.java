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
class PaymentServiceImplTest extends JUnitObjectPaymentServiceImpl{
	@Mock
	private PaymentRepository mockPaymentRepository;
	@Autowired
	private PaymentServiceImpl mockPaymentServiceImpl;
	@Autowired 
	private CheckoutServiceImpl mockCheckoutServiceImpl;
	@Test
	void testGetPaymentMode() {
		PaymentModeModel paymentModeModel = new PaymentModeModel();
		paymentModeModel.setNetBanking(AppConstant.NETBANKING);
		paymentModeModel.setCard(AppConstant.ATM_CARD_TYPE);
		PaymentModeModel paymentModeModel1 = mockPaymentServiceImpl.getPaymentMode();
		Assert.assertEquals(AppConstant.NETBANKING, paymentModeModel1.getNetBanking());
	}

	
	/**
	 * Test case success when payment gateway return success response
	 */
	@Test
	public void test_Payment_When_Success() {
		CreateOrderResponse creatOrderResponse = fetchOrderDetailsSuccessObj();
		Payment paymentEntity = paymentEntityObj();
		when(mockPaymentRepository.save(Mockito.any())).thenReturn(paymentEntity);
		GatewayData gatewayData = mockPaymentServiceImpl.payment(creatOrderResponse);
		Assert.assertEquals(AppConstant.SUCCESS, gatewayData.getPaymentStatus());
		
	}
	
	/**
	 * Test case success when create order successfully
	 */
	@Test
	public void test_fetchOrderDetails_When_Success() {
		CartOrderRequest cartOrderRequest = cartOrderRequest();
		CreateOrderResponse orderResponse = mockPaymentServiceImpl.fetchOrderDetails(cartOrderRequest);
		Assert.assertEquals(AppConstant.CREAT_ODER_SUCCESS, orderResponse.getMessage());
		
	}
	
	
	
	/**
	 * Test case success when create shipment request successfully
	 */
	@Test
	public void test_createShipmentObj_When_Success() {
		GatewayData gatewayData = paymentSuccessObj();
		ShipmentRequest createShipmentObj = mockPaymentServiceImpl.createShipmentObj(gatewayData);
		Assert.assertEquals("1", createShipmentObj.getUserid());
		
	}
	
	
	
	
	/**
	 * Test case success when initiate shipment  successfully
	 */
	@Test
	public void test_initiateProductShipment_When_Success() {
		GatewayData gatewayData = paymentSuccessObj();
		mockPaymentServiceImpl.initiateProductShipment(gatewayData);
		ShipmentRequest createShipmentObj = createShipmentTestSuccessObj();
		CommonResponseModel shipmentResponseModel = mockCheckoutServiceImpl.creatShipment(createShipmentObj);
		Assert.assertEquals(true, shipmentResponseModel.getStatus());
		
	}
	

	
	
	
	/**
	 * Test case success when initiate shipment  successfully
	 */
	@Test
	public void testRemoveCartItem() {
		CommonResponseModel cartItemResponseModel = commonResponseModelObjForSuccess();
		mockPaymentServiceImpl.removeCartItem("1");
		Assert.assertEquals(true, cartItemResponseModel.getStatus());
		
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
