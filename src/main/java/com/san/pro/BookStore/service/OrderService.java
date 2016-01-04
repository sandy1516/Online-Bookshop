package com.san.pro.BookStore.service;

import com.google.inject.Inject;
import com.san.pro.BookStore.dao.BookDAO;
import com.san.pro.BookStore.dao.OrderDAO;
import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.exceptions.ErrorCodes;
import com.san.pro.BookStore.model.Book;
import com.san.pro.BookStore.model.Order;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by sandeepkumar.s on 12/24/2015.
 */
public class OrderService {

    private OrderDAO orderDAO;
    private BookDAO bookDAO;

    @Inject
    public OrderService(OrderDAO orderDAO, BookDAO bookDAO) {
        this.orderDAO = orderDAO;
        this.bookDAO = bookDAO;
    }

    public Long create(Order order) {
        Long id = orderDAO.create(order);
        order.setId(id);
        return id;
    }

    public Order getById(long id) {
        Order order = orderDAO.read(id);
        List<Book> bookList = new ArrayList<Book>();
        try {
            if (!Objects.equals(null, order)) {
                for (String retval : order.getOrderItemList().split(",")) {
                    bookList.add(bookDAO.read(Long.parseLong(retval)));
                }
                order.setOrderedBooksList(bookList);
                return order;
            } else {
                throw new ApiException(new NoSuchElementException(" Order not found with the given id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
            }
        } catch (NumberFormatException nfe) {
            throw new ApiException(new NumberFormatException(" NumberFormatException: "), Response.Status.BAD_REQUEST).addError(ErrorCodes.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Order> getAll() {
        List<Order> orderList = orderDAO.findAll();
        if(!Objects.equals(null,orderList)) {
            return orderList;
        }
        throw new ApiException(new NoSuchElementException(" No Order found !!!"), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
    }


    public Order update(Long id, Order model) {
        Order order = orderDAO.read(id);
        if(!Objects.equals(null, order)) {
            order.merge(model);
            orderDAO.update(order);
            return order;
        } else {
            throw new ApiException(new NoSuchElementException(" Order not found with the given id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public void delete(Long id) {
        orderDAO.delete(id);
    }
}
