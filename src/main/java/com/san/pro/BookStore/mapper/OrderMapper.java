package com.san.pro.BookStore.mapper;

import com.san.pro.BookStore.model.Order;
import com.san.pro.BookStore.model.OrderStatus;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandeepkumar.s on 11/18/2015.
 */
public class OrderMapper implements ResultSetMapper<Order> {
    public Order map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Order order = new Order();
        order.setId(resolveLong(resultSet, "id"));



        order.setAddress(resultSet.getString("address"));
        order.setStreet(resultSet.getString("street"));
        order.setCity(resultSet.getString("city"));
        order.setState(resultSet.getString("state"));
        order.setCountry(resultSet.getString("country"));
        order.setZip(resultSet.getString("zip"));
        order.setSelectedNumberOfBooks(resultSet.getInt("selected_no_of_books"));
        order.setCardNo(resultSet.getString("card_no"));
        order.setCardExpiry(resultSet.getString("card_expiry"));
        order.setTotalAmount(resultSet.getFloat("total_amount"));
        order.setOrderDate(resultSet.getString("order_date"));
//        order.setOrderItemList(resolveLong(resultSet, "order_item_list"));
        order.setOrderStatus(getOrderStatusFromString(resultSet.getString("order_status")));
        order.setCreatedAt(resolveLong(resultSet, "created_at"));
        order.setCreatedBy(resolveLong(resultSet, "created_by"));
        order.setUpdatedAt(resolveLong(resultSet, "updated_at"));
        order.setUpdatedBy(resolveLong(resultSet, "updated_by"));
        return order;
    }

    private Long resolveLong(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getObject(columnName) != null ? resultSet.getLong(columnName) : null;
    }

    private OrderStatus getOrderStatusFromString(String orderStatus) {
        return OrderStatus.fromString(orderStatus);
    }
}
