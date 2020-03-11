package com.checkout.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkout.management.apputil.AppConstant;
import com.checkout.management.iservice.IPaymentService;
import com.checkout.management.model.response.PaymentModeModel;
import com.checkout.management.model.response.ResponseModel;
/**
 * All payment related action method 
 * @author RanjeetSi
 *
 */
@RestController
public class PaymentController {
	@Autowired
	private IPaymentService iPaymentService;
	
	@GetMapping("/getPaymentMethod")
	public ResponseEntity<Object> PaymentMethod() {
		ResponseEntity<Object> responseEntity = null;
		PaymentModeModel paymentModeModel = iPaymentService.getPaymentMode();
		if(paymentModeModel == null) {
			responseEntity = new ResponseEntity<Object>(
					new ResponseModel(true, AppConstant.PAYMENT_IS_NOT_AVAILABLE, null, 0), HttpStatus.OK);
			return responseEntity;
		}
		responseEntity = new ResponseEntity<Object>(
				new ResponseModel(true, AppConstant.LIST_OF_PAYMENT_MODE, paymentModeModel, 0), HttpStatus.OK);

		return responseEntity;
	}
}
