package com.san.pro.BookStore.mapper;

import com.san.pro.BookStore.model.Role;
import com.san.pro.BookStore.model.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandeepkumar.s on 10/30/2015.
 */
public class UserMapper implements ResultSetMapper<User> {
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        User user = new User();
        user.setId(resolveLong(resultSet, "id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmailId(resultSet.getString("email_id"));
        user.setMobile(resolveLong(resultSet, "mobile"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(getRoleFromString(resultSet.getString("role")));
        user.setCreatedAt(resolveLong(resultSet, "created_at"));
        user.setCreatedBy(resolveLong(resultSet, "created_by"));
        user.setUpdatedAt(resolveLong(resultSet, "updated_at"));
        user.setUpdatedBy(resolveLong(resultSet, "updated_by"));
        return user;
    }

    private Long resolveLong(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getObject(columnName) != null ? resultSet.getLong(columnName) : null;
    }

    private Boolean resolveBoolean(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getObject(columnName) != null ? resultSet.getBoolean(columnName) : null;
    }

    private Role getRoleFromString(String role) {
            return Role.fromString(role);
        }
}
