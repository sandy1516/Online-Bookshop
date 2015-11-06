package com.san.pro.BookStore.service;

import com.google.inject.Inject;
import com.san.pro.BookStore.dao.UserDAO;
import com.san.pro.BookStore.model.User;

import java.util.List;

/**
 * Created by sandeepkumar.s on 10/28/2015.
 */
public class UserService {

    private UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User get(long id) {
        return userDAO.read(id);
    }

    public Long create(User user) {
        Long id = userDAO.create(user);
        user.setId(id);
        return id;
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }
}
