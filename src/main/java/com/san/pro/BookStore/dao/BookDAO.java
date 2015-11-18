package com.san.pro.BookStore.dao;

import com.san.pro.BookStore.mapper.BookMapper;
import com.san.pro.BookStore.model.Book;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.TransactionCallback;
import org.skife.jdbi.v2.TransactionStatus;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by sandeepkumar.s on 11/18/2015.
 */
public class BookDAO implements BookSQL {

    private final DBI dbi;

    public DBI getDBI() {
        return this.dbi;
    }

    @Inject
    public BookDAO(DBI dbi) {
        this.dbi = dbi;
    }

    @Override
    public Long create(final Book book) {
        return getDBI().inTransaction(new TransactionCallback<Long>() {
            @Override
            public Long inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                BookSQL daoModel = handle.attach(BookSQL.class);
                return daoModel.create(book);
            }
        });
    }

    @Override
    public long update(final Book book) {
        return getDBI().inTransaction(new TransactionCallback<Long>() {
            @Override
            public Long inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                BookSQL daoModel = handle.attach(BookSQL.class);
                return daoModel.update(book);
            }
        });
    }

    @Override
    public Book read(final Long id) {
        return getDBI().inTransaction(new TransactionCallback<Book>() {
            @Override
            public Book inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                BookSQL daoModel = handle.attach(BookSQL.class);
                return daoModel.read(id);
            }
        });
    }

    @Override
    public List<Book> findAll() {
        return getDBI().inTransaction(new TransactionCallback<List<Book>>() {
            @Override
            public List<Book> inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                handle.registerMapper(new BookMapper());
                BookSQL daoModel = handle.attach(BookSQL.class);
                return daoModel.findAll();
            }
        });
    }

    @Override
    public void delete(final long id) {
        getDBI().inTransaction(new TransactionCallback<Void>() {
            @Override
            public Void inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                BookSQL daoModel = handle.attach(BookSQL.class);
                daoModel.delete(id);
                return null;
            }
        });
    }

    @Override
    public Book findByISBN(final String isbn) {
        return getDBI().inTransaction(new TransactionCallback<Book>() {
            @Override
            public Book inTransaction(Handle handle, TransactionStatus transactionStatus) throws Exception {
                BookSQL daoModel = handle.attach(BookSQL.class);
                return daoModel.findByISBN(isbn);
            }
        });
    }
}
