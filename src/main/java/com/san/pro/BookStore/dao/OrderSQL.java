package com.san.pro.BookStore.dao;

import com.san.pro.BookStore.mapper.OrderMapper;
import com.san.pro.BookStore.model.Order;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandeepkumar.s on 12/23/2015.
 */
@RegisterMapper(OrderMapper.class)
public interface OrderSQL {
    @SqlUpdate("INSERT into orders (user_id, number, address, street, city, state, country, zip, card_no, card_expiry, total_amount, order_date, order_status, selected_number_Of_books, order_item_list, created_at, created_by, updated_at, updated_by) values (:userId, :number, :address, :street, :city, :state, :country, :zip, :cardNo, :cardExpiry, :totalAmount, :orderDate, :orderStatus, :selectedNumberOfBooks, :orderItemList, :createdAt, :createdBy, :updatedAt, :updatedBy)")
    @GetGeneratedKeys
    Long create(@BindBean Order order);

    @SqlUpdate("UPDATE orders set user_id=:userId, number=:number,  address=:address, street=:street, city=:city, state=:state, country=:country, zip=:zip, card_no=:cardNo, card_expiry=:cardExpiry, total_amount=:totalAmount, order_date=:orderDate, order_status = :orderStatus, selected_number_Of_books =:selectedNumberOfBooks, order_item_list = :orderItemList, created_at = :createdAt, created_by = :createdBy, updated_at = :updatedAt, updated_by = :updatedBy WHERE id = :id")
    long update(@BindBean Order order);

    @SqlQuery("SELECT * from orders where id = :id")
    Order read(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM orders")
    List<Order> findAll();

    @SqlUpdate("DELETE from orders where id = :id")
    void delete(@Bind("id") long id);
}
