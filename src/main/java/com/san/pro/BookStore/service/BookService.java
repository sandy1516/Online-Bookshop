package com.san.pro.BookStore.service;

import com.google.inject.Inject;
import com.san.pro.BookStore.dao.BookDAO;
import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.exceptions.ErrorCodes;
import com.san.pro.BookStore.model.Book;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        Book book = bookDAO.read(id);
        if(!Objects.equals(null, book)) {
            return book;
        } else {
            throw new ApiException(new NoSuchElementException(" Book not found with the given id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public Long create(Book book) {
        Long id = bookDAO.create(book);
        book.setId(id);
        return id;
    }

    public List<Book> getAll() {
        List<Book> bookList = bookDAO.findAll();
        if(!Objects.equals(null,bookList)) {
            return bookList;
        }
            throw new ApiException(new NoSuchElementException(" No Book found !!!"), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
    }

    public Book getByISBN(String isbn) {
        Book book = bookDAO.findByISBN(isbn);
        if(!Objects.equals(null, book)) {
            return book;
        } else {
            throw new ApiException(new NoSuchElementException(" Book not found with the given ISBN "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public Book update(Long id, Book model) {
        Book book = bookDAO.read(id);
        if(!Objects.equals(null, book)) {
            book.merge(model);
            bookDAO.update(book);
            return book;
        } else {
            throw new ApiException(new NoSuchElementException(" Book not found with the given id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public void delete(Long id) {
        bookDAO.delete(id);
    }
}
