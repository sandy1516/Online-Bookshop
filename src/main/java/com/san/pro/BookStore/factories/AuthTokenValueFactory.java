package com.san.pro.BookStore.factories;

import com.san.pro.BookStore.core.AuthToken;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;

/**
 * Created by sandeepkumar.s on 11/27/2015.
 */
public class AuthTokenValueFactory extends AbstractContainerRequestValueFactory<AuthToken> {

    @Override
    public AuthToken provide() {
        return null;
    }
}
