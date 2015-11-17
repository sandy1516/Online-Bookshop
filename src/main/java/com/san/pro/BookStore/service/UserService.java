package com.san.pro.BookStore.service;

import com.google.common.base.Optional;
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

    public User get(long id) {
        return userDAO.read(id);
    }

    public Optional<User> getByEmail(String email) {
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
        String emaiId;
        emaiId = credentials.getEmailId();
        Optional<User> existing = getByEmail(emaiId);
        if(existing.isPresent()) {
            User user = existing.get();
            boolean isValid = Cryptography.validatePassword(user.getPassword(), credentials.getPassword());
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
}
