package com.san.pro.BookStore.providers;


import com.san.pro.BookStore.core.AuthToken;
import com.san.pro.BookStore.factories.AuthTokenValueFactory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.model.Parameter;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

/**
 * Created by sandeepkumar.s on 11/27/2015.
 */
@Provider
@Singleton
public class AuthParamValueFactoryProvider extends AbstractValueFactoryProvider{

    @Inject
    protected AuthParamValueFactoryProvider(MultivaluedParameterExtractorProvider mpep, ServiceLocator locator) {
        super(mpep, locator, Parameter.Source.UNKNOWN);
    }

    @Override
    protected AbstractContainerRequestValueFactory<?> createValueFactory(Parameter parameter) {
        Class<?> classType = parameter.getRawType();
        if(classType == null || !classType.equals(AuthToken.class)) {
            return null;
        }
        return new AuthTokenValueFactory();
    }
}