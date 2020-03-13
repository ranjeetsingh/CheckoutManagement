
package com.checkout.management.model.response.order;

public class CreateOrderResponse {

    private Boolean status;
    private String message;
    private Createorder createorder;
    private Integer errorCode;

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

    public Createorder getCreateorder() {
        return createorder;
    }

    public void setCreateorder(Createorder createorder) {
        this.createorder = createorder;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

}
