package com.san.pro.BookStore.dao;

import com.san.pro.BookStore.mapper.OrderMapper;
import com.san.pro.BookStore.model.Order;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.TransactionCallback;
import org.skife.jdbi.v2.TransactionStatus;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by sandeepkumar.s on 12/23/2015.
 */
public class OrderDAO implements OrderSQL {
    private final DBI dbi;

    public DBI getDBI() {
        return this.dbi;
    }

    @Inject
    public OrderDAO(DBI dbi) {
        this.dbi = dbi;
    }

    @Override
    public Long create(final Order order) {
        return getDBI().inTransaction(new TransactionCallback<Long>() {
            @Override
            public Long inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                OrderSQL daoModel = handle.attach(OrderSQL.class);
                return daoModel.create(order);
            }
        });
    }

    @Override
    public long update(final Order order) {
        return getDBI().inTransaction(new TransactionCallback<Long>() {
            @Override
            public Long inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                OrderSQL daoModel = handle.attach(OrderSQL.class);
                return daoModel.update(order);
            }
        });
    }

    @Override
    public Order read(final Long id) {
        return getDBI().inTransaction(new TransactionCallback<Order>() {
            @Override
            public Order inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                OrderSQL daoModel = handle.attach(OrderSQL.class);
                return daoModel.read(id);
            }
        });
    }

    @Override
    public List<Order> findAll() {
        return getDBI().inTransaction(new TransactionCallback<List<Order>>() {
            @Override
            public List<Order> inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                handle.registerMapper(new OrderMapper());
                OrderSQL daoModel = handle.attach(OrderSQL.class);
                return daoModel.findAll();
            }
        });
    }

    @Override
    public void delete(final long id) {
        getDBI().inTransaction(new TransactionCallback<Void>() {
            @Override
            public Void inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                OrderSQL daoModel = handle.attach(OrderSQL.class);
                daoModel.delete(id);
                return null;
            }
        });
    }
}

