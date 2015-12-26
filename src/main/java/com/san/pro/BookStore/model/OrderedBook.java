package com.san.pro.BookStore.model;

import javax.validation.constraints.NotNull;

/**
 * Created by sandeepkumar.s on 12/22/2015.
 */
public class OrderedBook extends Model{
    @NotNull
    private String orderId;
    @NotNull
    private String isbn;
    @NotNull
    private String bookName;
    @NotNull
    private String author;
    @NotNull
    private String publication;
    @NotNull
    private String edition;
    @NotNull
    private Float cost;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
