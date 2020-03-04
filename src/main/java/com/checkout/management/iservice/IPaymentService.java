package com.checkout.management.iservice;

public interface IPaymentService {
	//parameter will change latter
	//return type will change as per requirement
	//only define method
	void selectPaymentMode();
	void paymentWithNetBanking(String netBanking, String amt);
	void paymentWithCardDetails(String cardDetails, String amt);
}
