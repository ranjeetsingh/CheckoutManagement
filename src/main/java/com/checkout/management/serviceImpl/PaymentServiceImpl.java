package com.checkout.management.serviceImpl;

import org.springframework.stereotype.Service;

import com.checkout.management.iservice.IPaymentService;
import com.checkout.management.model.response.PaymentModeModel;

/**
 * All payment related method
 * 
 * @author RanjeetSi
 *
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

	/**
	 * This method will return the list of payment mode
	 * 
	 * @return PaymentModeModel
	 */
	@Override
	public PaymentModeModel getPaymentMode() {
		PaymentModeModel paymentModeModel = new PaymentModeModel();
		paymentModeModel.setNetBanking("NetBanking");
		paymentModeModel.setCard("credit/debit/ATM card");
		return paymentModeModel;
	}

}
