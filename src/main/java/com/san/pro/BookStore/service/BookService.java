package com.san.pro.BookStore.service;

import com.google.inject.Inject;
import com.san.pro.BookStore.dao.BookDAO;
import com.san.pro.BookStore.model.Book;

import java.util.List;

/**
 * Created by sandeepkumar.s on 11/18/2015.
 */
public class BookService {

    private BookDAO bookDAO;

    @Inject
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public Book getById(long id) {
        return bookDAO.read(id);
    }

    public Long create(Book book) {
        Long id = bookDAO.create(book);
        book.setId(id);
        return id;
    }

    public List<Book> getAll() {
        return bookDAO.findAll();
    }

    public Book update(Long id, Book model) {
        Book book = bookDAO.read(id);
        book.merge(model);
        bookDAO.update(book);
        return book;
    }

    public void delete(Long id) {
        bookDAO.delete(id);
    }
}
