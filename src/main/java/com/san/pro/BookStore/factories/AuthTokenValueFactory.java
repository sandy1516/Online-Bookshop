package com.san.pro.BookStore.factories;

import com.san.pro.BookStore.config.ApiConfiguration;
import com.san.pro.BookStore.core.AuthToken;
import com.san.pro.BookStore.core.Constants;
import com.san.pro.BookStore.exceptions.ApiException;
import com.san.pro.BookStore.exceptions.ErrorCodes;
import com.san.pro.BookStore.util.Cryptography;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.joda.time.Instant;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Objects;

/**
 * Created by sandeepkumar.s on 11/27/2015.
 */
public class AuthTokenValueFactory extends AbstractContainerRequestValueFactory<AuthToken> {

    @Inject
    HttpServletRequest request;

    @Inject
    @Named(ApiConfiguration.NAMED_BINDING)
    private ApiConfiguration apiConfiguration;

    @Override
    public AuthToken provide() throws ApiException{

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader==null || authHeader.length()<1) {
            throw new ApiException(new IllegalArgumentException(" Token should be present "), Response.Status.UNAUTHORIZED).addError(ErrorCodes.INVALID_AUTHORIZATION_TOKEN);
        }
        String[] splitted = authHeader.split(" ");
        if(splitted.length != 2 || !splitted[0].equals("Bearer") || splitted[1].length()<1) {
            throw new ApiException(new IllegalArgumentException(" Token should be in form [Bearer <Token>] "), Response.Status.UNAUTHORIZED).addError(ErrorCodes.INVALID_AUTHORIZATION_TOKEN);
        }
        Map<String, Object> decodePayload = Cryptography.unsignedJwt(splitted[1], apiConfiguration.getJwtSecret(), apiConfiguration.getJwtAudience());
        if(Objects.equals(null, decodePayload.get(Constants.TOKEN_EXPIRATION_IDENTIFIER))) {
            throw new ApiException(new IllegalArgumentException(" Time should be present in the token "), Response.Status.UNAUTHORIZED).addError(ErrorCodes.INVALID_AUTHORIZATION_TOKEN);
        }
        if(Objects.equals(null, decodePayload.get(Constants.USER_ID_IDENTIFIER))) {
            throw new ApiException(new IllegalArgumentException(" User Id should be present in the token "), Response.Status.UNAUTHORIZED).addError(ErrorCodes.INVALID_AUTHORIZATION_TOKEN);
        }
        long millis = new Instant().getMillis();
        if(millis > Long.valueOf(decodePayload.get(Constants.TOKEN_EXPIRATION_IDENTIFIER).toString())) {
            throw new ApiException(new IllegalArgumentException(""), Response.Status.UNAUTHORIZED).addError(ErrorCodes.JWT_TOKEN_EXPIRED);
        }
        Long userId = Long.valueOf(decodePayload.get(Constants.USER_ID_IDENTIFIER).toString());
        return new AuthToken(userId);
    }
}
