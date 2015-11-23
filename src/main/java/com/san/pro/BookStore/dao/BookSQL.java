package com.san.pro.BookStore.dao;

import com.san.pro.BookStore.mapper.BookMapper;
import com.san.pro.BookStore.model.Book;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandeepkumar.s on 11/18/2015.
 */
@RegisterMapper(BookMapper.class)
public interface BookSQL {

    @SqlUpdate("INSERT into books (isbn, book_name, author, publication, edition, cost, selected_no_of_book, created_at, created_by, updated_at, updated_by) values (:isbn, :bookName, :author, :publication, :edition, :cost, :selectedNumberOfBook, :createdAt, :createdBy, :updatedAt, :updatedBy)")
    @GetGeneratedKeys
    Long create(@BindBean Book book);

    @SqlUpdate("UPDATE books set isbn = :isbn, book_name = :bookName, author = :author, publication = :publication, edition = :edition, cost = :cost, selected_no_of_book = :selectedNumberOfBook, created_at = :createdAt, created_by = :createdBy, updated_at = :updatedAt, updated_by = :updatedBy WHERE id = :id")
    long update(@BindBean Book book);

    @SqlQuery("SELECT * from books where id = :id")
    Book read(@Bind("id") Long id);

    @SqlQuery("SELECT * from books where isbn = :isbn")
    Book findByISBN(@Bind("isbn") String isbn);

    @SqlQuery("SELECT * FROM books")
    List<Book> findAll();

    @SqlUpdate("DELETE from books where id = :id")
    void delete(@Bind("id") long id);

}