package com.san.pro.BookStore.model;

/**
 * Created by Administrator on 21-10-2015.
 */
public class Book extends Model {
    private String bookName;
    private String author;
    private String publication;
    private String edition;
    private String cost;
    private String selectedNumberOfBook;

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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSelectedNumberOfBook() {
        return selectedNumberOfBook;
    }

    public void setSelectedNumberOfBook(String selectedNumberOfBook) {
        this.selectedNumberOfBook = selectedNumberOfBook;
    }
}
