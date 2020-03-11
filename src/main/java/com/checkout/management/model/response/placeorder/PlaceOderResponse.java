
package com.checkout.management.model.response.placeorder;

import java.util.List;

public class PlaceOderResponse {

    private Address address;
    private List<Cartitem> cartitem = null;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Cartitem> getCartitem() {
        return cartitem;
    }

    public void setCartitem(List<Cartitem> cartitem) {
        this.cartitem = cartitem;
    }

}
