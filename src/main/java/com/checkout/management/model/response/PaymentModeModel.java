package com.checkout.management.model.response;

/**
 * Model for select payment mode
 * 
 * @author RanjeetSi
 *
 */
public class PaymentModeModel {
	private String NetBanking;
	private String card;

	public String getNetBanking() {
		return NetBanking;
	}

	public void setNetBanking(String netBanking) {
		NetBanking = netBanking;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

}
