
package com.checkout.management.model.response.inventory;

import java.util.List;

public class InventoryResponse {

    private List<DataArray> dataArray = null;
    private String message;
    private Boolean status;
    private Integer statusCode;

    public List<DataArray> getDataArray() {
        return dataArray;
    }

    public void setDataArray(List<DataArray> dataArray) {
        this.dataArray = dataArray;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
