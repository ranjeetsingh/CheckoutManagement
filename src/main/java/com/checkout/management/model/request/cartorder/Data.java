
package com.checkout.management.model.request.cartorder;

import java.util.List;

public class Data {

    private Address address;
    private Payment payment;
    private List<Cartitem> cartitem = null;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Cartitem> getCartitem() {
        return cartitem;
    }

    public void setCartitem(List<Cartitem> cartitem) {
        this.cartitem = cartitem;
    }

}
