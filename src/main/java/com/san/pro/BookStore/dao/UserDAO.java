package com.san.pro.BookStore.dao;

import com.san.pro.BookStore.mapper.UserMapper;
import com.san.pro.BookStore.model.User;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.TransactionCallback;
import org.skife.jdbi.v2.TransactionStatus;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by sandeepkumar.s on 10/28/2015.
 */
public class UserDAO implements UserSQL {

    private final DBI dbi;

    public DBI getDBI() {
        return this.dbi;
    }

    @Inject
    public UserDAO(DBI dbi) {
        this.dbi = dbi;
    }

    @Override
    public Long create(final User user) {
        return getDBI().inTransaction(new TransactionCallback<Long>() {
            @Override
            public Long inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                UserSQL daoModel = handle.attach(UserSQL.class);
                return daoModel.create(user);
            }
        });
    }

    @Override
    public long update(final User user) {
        return getDBI().inTransaction(new TransactionCallback<Long>() {
            @Override
            public Long inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                UserSQL daoModel = handle.attach(UserSQL.class);
                return daoModel.update(user);
            }
        });
    }

    @Override
    public User read(final Long id) {
        return getDBI().inTransaction(new TransactionCallback<User>() {
            @Override
            public User inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                UserSQL daoModel = handle.attach(UserSQL.class);
                return daoModel.read(id);
            }
        });
    }

    @Override
    public List<User> findAll() {
        return getDBI().inTransaction(new TransactionCallback<List<User>>() {
            @Override
            public List<User> inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                handle.registerMapper(new UserMapper());
                UserSQL daoModel = handle.attach(UserSQL.class);
                return daoModel.findAll();
            }
        });
    }
}
