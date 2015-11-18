package com.san.pro.BookStore.service;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.san.pro.BookStore.dao.UserDAO;
import com.san.pro.BookStore.model.User;
import com.san.pro.BookStore.util.Cryptography;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by sandeepkumar.s on 10/28/2015.
 */
public class UserService {

    private UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getById(long id) {
        return userDAO.read(id);
    }

    public User getByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public Long create(User user) {
        user.setPassword(Cryptography.encryptPassword(user.getPassword()));
        Long id = userDAO.create(user);
        user.setId(id);
        user.setPassword(null);
        return id;
    }

    public User login(User credentials) {
        String emailId;
        emailId = credentials.getEmailId();
        User user = getByEmail(emailId);
        if(!Objects.equal(user, null)) {
            boolean isValid = Cryptography.validatePassword(credentials.getPassword(), user.getPassword());
            if(isValid) {
                return user;
            } else {
                throw new IllegalArgumentException("Password is invalid");
            }
        } else {
            throw new NoSuchElementException("User not found with the given emailId");
        }
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }

    public long update(Long id, User model) {
        User existing = userDAO.read(id);
        existing.merge(model);
        userDAO.update(existing);
        return id;
    }

    public void delete(Long id) {
        userDAO.delete(id);
    }
}
