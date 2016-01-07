package com.san.pro.BookStore.model;

/**
 * Created by Administrator on 21-10-2015.
 */
public enum OrderStatus {

    confirmed("confirmed"),
    processing("processing"),
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

    public static OrderStatus fromString(String status) {
        if(status != null) {
            for(OrderStatus orderStatus: OrderStatus.values()) {
                if(orderStatus.equals(orderStatus.getOrderStatus())) {
                    return orderStatus;
                }
            }
        }
        return null;
    }
}
