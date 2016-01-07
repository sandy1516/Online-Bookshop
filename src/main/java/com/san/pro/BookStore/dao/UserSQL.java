package com.san.pro.BookStore.dao;

import com.san.pro.BookStore.mapper.UserMapper;
import com.san.pro.BookStore.model.User;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandeepkumar.s on 10/28/2015.
 */
@RegisterMapper(UserMapper.class)
public interface UserSQL {
    @SqlUpdate("INSERT into users (first_name, last_name, email_id, mobile, user_name, password, role, created_at, created_by, updated_at, updated_by) values (:firstName, :lastName, :emailId, :mobile, :userName, :password, :role, :createdAt, :createdBy, :updatedAt, :updatedBy)")
    @GetGeneratedKeys
    Long create(@BindBean User user);

    @SqlUpdate("UPDATE users set first_name = :firstName, last_name = :lastName, email_id = :emailId, mobile = :mobile, user_name = :userName, password = :password, role = :role, updated_at = :updatedAt, updated_by = :updatedBy WHERE id = :id")
    long update(@BindBean User user);

    @SqlQuery("SELECT * from users where id = :id")
    User read(@Bind("id") Long id);

    @SqlQuery("SELECT * from users where email_id = :emailId")
    User findByEmail(@Bind("emailId") String emailId);

    @SqlQuery("SELECT * FROM users")
    List<User> findAll();

    @SqlUpdate("DELETE from users where id = :id")
    void delete(@Bind("id") long id);

}
