package com.san.pro.BookStore.service;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.san.pro.BookStore.Configuration;
import com.san.pro.BookStore.config.ApiConfiguration;
import com.san.pro.BookStore.core.Constants;
import com.san.pro.BookStore.dao.UserDAO;
import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.exceptions.ErrorCodes;
import com.san.pro.BookStore.model.User;
import com.san.pro.BookStore.util.Cryptography;
import org.joda.time.Instant;

import javax.inject.Named;
import javax.ws.rs.core.Response;
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
    public UserService(UserDAO userDAO, Configuration configuration, @Named (ApiConfiguration.NAMED_BINDING) ApiConfiguration apiConfiguration) {
        this.userDAO = userDAO;
        this.configuration = configuration;
        this.apiConfiguration = apiConfiguration;
    }

    public User getById(long id) throws ApiException {
        User user =userDAO.read(id);
        if(!Objects.equal(null, user)) {
            return user;
        } else {
            throw new ApiException(new NoSuchElementException(" User not found with the given id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public User getByEmail(String email) throws ApiException {
        User user = userDAO.findByEmail(email);
        if(Objects.equal(null, user)) {
            return user;
        } else {
            throw new ApiException(new NoSuchElementException(" User not found with the given email id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public Long create(User user) throws ApiException {
        user.setPassword(Cryptography.encryptPassword(user.getPassword()));
        Long id = userDAO.create(user);
        user.setId(id);
        user.setPassword(null);
        return id;
    }

    public String login(User credentials) throws ApiException {
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
                System.out.println(" this is claim "+claims);
                System.out.println(" this is secret "+apiConfiguration.getJwtSecret());
                return Cryptography.signJwt(claims, apiConfiguration.getJwtSecret());
            } else {
                throw new ApiException(new IllegalArgumentException(" Password is invalid "), Response.Status.UNAUTHORIZED).addError(ErrorCodes.INVALID_PASSWORD);
            }
        } else {
            throw new ApiException(new NoSuchElementException(" User not found with the given emailId "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public List<User> getAll() throws ApiException {
        List<User> userList = userDAO.findAll();
        if(!Objects.equal(null, userList)) {
            return userList;
        } else {
            throw new ApiException(new NoSuchElementException(" No User found !!!"), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public User update(Long id, User model) throws ApiException {
        User user = userDAO.read(id);
        if(!Objects.equal(null, user)) {
            user.merge(model);
            userDAO.update(user);
            return user;
        } else {
            throw new ApiException(new NoSuchElementException(" User not found with the given id "), Response.Status.NOT_FOUND).addError(ErrorCodes.RESOURCE_NOT_FOUND);
        }
    }

    public void delete(Long id) throws ApiException {
        userDAO.delete(id);
    }
}
