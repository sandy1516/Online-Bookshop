package com.san.pro.BookStore.factories;

import com.auth0.jwt.JWTVerifyException;
import com.google.inject.Inject;
import com.san.pro.BookStore.config.ApiConfiguration;
import com.san.pro.BookStore.core.AuthToken;
import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.util.Cryptography;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

/**
 * Created by sandeepkumar.s on 11/27/2015.
 */
public class AuthTokenValueFactory extends AbstractContainerRequestValueFactory<AuthToken> {

    @Inject
    ApiConfiguration apiConfiguration;
    @Override
    public AuthToken provide() throws ApiException{
        HttpServletRequest request = null;
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader==null || authHeader.length()<1) {
            throw new IllegalArgumentException(" Token should be present ");
        }
        String[] splitted = authHeader.split(" ");
        if(splitted.length != 2 || !splitted[0].equals("Bearer") || splitted[1].length()<1) {
                throw new IllegalArgumentException("Token should be in form [Bearer <Token>]");
        }
        Cryptography.unsignedJwt(splitted[1], apiConfiguration.getJwtSecret(), apiConfiguration.getJwtAudience())


        return null;
    }
}
