package com.san.pro.BookStore.mapper;

import com.san.pro.BookStore.model.Book;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandeepkumar.s on 11/18/2015.
 */
public class BookMapper implements ResultSetMapper<Book> {
    public Book map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Book book = new Book();
        book.setId(resolveLong(resultSet, "id"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setBookName(resultSet.getString("book_name"));
        book.setAuthor(resultSet.getString("author"));
        book.setPublication(resultSet.getString("publication"));
        book.setEdition(resultSet.getString("edition"));
        book.setCost(resultSet.getFloat("cost"));
        book.setCreatedAt(resolveLong(resultSet, "created_at"));
        book.setCreatedBy(resolveLong(resultSet, "created_by"));
        book.setUpdatedAt(resolveLong(resultSet, "updated_at"));
        book.setUpdatedBy(resolveLong(resultSet, "updated_by"));
        return book;
    }

    private Long resolveLong(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getObject(columnName) != null ? resultSet.getLong(columnName) : null;
    }
}
