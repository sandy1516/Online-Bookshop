package com.san.pro.BookStore.model;

/**
 * Created by Administrator on 21-10-2015.
 */
public enum OrderStatus {

    confirmed("confirmed"),
    process("process"),
    shipment("shipment"),
    reached("reached"),
    delivered("delivered"),
    cancelled("cancelled");

    String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
