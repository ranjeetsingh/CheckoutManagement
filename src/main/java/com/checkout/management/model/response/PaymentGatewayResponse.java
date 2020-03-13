
package com.checkout.management.model.response;

public class PaymentGatewayResponse {

	private Boolean status;
	private String message;
	private Integer code;
	private GatewayData gatewayData;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public GatewayData getGatewayData() {
		return gatewayData;
	}

	public void setGatewayData(GatewayData gatewayData) {
		this.gatewayData = gatewayData;
	}

}
