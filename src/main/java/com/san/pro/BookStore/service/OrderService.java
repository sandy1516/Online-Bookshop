package com.san.pro.BookStore.service;

import com.google.inject.Inject;
import com.san.pro.BookStore.dao.OrderDAO;
import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.exceptions.ErrorCodes;
import com.san.pro.BookStore.model.Order;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by sandeepkumar.s on 12/24/2015.
 */
public class OrderService {

    private OrderDAO orderDAO;

    @Inject
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public Long create(Order order) {
        Long id = orderDAO.create(order);
        order.setId(id);
        return id;
    }

    public Order getById(long id) {
        Order order = orderDAO.read(id);
        if(!Objects.equals(null, order)) {
            return order;
        } else {
            throw new ApiException(new NoSuchElementException(" Order not found with the given id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
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
