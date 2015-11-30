package com.san.pro.BookStore.service;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.san.pro.BookStore.Configuration;
import com.san.pro.BookStore.config.ApiConfiguration;
import com.san.pro.BookStore.core.Constants;
import com.san.pro.BookStore.dao.UserDAO;
import com.san.pro.BookStore.model.User;
import com.san.pro.BookStore.util.Cryptography;
import org.joda.time.Instant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by sandeepkumar.s on 10/28/2015.
 */
public class UserService {

    private UserDAO userDAO;
    private Configuration configuration;
    private ApiConfiguration apiConfiguration;

    @Inject
    public UserService(UserDAO userDAO, Configuration configuration, ApiConfiguration apiConfiguration) {
        this.userDAO = userDAO;
        this.configuration = configuration;
        this.apiConfiguration = apiConfiguration;
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

    public String login(User credentials) {
        String emailId;
        emailId = credentials.getEmailId();
        User user = getByEmail(emailId);
        if(!Objects.equal(user, null)) {
            boolean isValid = Cryptography.validatePassword(credentials.getPassword(), user.getPassword());
            if(isValid) {
                Map<String, Object> claims = new HashMap<String, Object>();
                claims.put(Constants.USER_ID_IDENTIFIER, user.getId());
                long expiry = new Instant().getMillis() + configuration.getTokenExpirationInMillis();
                claims.put(Constants.TOKEN_EXPIRATION_IDENTIFIER, expiry);
                return Cryptography.signJwt(claims, apiConfiguration.getJwtSecret());
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

    public User update(Long id, User model) {
        User user = userDAO.read(id);
        user.merge(model);
        userDAO.update(user);
        return user;
    }

    public void delete(Long id) {
        userDAO.delete(id);
    }
}
