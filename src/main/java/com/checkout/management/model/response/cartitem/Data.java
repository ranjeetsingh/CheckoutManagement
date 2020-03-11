
package com.checkout.management.model.response.cartitem;

import java.util.List;

import com.checkout.management.model.response.placeorder.Cartitem;

public class Data {

	private List<Cartitem> cartitem = null;

	public List<Cartitem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(List<Cartitem> cartitem) {
		this.cartitem = cartitem;
	}

}
