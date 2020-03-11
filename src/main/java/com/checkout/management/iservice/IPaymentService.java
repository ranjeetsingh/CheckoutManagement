package com.checkout.management.iservice;

import com.checkout.management.model.response.PaymentModeModel;

public interface IPaymentService {
	//parameter will change latter
	//return type will change as per requirement
	//only define method
	/**
	 * This method will return list payment mode
	 * @return PaymentModeModel
	 */
	PaymentModeModel getPaymentMode();
	//void paymentWithNetBanking(String netBanking, String amt);
	//void paymentWithCardDetails(String cardDetails, String amt);
}
