package com.san.pro.BookStore.model;

import com.google.common.base.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Administrator on 21-10-2015.
 */
public class Order extends Model{

    private Long userId;
    @Max(50)
    private String number;
    @NotNull
    private String address;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    private String zip;
    @NotNull
    private String cardNo;
    @NotNull
    private String cardExpiry;

    private int selectedNumberOfBooks;

    private float totalAmount;
    @NotNull
    private String orderDate;
    @NotNull
    private String orderItemList;

    private List<Book> orderedBooksList;

    private OrderStatus orderStatus;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getSelectedNumberOfBooks() {
        return selectedNumberOfBooks;
    }

    public void setSelectedNumberOfBooks(int selectedNumberOfBooks) {
        this.selectedNumberOfBooks = selectedNumberOfBooks;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(String orderItemList) {
        this.orderItemList = orderItemList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderedBooksList(List<Book> orderedBooksList) {
        this.orderedBooksList = orderedBooksList;
    }

    public List<Book> getOrderedBooksList() {
        return orderedBooksList;
    }

    public void merge(Order order) {
        if(!Objects.equal(null, order.getUserId())) {
            this.setUserId(order.getUserId());
        }
        if(!Objects.equal(null, order.getNumber())) {
            this.setNumber(order.getNumber());
        }
        if(!Objects.equal(null, order.getAddress())) {
            this.setAddress(order.getAddress());
        }
        if(!Objects.equal(null, order.getStreet())) {
            this.setStreet(order.getStreet());
        }
        if(!Objects.equal(null, order.getCity())) {
            this.setCity(order.getCity());
        }
        if(!Objects.equal(null, order.getState())) {
            this.setState(order.getState());
        }
        if(!Objects.equal(null, order.getCountry())) {
            this.setCountry(order.getCountry());
        }
        if(!Objects.equal(null, order.getZip())) {
            this.setZip(order.getZip());
        }
        if(!Objects.equal(null, order.getSelectedNumberOfBooks())) {
            this.setSelectedNumberOfBooks(order.getSelectedNumberOfBooks());
        }
        if(!Objects.equal(null, order.getCardNo())) {
            this.setCardNo(order.getCardNo());
        }
        if(!Objects.equal(null, order.getCardExpiry())) {
            this.setCardExpiry(order.getCardExpiry());
        }
        if(!Objects.equal(null, order.getTotalAmount())) {
            this.setTotalAmount(order.getTotalAmount());
        }
        if(!Objects.equal(null, order.getOrderDate())) {
            this.setOrderDate(order.getOrderDate());
        }
        if(!Objects.equal(null, order.getOrderItemList())) {
            this.setOrderItemList(order.getOrderItemList());
        }
        if(!Objects.equal(null, order.getOrderStatus())) {
            this.setOrderStatus(order.getOrderStatus());
        }
    }
}
