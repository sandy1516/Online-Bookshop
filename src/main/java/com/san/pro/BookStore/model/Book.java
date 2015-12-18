package com.san.pro.BookStore.model;

import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 21-10-2015.
 */
public class Book extends Model {

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

    public void merge(Book book) {

        if(!Objects.equal(null, book.getIsbn())) {
            this.setIsbn(book.getIsbn());
        }
        if(!Objects.equal(null, book.getBookName())) {
            this.setBookName(book.getBookName());
        }
        if(!Objects.equal(null, book.getAuthor())) {
            this.setAuthor(book.getAuthor());
        }
        if(!Objects.equal(null, book.getPublication())) {
            this.setPublication(book.getPublication());
        }
        if(!Objects.equal(null, book.getEdition())) {
            this.setEdition(book.getEdition());
        }
        if(!Objects.equal(null, book.getCost())) {
            this.setCost(book.getCost());
        }
    }
}
